package com.dove.authority.Async;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dove.authority.entity.vo.RoleVo;
import com.dove.authority.entity.vo.StaffVo;
import com.dove.authority.mapper.RoleMapper;
import com.dove.authority.mapper.UserMapper;
import com.dove.entity.GlobalException;
import com.dove.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author run
 * @since 2021/3/22 8:59
 */
@Component
public class UserAsync {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ExecutorService executorService;


    public void setRolesToStaff(StaffVo staff, CountDownLatch count){
        executorService.submit(()->{
            List<RoleVo> roleVos = roleMapper.findRoleVoOfUser(staff.getId());
            staff.setRoles(roleVos);
            count.countDown();
        });
    }


    public void setStaffToPage(IPage<StaffVo> staffPage, Long enterpriseId, CountDownLatch count){
        executorService.submit(()->{
            int page = (int) staffPage.getCurrent();
            int size = (int) staffPage.getSize();
            List<StaffVo> staffs = userMapper.getStaffOfEnterpriseAndPage(enterpriseId, (page - 1) * size, size);
            staffPage.setRecords(staffs);

            CountDownLatch childCount = new CountDownLatch(staffs.size());
            staffs.forEach(staff -> setRolesToStaff(staff, childCount));
            try {
                childCount.await();
            } catch (InterruptedException e) {
                throw new GlobalException(Result.error("获取失败"));
            }

            count.countDown();
        });

    }


    public void setTotalToPage(IPage<StaffVo> staffPage, Long enterpriseId, CountDownLatch count){
        executorService.submit(()->{
            int total = userMapper.getStaffCountOfEnterprise(enterpriseId);
            staffPage.setTotal(total);
            int pages = (int) (total / staffPage.getSize());
            staffPage.setPages(pages + total % staffPage.getSize() == 0 ? 0 : 1);
            count.countDown();
        });
    }
}
