package com.shpun.mall.back.test;

import com.shpun.mall.common.model.MallUser;
import com.shpun.mall.common.service.MallUserService;
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
public class MallUserServiceTest {

    @Autowired
    private MallUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insert() {
        MallUser user = buildMallUser("zhangsan","zhangsan123","zhangsan","18911112222");
        userService.insertSelective(user);
    }

    @Test
    public void select() {
        MallUser user = userService.selectByPrimaryKey(1);
        System.out.println();
    }

    private MallUser buildMallUser(String username, String nickname, String password, String phone) {
        MallUser user = new MallUser();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhone(phone);
        return user;
    }

    @Test
    void encode() {
        System.out.println(passwordEncoder.encode("zhangsan"));
    }

    @Test
    public void decode() {
        System.out.println(passwordEncoder.matches("zhangsan","$2a$10$4Whl2BAvJ5xcThUEchGve.i3xbEQwPWdoZGQq6AeHd3Fm0tAlzWYa"));
    }

}
