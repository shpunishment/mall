package com.shpun.mall.back.prod;

import com.shpun.mall.common.common.Const;
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

    /**
     * 造数据
     * @throws Exception
     */
    @Test
    public void insert() throws Exception {
        MallActivity activity1 = buildMallActivity("活动1", null, dateFormat.parse("2020-09-09 00:00:00"), dateFormat.parse("2020-09-30 23:59:59"));
        activityService.insertSelective(activity1);

        MallActivity activity2 = buildMallActivity("活动2", null, dateFormat.parse("2020-09-09 00:00:00"), dateFormat.parse("2020-09-30 23:59:59"));
        activityService.insertSelective(activity2);

        MallActivity activity3 = buildMallActivity("活动3", null, dateFormat.parse("2020-09-09 00:00:00"), dateFormat.parse("2020-09-30 23:59:59"));
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

}
