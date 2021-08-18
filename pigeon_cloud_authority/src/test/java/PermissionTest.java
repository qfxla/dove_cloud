import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dove.authority.AuthorityApplication;
import com.dove.authority.entity.Permission;
import com.dove.authority.entity.Role;
import com.dove.authority.mapper.PermissionMapper;
import com.dove.authority.mapper.RoleMapper;
import com.dove.authority.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author run
 * @since 2021/3/19 20:20
 */
//@SpringBootTest(classes = AuthorityApplication.class)
//@RunWith(SpringRunner.class)
public class PermissionTest {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        roleMapper.addRoleOfUser(1373895872617656321L,1367409141675012099L,IdWorker.getId());
        roleMapper.addRoleOfUser(1373895873825615873L,1373909122910646274L,IdWorker.getId());
        roleMapper.addRoleOfUser(1373895874207297537L,1373909124575784962L,IdWorker.getId());


    }

    public static void addPermission(Permission permission, List<Permission> allPermission){
        String remarks = permission.getRemarks();
        remarks = remarks.substring(0,remarks.length() - 2);
        allPermission.add(new Permission(permission.getName() + "_insert",remarks + "增加",permission.getId()));
        allPermission.add(new Permission(permission.getName() + "_delete",remarks + "删除",permission.getId()));
        allPermission.add(new Permission(permission.getName() + "_update",remarks + "修改",permission.getId()));
        allPermission.add(new Permission(permission.getName() + "_select",remarks + "查询",permission.getId()));
    }
}
