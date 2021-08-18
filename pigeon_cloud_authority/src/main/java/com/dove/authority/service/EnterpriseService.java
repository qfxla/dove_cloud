package com.dove.authority.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dove.authority.entity.Enterprise;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.authority.entity.vo.EnterpriseVo;
import com.dove.authority.entity.vo.StaffVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author run
 * @since 2021-03-21
 */
public interface EnterpriseService extends IService<Enterprise> {

    public EnterpriseVo getEnterpriseInfo();

    public boolean updateEnterprise(Enterprise enterprise);

    public boolean createEnterprise(Enterprise enterprise);

    public List<StaffVo> getStaffInfo();

    public IPage<StaffVo> getStaffInfo(Integer page, Integer size);

    public boolean shiftOutUser(Long userId);

    public String generateInviteCode();

    public EnterpriseVo getEnterpriseById(Long enterpriseId, String uuid);
}
