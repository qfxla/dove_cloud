package com.dove.breed.service;

import com.dove.breed.entity.FeedStock;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.FeedStockVo;
import com.dove.breed.entity.vo.UseOfFeedVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 月底饲料盘点表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
public interface FeedStockService extends IService<FeedStock> {
    List<UseOfFeedVo> getUseOfFeedMonth(Long baseId, String dovecoteNumber, int year, int month);

    /**
     * 更新月结表
     * @param baseId
     * @param dovecoteNumber
     */
    void updateDovecoteMonth(Long baseId, String dovecoteNumber);

    /**
     * 获取月结报表
     * @param baseId
     * @param dovecoteNumber
     * @param feedType
     * @param month
     * @return
     */
    List<FeedStockVo> getMonthlyStatementReport(Long baseId, String dovecoteNumber,String feedType,String month);

    /**
     * 根据剩余饲料和ID更新月结表
     * @param id
     * @param residue
     * @return
     */
    boolean updateResidue(Long id, Integer residue);
}
