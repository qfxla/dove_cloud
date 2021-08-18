package com.dove.authority.Async;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dove.authority.entity.Package;
import com.dove.authority.entity.vo.PermissionVo;
import com.dove.authority.mapper.PermissionMapper;
import com.dove.authority.mapper.RoleMapper;
import com.dove.entity.GlobalException;
import com.dove.entity.MySimpleGrantedAuthority;
import com.dove.entity.Result;
import com.dove.entity.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @author run
 * @since 2021/3/20 21:48
 */
@Component
public class AuthorityAsync {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${spring.redis.content-key.user-details}")
    private String USER_DETAILS_KEY;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ExecutorService executorService;


    public void getAuthorityOfRole(Long roleId, CountDownLatch count, Lock lock, Set<MySimpleGrantedAuthority> set){
        executorService.submit(()->{
            List<String> permissions = permissionMapper.getPermissionNameOfRole(roleId);
            List<MySimpleGrantedAuthority> authorities = new ArrayList<>(permissions.size());
            permissions.forEach(permission -> authorities.add(new MySimpleGrantedAuthority(permission)));

            lock.lock();
            set.addAll(authorities);
            lock.unlock();

            count.countDown();
        });
    }




    public void updateAuthorityOfUser(Long userId, CountDownLatch count){

        executorService.submit(()->{
            //获取用户的角色id列表
            List<Long> roleIds = roleMapper.findRoleIdsOfUser(userId);
            Set<MySimpleGrantedAuthority> authorities = new HashSet<>();
            Lock lock = new ReentrantLock();
            CountDownLatch childCount = new CountDownLatch(roleIds.size());

            roleIds.forEach(roleId -> getAuthorityOfRole(roleId, childCount, lock, authorities));

            try {
                childCount.await(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new GlobalException(Result.error("权限信息填充失败"));
            }

            UserDetailsImpl userDetails = (UserDetailsImpl) redisTemplate.opsForValue().get(USER_DETAILS_KEY + userId);

            //设置权限信息
            if (userDetails != null){
                userDetails.setAuthorities(new ArrayList<>(authorities));
                redisTemplate.opsForValue().set(USER_DETAILS_KEY + userId, userDetails);
            }

            count.countDown();
        });


    }

    //非异步
    public void updateAuthorityOfUser(Long userId){
        CountDownLatch count = new CountDownLatch(1);
        updateAuthorityOfUser(userId, count);
        try {
            count.await();
        } catch (InterruptedException e) {
            throw new GlobalException(Result.error("更新权限信息失败"));
        }
    }


    public Future<String> addPermissionOfRole(List<Long> permissions, Long roleId){
        return executorService.submit(()->{
            CountDownLatch count = new CountDownLatch(permissions.size());

            permissions.forEach(permissionId -> addPermissionOfRole(roleId, permissionId, count));

            try {
                count.await(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new GlobalException(Result.error("添加权限信息错误"));
            }
            return "";
        });
    }


    public void addPermissionOfRole(Long roleId, Long permissionId, CountDownLatch count){
        executorService.submit(()->{
            permissionMapper.addPermissionOfRole(roleId, permissionId, IdWorker.getId());
            count.countDown();
        });

    }


    public Future<List<PermissionVo>> getAllPermission(List<Long> roleIds){
        return executorService.submit(() -> {
            List<PermissionVo> rootPermissionVo = permissionMapper.getRootPermissionOfRoles(roleIds);

            CountDownLatch count = new CountDownLatch(rootPermissionVo.size());

            rootPermissionVo.forEach(permission -> setChildPermission(permission, roleIds, count));

            try {
                count.await(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new GlobalException(Result.error("获取失败"));
            }

            return rootPermissionVo;
        });

    }


    public void setChildPermission(PermissionVo parent, List<Long> roleIds, CountDownLatch count){
        executorService.submit(()->{
            List<PermissionVo> childPermission = permissionMapper.getPermissionOfParentAndRoles(parent.getId(), roleIds);
            parent.setChildPermission(childPermission);

            CountDownLatch childCount = new CountDownLatch(childPermission.size());

            childPermission.forEach(permissionVo -> setChildPermission(permissionVo, roleIds, childCount));

            try {
                childCount.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            count.countDown();
        });

    }


    public void getAllPermission(Long roleId, Package<List<PermissionVo>> listPackage){
        executorService.submit(()->{
            List<PermissionVo> rootPermission = permissionMapper.getRootPermissionOfRole(roleId);

            CountDownLatch count = new CountDownLatch(rootPermission.size());

            ArrayList<Long> roleIds = new ArrayList<>(1);
            roleIds.add(roleId);

            rootPermission.forEach(permissionVo -> setChildPermission(permissionVo, roleIds, count));

            try {
                count.await(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (listPackage){
                listPackage.setValue(rootPermission);
                listPackage.notify();
            }
        });
    }
}
