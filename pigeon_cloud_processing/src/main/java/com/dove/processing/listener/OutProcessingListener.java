package com.dove.processing.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.dove.processing.entity.Vo.OutProcessInfoVo;
import com.dove.processing.entity.Vo.OutProcessingBillVo;
import com.dove.processing.entity.Vo.OutProcessingVo;
import com.dove.processing.service.OutProcessingBillService;
import com.dove.processing.service.OutProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小亮
 * @date 2021/8/23  -  0:17
 */
@Component
public class OutProcessingListener  extends AnalysisEventListener<OutProcessInfoVo> {

    @Resource
    private OutProcessingService outProcessingService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OutProcessingListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<OutProcessInfoVo> list = new ArrayList<>();


    public OutProcessingListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
        // excelUploadMapper = new DemoDAO();
    }
    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param outProcessingService
     */
    public OutProcessingListener(OutProcessingService outProcessingService) {
        this.outProcessingService = outProcessingService;
    }
    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(OutProcessInfoVo data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }
    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        if(list.size() > 0) {
            saveData();
        }
        LOGGER.info("所有数据解析完成！");
    }
    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        outProcessingService.saveList(list);
        LOGGER.info("存储数据库成功！");
    }
}
