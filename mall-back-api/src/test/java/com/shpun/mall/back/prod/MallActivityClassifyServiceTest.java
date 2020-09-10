package com.shpun.mall.back.prod;

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

    /**
     * 造数据
     */
    @Test
    public void insert() {
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

}
