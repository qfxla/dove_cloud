import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dove.authority.AuthorityApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author run
 * @since 2021/3/20 9:39
 */
//@SpringBootTest(classes = AuthorityApplication.class)
//@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception{
        String encode = new BASE64Encoder().encode("dadadawdaw".getBytes());
        String decode = new String(new BASE64Decoder().decodeBuffer(encode));
        System.out.println(decode);
    }


}
