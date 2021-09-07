import com.dove.authority.mapper.PermissionMapper;
import com.dove.authority.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import sun.misc.BASE64Encoder;

/**
 * @author run
 * @since 2021/3/22 15:01
 */
//@SpringBootTest(classes = AuthorityApplication.class)
//@RunWith(SpringRunner.class)
public class CommonTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Value("${jwt.salt}")
    private String SALT;

    private BASE64Encoder encoder = new BASE64Encoder();

    @Test
    public void test() {
        Long.valueOf("1354412676377280515");
    }

    //1354412676377280515
}
