package com.shpun.mall.back.prod;

import com.shpun.mall.common.model.MallActivityClassifyProduct;
import com.shpun.mall.common.service.MallActivityClassifyProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/6 16:09
 */
@SpringBootTest
public class MallActivityClassifyProductServiceTest {

    @Autowired
    private MallActivityClassifyProductService activityClassifyProductService;

    @Test
    public void insert() {
        this.insert1();
        this.insert2();
        this.insert3();
    }

    /**
     * 造数据
     */
    @Test
    public void insert1() {

        List<Integer> productIdList;

        // 活动1-分类1
        productIdList = Stream.of(1,2,3,4).collect(Collectors.toList());
        this.insertBatch(1, productIdList);

        // 活动1-分类2
        productIdList = Stream.of(5,6,7,8).collect(Collectors.toList());
        this.insertBatch(2, productIdList);

        // 活动1-分类3
        productIdList = Stream.of(9,10,11,12).collect(Collectors.toList());
        this.insertBatch(3, productIdList);

        // 活动1-分类4
        productIdList = Stream.of(13,14,15,16).collect(Collectors.toList());
        this.insertBatch(4, productIdList);

    }

    @Test
    public void insert2() {

        List<Integer> productIdList;

        // 活动2-分类1
        productIdList = Stream.of(17,18,19,20).collect(Collectors.toList());
        this.insertBatch(5, productIdList);

        // 活动2-分类2
        productIdList = Stream.of(21,22,23,24).collect(Collectors.toList());
        this.insertBatch(6, productIdList);

        // 活动2-分类3
        productIdList = Stream.of(25,26,27,28).collect(Collectors.toList());
        this.insertBatch(7, productIdList);

        // 活动2-分类4
        productIdList = Stream.of(29,30,31,32).collect(Collectors.toList());
        this.insertBatch(8, productIdList);

    }

    @Test
    public void insert3() {

        List<Integer> productIdList;

        // 活动3-分类1
        productIdList = Stream.of(33,34,35,36).collect(Collectors.toList());
        this.insertBatch(9, productIdList);

        // 活动3-分类2
        productIdList = Stream.of(37,38,39,40).collect(Collectors.toList());
        this.insertBatch(10, productIdList);

        // 活动3-分类3
        productIdList = Stream.of(40,41,42,44).collect(Collectors.toList());
        this.insertBatch(11, productIdList);

        // 活动3-分类4
        productIdList = Stream.of(45,46,47,48).collect(Collectors.toList());
        this.insertBatch(12, productIdList);

    }

    private void insertBatch(Integer classifyId, List<Integer> productIdList) {
        productIdList.forEach(productId -> {
            MallActivityClassifyProduct activityClassifyProduct = buildMallActivityClassifyProduct(classifyId, productId);
            activityClassifyProductService.insertSelective(activityClassifyProduct);
        });

    }

    private MallActivityClassifyProduct buildMallActivityClassifyProduct(Integer classifyId, Integer productId) {
        MallActivityClassifyProduct activityClassifyProduct = new MallActivityClassifyProduct();
        activityClassifyProduct.setClassifyId(classifyId);
        activityClassifyProduct.setProductId(productId);
        return activityClassifyProduct;
    }

}
