package com.dove.breed.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.ManualIncubation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.ManualIncubationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 人工孵化表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-09-04
 */
public interface ManualIncubationService extends IService<ManualIncubation> {
    int addManualIncubationData(Long baseId,String dovecoteNumber,
                                int one,int two,int three,int four);
    //查某个鸽棚的数据
    Page<ManualIncubationVo> getByDovecoteNumber(Long baseId, String dovecoteNumber, int pageNum, int pageSize);

    List<ManualIncubationVo> getByDate(Long baseId, int year, int month, int day);
}
