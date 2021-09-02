package generate;

import com.dove.breed.BreedApplication;
import com.dove.breed.entity.DovecoteDaily;
import com.dove.breed.entity.DovecoteEntryBase;
import com.dove.breed.entity.ShipmentEntryBill;
import com.dove.breed.entity.ShipmentOutBase;
import com.dove.breed.entity.dto.ShipmentEntryBaseDto;
import com.dove.breed.entity.vo.*;
import com.dove.breed.mapper.*;
import com.dove.breed.service.DovecoteService;
import com.dove.breed.service.FeedStockService;
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
    @Test
    public void test(){
        DovecoteDaily dovecoteDaily = new DovecoteDaily();
        dovecoteDaily.setBadEggs(100);
        dovecoteDailyMapper.insert(dovecoteDaily);
    }


    @Test
    public void test1(){
    }

}
