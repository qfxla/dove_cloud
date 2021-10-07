package generate;

import com.alibaba.fastjson.JSON;
import com.dove.breed.BreedApplication;
import com.dove.breed.entity.*;
import com.dove.breed.entity.dto.DovecoteDto;
import com.dove.breed.entity.dto.DovecoteOutBillDto;
import com.dove.breed.entity.dto.ManualIncubationDto;
import com.dove.breed.entity.dto.ShipmentEntryBaseDto;
import com.dove.breed.entity.vo.*;
import com.dove.breed.mapper.*;
import com.dove.breed.service.*;
import com.dove.breed.utils.ConvertUtil;
import com.dove.breed.utils.GetMonth;
import com.dove.breed.utils.Image2Mp4;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.PrinterAbortException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

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
    @Autowired
    private CagePositionService cagePositionService;
    @Autowired
    private DovecoteOutBaseMapper dovecoteOutBaseMapper;
    @Autowired
    private ManualIncubationService manualIncubationService;
    @Autowired
    private CageService cageService;
    @Autowired
    private ExecutorService executorService;
    @Autowired
    private Image2Mp4 image2Mp4;
    @Autowired
    private CagePictureMapper cagePictureMapper;
    @Autowired
    private CageVideoMapper cageVideoMapper;

    @Test
    public void test() throws IOException, InterruptedException {
        DovecoteEntryBase dovecoteEntryBase = new DovecoteEntryBase();
        dovecoteEntryBase.setType("ii");
        DovecoteEntryBaseVo convert = convertUtil.convert(dovecoteEntryBase, DovecoteEntryBaseVo.class);
        System.out.println(convert);
    }
    @Test
    public void test1() throws IOException, InterruptedException {
        ShipmentOutBill shipmentOutBill = new ShipmentOutBill();
        shipmentOutBill.setTotal(10);
        ShipmentOutBillVo convert = convertUtil.convert(shipmentOutBill, ShipmentOutBillVo.class);
        System.out.println(convert);
    }

    @Test
    public void test3(){

    }

}
