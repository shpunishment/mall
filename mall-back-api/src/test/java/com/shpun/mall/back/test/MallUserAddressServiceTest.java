package com.shpun.mall.back.test;

import com.shpun.mall.common.model.MallUserAddress;
import com.shpun.mall.common.service.MallUserAddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 11:10
 */
@SpringBootTest
public class MallUserAddressServiceTest {

    @Autowired
    private MallUserAddressService userAddressService;

    @Test
    public void insert() {
        MallUserAddress userAddress = buildMallUserAddress(1, "张三", "18911112222", "福州市XX区XX镇XX小区", 1);
        userAddressService.insertSelective(userAddress);
    }

    private MallUserAddress buildMallUserAddress(Integer userId, String name, String phone, String address, Integer type) {
        MallUserAddress userAddress = new MallUserAddress();
        userAddress.setUserId(userId);
        userAddress.setName(name);
        userAddress.setPhone(phone);
        userAddress.setAddress(address);
        userAddress.setType(type);
        return userAddress;
    }

    @Test
    public void getByUserId() {
        List<MallUserAddress> userAddressList = userAddressService.getByUserId(1);

        System.out.println();
    }

}
