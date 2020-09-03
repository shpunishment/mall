package com.shpun.mall.back;

import com.shpun.mall.common.model.MallUserFootprint;
import com.shpun.mall.common.service.MallUserFootprintService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 11:10
 */
@SpringBootTest
public class MallUserFootprintServiceTest {

    @Autowired
    private MallUserFootprintService userFootprintService;

    @Test
    public void insert() {
        MallUserFootprint userFootprint = buildMallUserFootprint(1, 1);
        userFootprintService.insertSelective(userFootprint);
    }

    private MallUserFootprint buildMallUserFootprint(Integer userId, Integer productId) {
        MallUserFootprint userFootprint = new MallUserFootprint();
        userFootprint.setUserId(userId);
        userFootprint.setProductId(productId);
        return userFootprint;
    }

    @Test
    public void getByUserId() {
        List<MallUserFootprint> userFootprintList = userFootprintService.getByUserId(1);

        System.out.println();
    }

}
