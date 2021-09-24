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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.PrinterAbortException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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

    @Test
    public void test() throws IOException, InterruptedException {
//        List<Map<String, Object>> list = new ArrayList<>();
//        for (int i = 0; i < 1000;i++){
//            Map<String, Object> map = new HashMap<>();
//            map.put("index",i);
//            list.add(map);
//        }
//
//        Long start = System.currentTimeMillis();
//        for (Map<String, Object> map : list) {
//            Thread.sleep(10);
//            System.out.println(map.get("index"));
//        }
//        Long end = System.currentTimeMillis();
//        System.out.println(end-start);  //15661
    }


    @Test
    public void test1() throws IOException, InterruptedException {
//        List<Map<String, Object>> list = new ArrayList<>();
//        for (int i = 0; i < 1000;i++){
//            Map<String, Object> map = new HashMap<>();
//            map.put("index",i);
//            list.add(map);
//        }
//
//        CountDownLatch cdl = new CountDownLatch(list.size());
//
//        Long start = System.currentTimeMillis();
//        for (Map<String, Object> map : list) {
//            executorService.submit(() -> {
//                try {
//                    Thread.sleep(10);
//                    System.out.println(map.get("index"));
//                } catch (InterruptedException e) {
//                    new RuntimeException();
//                }
//                cdl.countDown();
//            });
//
//        }
//        cdl.await();
//        executorService.shutdown();
//        Long end = System.currentTimeMillis();
//        System.out.println(end-start);   //4557
    }

    @Test
    public void test3(){

    }

}
