package com.shpun.mall.back;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.model.MallAdmin;
import com.shpun.mall.common.service.MallAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 11:10
 */
@SpringBootTest
public class MallAdminServiceTest {

    @Autowired
    private MallAdminService adminService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insert() {
        MallAdmin admin = buildMallAdmin("admin", "admin");
        adminService.insertSelective(admin);
    }

    @Test
    public void select() {
        MallAdmin admin = adminService.selectByPrimaryKey(1);
        System.out.println();
    }

    private MallAdmin buildMallAdmin(String username, String password) {
        MallAdmin admin = new MallAdmin();
        admin.setCreateId(Const.ADMIN_ID);
        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(password));
        return admin;
    }

    @Test
    void encode() {
        System.out.println(passwordEncoder.encode("admin"));
    }

    @Test
    public void decode() {
        System.out.println(passwordEncoder.matches("admin","$2a$10$OVaK3MyIM8.8IhdeFFRzau9GP4ySpJBXghN61K06ziDO9GO6YDTU6"));
    }

}
