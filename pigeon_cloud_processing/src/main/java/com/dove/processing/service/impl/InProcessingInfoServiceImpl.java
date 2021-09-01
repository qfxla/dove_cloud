package com.dove.processing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.processing.entity.Dto.InProcessingBillDto;
import com.dove.processing.entity.Dto.InProcessingInfoDto;
import com.dove.processing.entity.Dto.OutProcessingBillDto;
import com.dove.processing.entity.InProcessingBill;
import com.dove.processing.entity.InProcessingInfo;
import com.dove.processing.entity.OutProcessingBill;
import com.dove.processing.entity.Vo.InProcessingBillVo;
import com.dove.processing.entity.Vo.InProcessingBothVo;
import com.dove.processing.entity.Vo.InProcessingInfoBindBillVo;
import com.dove.processing.mapper.InProcessingBillMapper;
import com.dove.processing.mapper.InProcessingInfoMapper;
import com.dove.processing.service.InProcessingInfoService;
import com.dove.processing.utils.ConvertUtil;
import com.dove.processing.utils.DataUtil;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * <p>
 * 加工厂入库信息表 服务实现类
 * </p>
 *
 * @author WTL
 * @since 2021-08-23
 */
@Service
public class InProcessingInfoServiceImpl extends ServiceImpl<InProcessingInfoMapper, InProcessingInfo> implements InProcessingInfoService {

    @Resource
    private InProcessingInfoMapper inProcessingInfoMapper;

    @Resource
    private InProcessingBillMapper inProcessingBillMapper;

    @Resource
    private ConvertUtil convertUtil;

    @Resource
    private ExecutorService executorService;

    /**
     * 耦合两表添加信息
     * @param inProcessingInfoDto
     * @return
     */
    @Override
    public boolean saveBothInProcessingInfo(InProcessingInfoDto inProcessingInfoDto) {
        InProcessingInfo inProcessingInfo = convertUtil.convert(inProcessingInfoDto, InProcessingInfo.class);
        Long ID = IdWorker.getId(inProcessingInfo);
        inProcessingInfo.setInId(ID);
        int addInfo = inProcessingInfoMapper.insert(inProcessingInfo);
        List<InProcessingBillDto> list = new ArrayList<>();
        int addBillInfo = 0;
        list = inProcessingInfoDto.getInProcessingBills();
        for (int i = 0; i < list.size(); i++) {
            InProcessingBillDto inProcessingDto = list.get(i);
            InProcessingBill inProcessingBill = convertUtil.convert(inProcessingDto, InProcessingBill.class);
            inProcessingBill.setInId(ID);
            addBillInfo = inProcessingBillMapper.insert(inProcessingBill);
        }
        if (addInfo > 0 && addBillInfo > 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 批量删除出库信息
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteByIds(ArrayList<Long> list) {
        int number = inProcessingInfoMapper.batchDeleteByIds(list);
        if (number > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 分页获取全部入库信息
     * @param no
     * @param size
     * @return
     */
    @Override
    public Page<InProcessingInfoBindBillVo> getInProcessingByPage(int no, int size) {
        Future<List<InProcessingInfoBindBillVo>> future = executorService.submit(()->inProcessingInfoMapper.getInProcessByPage((no-1)*size,size));
        Page<InProcessingInfoBindBillVo> page = DataUtil.getPage(no,size,future);
        return page;
    }

    /**
     * 根据in_id获取入库信息详情
     * @param id
     * @return
     */
    @Override
    public List<InProcessingInfoBindBillVo> getInInfoByInId(Long id) {
        List<InProcessingInfoBindBillVo> list = inProcessingInfoMapper.getDetailsInfoByInId(id);
        return list;
    }

    @Override
    public boolean updateBindInfo(Long id,InProcessingInfoDto inProcessingInfoDto) {
        InProcessingInfo inProcessinginfo = convertUtil.convert(inProcessingInfoDto,InProcessingInfo.class);
        int num = inProcessingInfoMapper.update(inProcessinginfo,new QueryWrapper<InProcessingInfo>().eq("in_id",id));
        InProcessingBillDto inProcessingBillDto = inProcessingInfoDto.getInProcessingBills().get(0);
        InProcessingBill inProcessingBill = convertUtil.convert(inProcessingBillDto,InProcessingBill.class);
        inProcessingBill.setInId(id);
        int number = inProcessingBillMapper.updateById(inProcessingBill);
        if (num > 0 && number > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Page<InProcessingInfoBindBillVo> getInProcessingByLikeSearch(String value, int no, int size) {
        Future<List<InProcessingInfoBindBillVo>> future = executorService.submit(()->inProcessingInfoMapper.getInfosBySearch(value,(no-1)*size,size));
        Page<InProcessingInfoBindBillVo> page = DataUtil.getPage(no,size,future);
        return page;
    }

    /**
     * 获取一段时间戳的记录
     * @param no
     * @param size
     * @param firstTime
     * @param lastTime
     * @return
     */
    @Override
    public Page<InProcessingInfoBindBillVo> getDataByDateTime(int no,int size,String firstTime, String lastTime) {
        Future<List<InProcessingInfoBindBillVo>> future = executorService.submit(()->inProcessingInfoMapper.getDataByDateStamp((no-1)*size,size,firstTime,lastTime));
        Page<InProcessingInfoBindBillVo> page = DataUtil.getPage(no,size,future);
        return page;
    }
}

