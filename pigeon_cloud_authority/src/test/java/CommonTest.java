import cn.hutool.json.JSONString;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dove.authority.AuthorityApplication;
import com.dove.authority.entity.Permission;
import com.dove.authority.entity.Role;
import com.dove.authority.entity.User;
import com.dove.authority.mapper.PermissionMapper;
import com.dove.authority.mapper.RoleMapper;
import com.dove.authority.mapper.UserMapper;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Encoder;

import java.io.InputStream;
import java.util.Date;

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
