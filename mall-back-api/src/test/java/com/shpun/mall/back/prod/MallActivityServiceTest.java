package com.shpun.mall.back.prod;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.model.MallActivity;
import com.shpun.mall.common.model.MallActivityClassify;
import com.shpun.mall.common.model.MallActivityClassifyProduct;
import com.shpun.mall.common.service.MallActivityClassifyProductService;
import com.shpun.mall.common.service.MallActivityClassifyService;
import com.shpun.mall.common.service.MallActivityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/6 16:08
 */
@SpringBootTest
public class MallActivityServiceTest {

    @Autowired
    private MallActivityService activityService;


    @Autowired
    private MallActivityClassifyService activityClassifyService;

    @Autowired
    private MallActivityClassifyProductService activityClassifyProductService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void insert() throws Exception {
        this.insertActivity();
        this.insertActivityClassify();
        this.insertActivityClassifyProduct();
    }

    /**
     * 造数据
     * @throws Exception
     */
    @Test
    public void insertActivity() throws Exception {
        MallActivity activity1 = buildMallActivity("活动1", 99, dateFormat.parse("2020-09-09 00:00:00"), dateFormat.parse("2020-09-30 23:59:59"));
        activityService.insertSelective(activity1);

        MallActivity activity2 = buildMallActivity("活动2", 100, dateFormat.parse("2020-09-09 00:00:00"), dateFormat.parse("2020-09-30 23:59:59"));
        activityService.insertSelective(activity2);

        MallActivity activity3 = buildMallActivity("活动3", 101, dateFormat.parse("2020-09-09 00:00:00"), dateFormat.parse("2020-09-30 23:59:59"));
        activityService.insertSelective(activity3);
    }

    private MallActivity buildMallActivity(String name, Integer pic, Date startTime, Date endTime) {
        MallActivity activity = new MallActivity();
        activity.setCreateId(Const.ADMIN_ID);
        activity.setName(name);
        activity.setPic(pic);
        activity.setStatus(1);
        activity.setStartTime(startTime);
        activity.setEndTime(endTime);
        return activity;
    }


    /**
     * 造数据
     */
    @Test
    public void insertActivityClassify() {
        // 活动1
        MallActivityClassify activityClassify1 = buildMallActivityClassify("活动1-分类1", 1);
        activityClassifyService.insertSelective(activityClassify1);

        MallActivityClassify activityClassify2 = buildMallActivityClassify("活动1-分类2", 1);
        activityClassifyService.insertSelective(activityClassify2);

        MallActivityClassify activityClassify3 = buildMallActivityClassify("活动1-分类3", 1);
        activityClassifyService.insertSelective(activityClassify3);

        MallActivityClassify activityClassify4 = buildMallActivityClassify("活动1-分类4", 1);
        activityClassifyService.insertSelective(activityClassify4);

        // 活动2
        MallActivityClassify activityClassify5 = buildMallActivityClassify("活动2-分类1", 2);
        activityClassifyService.insertSelective(activityClassify5);

        MallActivityClassify activityClassify6 = buildMallActivityClassify("活动2-分类2", 2);
        activityClassifyService.insertSelective(activityClassify6);

        MallActivityClassify activityClassify7 = buildMallActivityClassify("活动2-分类3", 2);
        activityClassifyService.insertSelective(activityClassify7);

        MallActivityClassify activityClassify8 = buildMallActivityClassify("活动2-分类4", 2);
        activityClassifyService.insertSelective(activityClassify8);

        // 活动3
        MallActivityClassify activityClassify9 = buildMallActivityClassify("活动3-分类1", 3);
        activityClassifyService.insertSelective(activityClassify9);

        MallActivityClassify activityClassify10 = buildMallActivityClassify("活动3-分类2", 3);
        activityClassifyService.insertSelective(activityClassify10);

        MallActivityClassify activityClassify11 = buildMallActivityClassify("活动3-分类3", 3);
        activityClassifyService.insertSelective(activityClassify11);

        MallActivityClassify activityClassify12 = buildMallActivityClassify("活动3-分类4", 3);
        activityClassifyService.insertSelective(activityClassify12);
    }

    private MallActivityClassify buildMallActivityClassify(String classifyName, Integer activityId) {
        MallActivityClassify activityClassify = new MallActivityClassify();
        activityClassify.setClassifyName(classifyName);
        activityClassify.setActivityId(activityId);
        return activityClassify;
    }

    @Test
    public void insertActivityClassifyProduct() {
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
