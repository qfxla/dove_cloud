package com.dove.processing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.processing.entity.Dto.InProcessingInfoDto;
import com.dove.processing.entity.InProcessingInfo;
import com.dove.processing.entity.Vo.InProcessingBothVo;
import com.dove.processing.entity.Vo.InProcessingInfoBindBillVo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 加工厂入库信息表 服务类
 * </p>
 *
 * @author WTL
 * @since 2021-08-23
 */
public interface InProcessingInfoService extends IService<InProcessingInfo> {

    boolean saveBothInProcessingInfo(InProcessingInfoDto inProcessingInfoDto);

    boolean deleteByIds(ArrayList<Long> ids);

    Page<InProcessingInfoBindBillVo> getInProcessingByPage(int no , int size);

    List<InProcessingInfoBindBillVo> getInInfoByInId(Long id);

    boolean updateBindInfo(Long id,InProcessingInfoDto inProcessingInfoDto);

    Page<InProcessingInfoBindBillVo> getInProcessingByLikeSearch(String value,int no ,int size);

    Page<InProcessingInfoBindBillVo> getDataByDateTime(int no,int size,String firstTime,String lastTime);
}
