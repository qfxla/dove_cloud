package generate;

import com.dove.breed.BreedApplication;
import com.dove.breed.entity.vo.DovecoteEntryBaseVo;
import com.dove.breed.entity.vo.FeedStockVo;
import com.dove.breed.mapper.DovecoteEntryBaseMapper;
import com.dove.breed.mapper.FeedStockMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zcj
 * @creat 2021-08-21-15:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BreedApplication.class)
public class TestCode {
    @Autowired
    private FeedStockMapper feedStockMapper;
    @Autowired
    private DovecoteEntryBaseMapper dovecoteEntryBaseMapper;
    @Test
    public void test1(){

        List<DovecoteEntryBaseVo> list = dovecoteEntryBaseMapper.getFeedEntryOfMonth("羊", "A1", 2021, 8);
        System.out.println(list);
    }

}
