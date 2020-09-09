package com.shpun.mall.back.test;

import com.shpun.mall.common.service.MallProductClassifyService;
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
public class MallProductClassifyServiceTest {

    @Autowired
    private MallProductClassifyService productClassifyService;

    @Test
    public void updateBatch() {
        List<Integer> newClassifyIdList = new ArrayList<>();
        newClassifyIdList.add(4);
        newClassifyIdList.add(5);
        newClassifyIdList.add(6);

        productClassifyService.updateBatch(newClassifyIdList, 1);
    }

}
