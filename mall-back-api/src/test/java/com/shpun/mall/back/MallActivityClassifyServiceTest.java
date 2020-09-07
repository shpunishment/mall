package com.shpun.mall.back;

import com.shpun.mall.common.model.MallActivityClassify;
import com.shpun.mall.common.service.MallActivityClassifyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/6 16:09
 */
@SpringBootTest
public class MallActivityClassifyServiceTest {

    @Autowired
    private MallActivityClassifyService activityClassifyService;

    @Test
    public void insert() {
        MallActivityClassify activityClassify1 = buildMallActivityClassify("活动1-分类1", 1);
        activityClassifyService.insertSelective(activityClassify1);

        MallActivityClassify activityClassify2 = buildMallActivityClassify("活动1-分类2", 1);
        activityClassifyService.insertSelective(activityClassify2);

        MallActivityClassify activityClassify3 = buildMallActivityClassify("活动1-分类3", 1);
        activityClassifyService.insertSelective(activityClassify3);

        MallActivityClassify activityClassify4 = buildMallActivityClassify("活动1-分类4", 1);
        activityClassifyService.insertSelective(activityClassify4);

        MallActivityClassify activityClassify5 = buildMallActivityClassify("活动2-分类1", 2);
        activityClassifyService.insertSelective(activityClassify5);

        MallActivityClassify activityClassify6 = buildMallActivityClassify("活动2-分类2", 2);
        activityClassifyService.insertSelective(activityClassify6);

        MallActivityClassify activityClassify7 = buildMallActivityClassify("活动2-分类3", 2);
        activityClassifyService.insertSelective(activityClassify7);
    }

    private MallActivityClassify buildMallActivityClassify(String classifyName, Integer activityId) {
        MallActivityClassify activityClassify = new MallActivityClassify();
        activityClassify.setClassifyName(classifyName);
        activityClassify.setActivityId(activityId);
        return activityClassify;
    }

}
