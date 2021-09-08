package com.dove.breed.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dove.breed.entity.DovecoteEntryBill;
import com.baomidou.mybatisplus.extension.service.IService;

import com.dove.breed.entity.dto.DovecoteEntryBaseFodderDto;
import com.dove.breed.entity.dto.DovecoteEntryBillDto;

import com.dove.breed.entity.dto.DovecoteEntryBaseDto;
import com.dove.breed.entity.dto.DovecoteEntryBillDto;

import com.dove.breed.entity.vo.DovecoteEntryBillVo;
import com.dove.breed.entity.vo.DovecoteOutBillVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 鸽棚入仓单 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
public interface DovecoteEntryBillService extends IService<DovecoteEntryBill> {
    List<DovecoteEntryBillVo> findBillByGmt_createAndBaseId(Date startTime, Date endTime, Long dovecoteId);

//    void submitDovecoteEntryBill(DovecoteEntryBillDto dovecoteEntryBillDto, ArrayList<DovecoteEntryBaseFodderDto> dovecoteEntryBaseFodderDtoList);

    /**
     * 展示鸽棚入仓单(分页)
     * @param pageNum
     * @param pageSize
     * @param baseId
     * @param dovecoteNumber
     * @param startTime
     * @param overTime
     * @return
     */
    IPage<DovecoteEntryBill> getAllOrder(int pageNum, int pageSize, Long baseId, String dovecoteNumber,String startTime,String overTime);
    /**
     * 根据订单号删除订单
     * @param id
     * @return
     */
    boolean deleteById(Long id);

    List<DovecoteEntryBillVo> findBillByDovecoteAndType(Long baseId, String dovecoteNumber, String type);
    DovecoteEntryBillVo submitDovecoteEntryBill(DovecoteEntryBillDto dovecoteEntryBillDto, List<DovecoteEntryBaseDto> dovecoteEntryBaseDtoList);

    List<DovecoteEntryBill> getAllEntryByIdAndType(Long baseId,String type);
}
