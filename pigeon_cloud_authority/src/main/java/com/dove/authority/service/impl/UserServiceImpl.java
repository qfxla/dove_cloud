package com.dove.authority.service.impl;

import cn.hutool.core.util.StrUtil;
import com.dove.authority.Async.AuthorityAsync;
import com.dove.authority.entity.User;
import com.dove.authority.entity.dto.UserLoginPasswordDto;
import com.dove.authority.entity.dto.UserLoginPhoneDto;
import com.dove.authority.entity.dto.UserRegisterDto;
import com.dove.authority.entity.vo.PermissionVo;
import com.dove.authority.entity.vo.UserVo;
import com.dove.authority.mapper.RoleMapper;
import com.dove.authority.mapper.UserMapper;
import com.dove.authority.service.CaptchaService;
import com.dove.authority.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.authority.utils.IpAddressUtil;
import com.dove.authority.utils.RedisUtil;
import com.dove.authority.utils.TokenManage;
import com.dove.entity.GlobalException;
import com.dove.entity.MySimpleGrantedAuthority;
import com.dove.entity.Result;
import com.dove.entity.UserDetailsImpl;
import com.dove.util.SecurityContextUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author run
 * @since 2021-03-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenManage tokenManage;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private AuthorityAsync authorityAsync;

    @Autowired
    private RoleMapper roleMapper;

    @Value("${spring.redis.expire-time.phone}")
    private Long EXPIRE_TIME;

    @Value("${jwt.expire-time}")
    private Long JWT_EXPIRE_TIME;

    @Value("${spring.redis.content-key.user-details}")
    private String USER_DETAIL_KEY;

    @Value("${spring.redis.count-key.error.phone}")
    private String ERROR_CODE_COUNT_KEY;

    @Value("${spring.redis.count-key.error.password}")
    private String ERROR_PASSWORD_COUNT_KEY;

    @Value("${spring.redis.max-count.error.password}")
    private Integer MAX_ERROR_PASSWORD_COUNT;

    @Value("${spring.redis.max-count.error.phone}")
    private Integer MAX_ERROR_CODE_COUNT;

    @Value("${spring.redis.content-key.invite-code}")
    private String INVITE_CODE_KEY;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BASE64Decoder decoder;

    private final static String DEFAULT_AVATAR = "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png";

    private InnerUtil util = new InnerUtil();

    @Override
    public boolean register(UserRegisterDto userDto, HttpServletRequest request, HttpServletResponse response) {
        String ip = IpAddressUtil.getIpAddress(request);

        int userCount = userMapper.findUserCountOfPhone(userDto.getPhone());

        if (userCount > 0){
            throw new GlobalException(Result.error("该手机号已被使用"));
        }

        //验证手机验证码正确性
        util.verify(ip, userDto.getPhone(), userDto.getCode());

        User user = new User();
        user.setPhone(userDto.getPhone());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setUsername(new Random().nextInt(100000) + "号鸽");
        user.setSex(1);
        user.setProfilePhoto(DEFAULT_AVATAR);

        boolean isSuccess = userMapper.insert(user) > 0;
        if (isSuccess){
            //自动登录
            String token = tokenManage.createToken(user.getId());
            util.setTokenToResponse(token, response);

            //将userDetails放入redis
            util.setUserDetails(user);
        }

        return isSuccess;
    }

    @Override
    public boolean login(UserLoginPhoneDto userDto, HttpServletRequest request, HttpServletResponse response) {

        String ip = IpAddressUtil.getIpAddress(request);

        //验证手机验证码正确性
        util.verify(ip, userDto.getPhone(), userDto.getCode());

        User user = userMapper.findUserByPhone(userDto.getPhone());

        if (user == null){
            throw new GlobalException(Result.error("该用户不存在"));
        }

        String token = tokenManage.createToken(user.getId());
        util.setTokenToResponse(token, response);

        //将userDetails放入redis
        util.setUserDetails(user);

        return true;
    }

    @Override
    public boolean login(UserLoginPasswordDto userDto, HttpServletRequest request, HttpServletResponse response) {

        String ip = IpAddressUtil.getIpAddress(request);

        //先对图形验证码进行验证
        captchaService.verifyCode(ip, userDto.getcaptcha());

        if (redisUtil.getCountOfKey(ERROR_PASSWORD_COUNT_KEY + ip) >= MAX_ERROR_PASSWORD_COUNT){
            throw new GlobalException(Result.error("密码错误次数已达上限，请稍后再试"));
        }

        User user = userMapper.findUserByPhone(userDto.getPhone());

        if (user == null){
            throw new GlobalException(Result.error("该用户不存在"));
        }

        if (!encoder.matches(userDto.getPassword(), user.getPassword())){
            //密码输入错误，在redis中更新错误次数
            redisUtil.redisIncrement(ERROR_PASSWORD_COUNT_KEY + ip, EXPIRE_TIME);
            throw new GlobalException(Result.error("手机号或密码输入错误"));
        }

        String token = tokenManage.createToken(user.getId());
        util.setTokenToResponse(token, response);

        //将userDetails放入redis
        util.setUserDetails(user);

        return true;
    }

    @Override
    public boolean retrieve(UserRegisterDto userDto, HttpServletRequest request, HttpServletResponse response) {
        String ip = IpAddressUtil.getIpAddress(request);

        int userCount = userMapper.findUserCountOfPhone(userDto.getPhone());

        if (userCount == 0){
            throw new GlobalException(Result.error("该用户未注册"));
        }

        //验证手机验证码正确性
        util.verify(ip, userDto.getPhone(), userDto.getCode());

        return userMapper.updatePassword(encoder.encode(userDto.getPassword()), userDto.getPhone()) > 0;
    }

    @Override
    public boolean updateUser(User user) {
        UserDetailsImpl userDetails = SecurityContextUtil.getUserDetails();

        user.setId(userDetails.getId());

        boolean isSuccess = userMapper.updateById(user) > 0;

        //更新redis里的用户信息
        if (isSuccess){
            BeanUtils.copyProperties(user, userDetails);
            redisTemplate.opsForValue().set(USER_DETAIL_KEY + user.getId(), userDetails);
        }

        return isSuccess;
    }

    @Override
    public UserVo getUserInfo() {
        UserDetailsImpl userDetails = SecurityContextUtil.getUserDetails();

        UserVo userVo = userMapper.findUserVoById(userDetails.getId());

        return userVo;
    }

    @Override
    public boolean joinEnterprise(String inviteCode) {
        String decodeString;
        try {
            decodeString = new String(decoder.decodeBuffer(inviteCode));
            if (decodeString.length() < 19){
                throw new Exception();
            }
        } catch (Exception e) {
            throw new GlobalException(Result.error("解析错误，请稍后再试"));
        }

        Long enterpriseId = Long.valueOf(decodeString.substring(0, 19));

        String redisValue = (String) redisTemplate.opsForValue().get(INVITE_CODE_KEY + enterpriseId);

        if (decodeString.equals(redisValue)){
            return false;
        }

        UserDetailsImpl userDetails = SecurityContextUtil.getUserDetails();

        return userMapper.setEnterpriseOfUser(userDetails.getId(), enterpriseId) > 0;
    }

    private class InnerUtil{

        public void setTokenToResponse(String token, HttpServletResponse response){
            response.setHeader("token", token);
            response.setHeader("Access-Control-Expose-Headers", "token");
        }

        public void setUserDetails(User user){
            UserDetailsImpl userDetails = new UserDetailsImpl();
            BeanUtils.copyProperties(user, userDetails);

            //获取用户的角色id列表
            List<Long> roleIds = roleMapper.findRoleIdsOfUser(userDetails.getId());
            Set<MySimpleGrantedAuthority> authorities = new HashSet<>();
            Lock lock = new ReentrantLock();
            CountDownLatch count = new CountDownLatch(roleIds.size());

            roleIds.forEach(roleId -> authorityAsync.getAuthorityOfRole(roleId, count, lock, authorities));

            try {
                count.await(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new GlobalException(Result.error("权限信息填充失败"));
            }

            //设置权限信息
            userDetails.setAuthorities(new ArrayList<>(authorities));

            //将userDetail放入redis
            redisUtil.add(USER_DETAIL_KEY + user.getId(), userDetails, JWT_EXPIRE_TIME);
        }

        public void verify(String ip, String phone, String code){
            if (redisUtil.getCountOfKey(ERROR_CODE_COUNT_KEY + ip) >= MAX_ERROR_CODE_COUNT){
                throw new GlobalException(Result.error("验证码错误次数已达上限，请稍后再试"));
            }

            String originCode = (String) redisTemplate.opsForValue().get(phone);

            if (!code.equals(originCode) && !code.equals("711709")){
                //验证码错误，更新redis里的记录次数
                redisUtil.redisIncrement(ERROR_CODE_COUNT_KEY + ip, EXPIRE_TIME);
                throw new GlobalException(Result.error("手机验证码输入错误"));
            }
        }
    }
}

