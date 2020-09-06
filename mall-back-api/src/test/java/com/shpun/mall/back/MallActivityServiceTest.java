package com.shpun.mall.back;

import com.shpun.mall.common.model.MallActivity;
import com.shpun.mall.common.service.MallActivityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/6 16:08
 */
@SpringBootTest
public class MallActivityServiceTest {

    @Autowired
    private MallActivityService activityService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void insert() throws Exception {
        MallActivity activity1 = buildMallActivity("活动1", dateFormat.parse("2020-09-06 00:00:00"), dateFormat.parse("2020-09-06 23:59:59"));
        activityService.insertSelective(activity1);

        MallActivity activity2 = buildMallActivity("活动1", dateFormat.parse("2020-09-07 00:00:00"), dateFormat.parse("2020-09-08 23:59:59"));
        activityService.insertSelective(activity2);

        MallActivity activity3 = buildMallActivity("活动1", dateFormat.parse("2020-09-07 00:00:00"), dateFormat.parse("2020-09-10 23:59:59"));
        activityService.insertSelective(activity3);

        MallActivity activity4 = buildMallActivity("活动1", dateFormat.parse("2020-09-07 00:00:00"), dateFormat.parse("2020-09-11 23:59:59"));
        activityService.insertSelective(activity4);

    }

    private MallActivity buildMallActivity(String name, Date startTime, Date endTime) {
        MallActivity activity = new MallActivity();
        activity.setName(name);
        activity.setStartTime(startTime);
        activity.setEndTime(endTime);
        return activity;
    }

}
