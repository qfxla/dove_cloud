package com.dove.processing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.processing.entity.Dto.OutProcessingDto;
import com.dove.processing.entity.OutProcessing;
import com.dove.processing.entity.Vo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 加工厂出库表 服务类
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
public interface OutProcessingService extends IService<OutProcessing> {

    Page<DoveProcessingVo> getFactoryByBossId(Long consignee, int no , int size);

    Page<OutProcessingBothBindVo> getProcessingInfoByPage(int no, int size);

    Page<OutProcessingBothBindVo> getProcessingByLikeSearch(String value ,int no,int size);

    void saveList(List<OutProcessInfoVo> list);

    boolean saveBothInfo(OutProcessingDto processingDto);

    List<OutProcessingBothBindVo> getInfoById(Long id);

    boolean updateBillInfo(Long id,OutProcessingDto outProcessingDto);

    Page<OutProcessingBothBindVo> getDataByDateTime(int no, int size, String firstTime, String lastTime);

}
