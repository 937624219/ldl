package plus.ldl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年03月16日  21:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTest {

    @Autowired
    private JdbcTemplate template;

    /**
     *
     */
    @Test
    public void test1() throws Exception {
        List<Map<String, Object>> list = template.queryForList(
                "select r.roleName\n" +
                        "from (select roleId from sys_user_role where userId = ?) ru\n" +
                        "         LEFT JOIN sys_role r on ru.roleId = r.id", 1);

        ArrayList<String> roleNames = new ArrayList<>();
        for (Map<String, Object> map : list) {
            roleNames.add((String) map.get("rolename"));
        }
        System.out.println("roleNames = " + roleNames);
    }

    /**
     *
     */
    @Test
    public void test2() throws Exception {
        List<String> list = template.query(
                "select r.roleName\n" +
                        "from (select roleId from sys_user_role where userId = ?) ru\n" +
                        "         LEFT JOIN sys_role r on ru.roleId = r.id", new BeanPropertyRowMapper<>(String.class), 1);
        System.out.println("list = " + list);
    }

    /**
     *
     */
    @Test
    public void test3() throws Exception {
    }
}
