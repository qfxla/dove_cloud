import com.dove.authority.AuthorityApplication;
import com.dove.authority.entity.vo.StaffVo;
import com.dove.authority.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author run
 * @since 2021/3/23 20:35
 */
@SpringBootTest(classes = AuthorityApplication.class)
@RunWith(SpringRunner.class)
public class AsyncTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void name() {

        List<StaffVo> staffs = userMapper.getStaffOfEnterpriseAndPage(1354412676377280515L, (1 - 1) * 10, 10);
    }
}
