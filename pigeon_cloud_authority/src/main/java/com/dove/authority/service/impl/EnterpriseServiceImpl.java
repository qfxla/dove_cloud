package com.dove.authority.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.authority.Async.AuthorityAsync;
import com.dove.authority.Async.UserAsync;
import com.dove.authority.entity.Enterprise;
import com.dove.authority.entity.Role;
import com.dove.authority.entity.vo.EnterpriseVo;
import com.dove.authority.entity.vo.StaffVo;
import com.dove.authority.mapper.EnterpriseMapper;
import com.dove.authority.mapper.PermissionMapper;
import com.dove.authority.mapper.RoleMapper;
import com.dove.authority.mapper.UserMapper;
import com.dove.authority.service.EnterpriseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.authority.utils.RedisUtil;
import com.dove.entity.GlobalException;
import com.dove.entity.Result;
import com.dove.entity.UserDetailsImpl;
import com.dove.util.SecurityContextUtil;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author run
 * @since 2021-03-21
 */
@Service
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper, Enterprise> implements EnterpriseService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private AuthorityAsync authorityAsync;

    @Autowired
    private UserAsync userAsync;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${spring.redis.content-key.invite-code}")
    private String INVITE_CODE_KEY;

    @Value("${spring.redis.expire-time.invite-code}")
    private Long EXPIRE_TIME;

    @Value("${spring.redis.content-key.user-details}")
    private String USER_DETAIL_KEY;

    @Autowired
    private BASE64Encoder encoder;


    @Autowired
    private BCryptPasswordEncoder cryptEncoder;

    @Value("${spring.redis.authority-invoke-key}")
    private String INVOKE_KEY;


    @Override
    public EnterpriseVo getEnterpriseInfo() {
        UserDetailsImpl userDetails = SecurityContextUtil.getUserDetails();

        return enterpriseMapper.getEnterpriseInfo(userDetails.getEnterpriseId());
    }

    @Override
    public boolean updateEnterprise(Enterprise enterprise) {
        UserDetailsImpl userDetails = SecurityContextUtil.getUserDetails();
        enterprise.setId(userDetails.getId());
        return enterpriseMapper.updateById(enterprise) > 0;
    }

    @Override
    @Transactional
    public boolean createEnterprise(Enterprise enterprise) {
        UserDetailsImpl userDetails = SecurityContextUtil.getUserDetails();
        if (userDetails.getEnterpriseId() != null){
            throw new GlobalException(Result.error("已加入企业，无法创建"));
        }

        boolean isSuccess = enterpriseMapper.insert(enterprise) > 0;

        if (!isSuccess){
            throw new GlobalException(Result.error("创建失败"));
        }

        //创建初始角色
        Role role = new Role();
        role.setEnterpriseId(enterprise.getId());
        role.setName("创始人");
        role.setWeight(0);
        isSuccess = roleMapper.insert(role) > 0;

        if (!isSuccess){
            throw new GlobalException(Result.error("创建失败"));
        }

        //赋予初始角色所有权限
        List<Long> allPermission = permissionMapper.getAllPermission();
        Future<String> task = authorityAsync.addPermissionOfRole(allPermission, role.getId());

        while (!task.isDone()){
            Thread.yield();
        }

        return true;
    }

    @Override
    public List<StaffVo> getStaffInfo() {
        UserDetailsImpl userDetails = SecurityContextUtil.getUserDetails();

        List<StaffVo> staffs = userMapper.getStaffOfEnterprise(userDetails.getEnterpriseId());
        CountDownLatch count = new CountDownLatch(staffs.size());
        staffs.forEach(staff -> userAsync.setRolesToStaff(staff, count));

        try {
            count.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new GlobalException(Result.error("获取失败"));
        }

        return staffs;
    }

    @Override
    public IPage<StaffVo> getStaffInfo(Integer page, Integer size) {
        UserDetailsImpl userDetails = SecurityContextUtil.getUserDetails();
        if (page < 1 || size < 1){
            throw new GlobalException(Result.error("页码参数输入错误"));
        }

        IPage<StaffVo> result = new Page<>(page, size);

        CountDownLatch count = new CountDownLatch(2);

        userAsync.setStaffToPage(result, userDetails.getEnterpriseId(), count);
        userAsync.setTotalToPage(result, userDetails.getEnterpriseId(), count);

        try {
            count.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new GlobalException(Result.error("获取失败"));
        }

        return result;
    }

    @Override
    @Transactional
    public boolean shiftOutUser(Long userId) {
        UserDetailsImpl userDetails = SecurityContextUtil.getUserDetails();
        int MyWeightest = roleMapper.getWeightestOfUser(userDetails.getId());
        int adverseWeightest = roleMapper.getWeightestOfUser(userId);

        if (MyWeightest <= adverseWeightest){
            throw new GlobalException(Result.error("无法移出权重大于等于自己的用户"));
        }

        boolean isSuccess = userMapper.shiftOutEnterprise(userId, userDetails.getEnterpriseId()) > 0;

        if (isSuccess){
            userDetails.setAuthorities(null);
            //更新redis里的权限信息
            redisTemplate.opsForHash().put(USER_DETAIL_KEY, userDetails.getId(), userDetails);
            //删除用户的角色信息
            roleMapper.deleteRoleOfUser(userId);
        }

        return isSuccess;
    }

    @Override
    public String generateInviteCode() {
        UserDetailsImpl userDetails = SecurityContextUtil.getUserDetails();

        String redisKey = INVITE_CODE_KEY + userDetails.getEnterpriseId();

        if (redisTemplate.hasKey(redisKey)){
            return ((String) redisTemplate.opsForValue().get(redisKey));
        }

        long mills = System.currentTimeMillis();

        String encode = encoder.encode((userDetails.getEnterpriseId() + "" + mills).getBytes());

        redisUtil.add(redisKey, encode, EXPIRE_TIME);

        return encode;
    }

    @Override
    public EnterpriseVo getEnterpriseById(Long enterpriseId, String uuid) {
        String secretKey = (String) redisTemplate.opsForValue().get(INVOKE_KEY);

        boolean matches = cryptEncoder.matches(uuid, secretKey);
        if (!matches){
            throw new GlobalException(Result.error("密钥匹配失败"));
        }

        redisTemplate.expire(INVOKE_KEY, 0, TimeUnit.MILLISECONDS);

        return enterpriseMapper.getEnterpriseInfo(enterpriseId);
    }
}
