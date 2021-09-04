package generate;

import com.dove.breed.BreedApplication;
import com.dove.breed.entity.*;
import com.dove.breed.entity.dto.DovecoteOutBillDto;
import com.dove.breed.entity.dto.ShipmentEntryBaseDto;
import com.dove.breed.entity.vo.*;
import com.dove.breed.mapper.*;
import com.dove.breed.service.*;
import com.dove.breed.utils.ConvertUtil;
import io.swagger.annotations.ApiOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.PrinterAbortException;
import java.util.*;

/**
 * @author zcj
 * @creat 2021-08-21-15:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BreedApplication.class)
public class TestCode {

    @Autowired
    private DovecoteService dovecoteService;
    @Autowired
    private  DovecoteMapper dovecoteMapper;
    @Autowired
    private CageMapper cageMapper;
    @Autowired
    private ShipmentEntryBillMapper shipmentEntryBillMapper;
    @Autowired
    private FeedStockService feedStockService;
    @Autowired
    private FeedStockMapper feedStockMapper;
    @Autowired
    private ShipmentEntryTypeMapper shipmentEntryTypeMapper;
    @Autowired
    private DovecoteEntryBaseMapper dovecoteEntryBaseMapper;
    @Autowired
    private CagePositionMapper cagePositionMapper;
    @Autowired
    private DovecoteDailyMapper dovecoteDailyMapper;
    @Autowired
    private DovecoteDailyService dovecoteDailyService;
    @Autowired
    private CageRealService cageRealService;
    @Autowired
    private CageRealMapper cageRealMapper;
    @Autowired
    private DovecoteOutBillMapper dovecoteOutBillMapper;
    @Autowired
    private DovecoteOutBillService dovecoteOutBillService;
    @Autowired
    private ConvertUtil convertUtil;
    @Test
    public void test(){
        DovecoteOutBillDto dovecoteOutBillDto = new DovecoteOutBillDto();
        dovecoteOutBillDto.setDovecoteDirector("张三");
        dovecoteOutBillDto.setBaseId(12L);
        DovecoteOutBill dovecoteOutBill = convertUtil.convert(dovecoteOutBillDto, DovecoteOutBill.class);
        int insert = dovecoteOutBillMapper.insert(dovecoteOutBill);
        System.out.println(insert);
    }


    @Test
    public void test1(){
    }

}
