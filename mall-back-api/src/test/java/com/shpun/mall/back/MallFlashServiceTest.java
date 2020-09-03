package com.shpun.mall.back;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.model.MallFlash;
import com.shpun.mall.common.service.MallFlashService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 11:10
 */
@SpringBootTest
public class MallFlashServiceTest {

    @Autowired
    private MallFlashService flashService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void insert() throws ParseException {
        MallFlash flash1 = buildMallFlash(dateFormat.parse("2020-08-29 18:00:00"));
        flashService.insertSelective(flash1);

        MallFlash flash2 = buildMallFlash(dateFormat.parse("2020-08-30 09:00:00"));
        flashService.insertSelective(flash2);

        MallFlash flash3 = buildMallFlash(dateFormat.parse("2020-08-30 11:00:00"));
        flashService.insertSelective(flash3);

        MallFlash flash4 = buildMallFlash(dateFormat.parse("2020-08-30 14:00:00"));
        flashService.insertSelective(flash4);

        MallFlash flash5 = buildMallFlash(dateFormat.parse("2020-08-30 16:00:00"));
        flashService.insertSelective(flash5);

        MallFlash flash6 = buildMallFlash(dateFormat.parse("2020-08-30 18:00:00"));
        flashService.insertSelective(flash6);
    }

    private MallFlash buildMallFlash(Date startTime) {
        MallFlash flash = new MallFlash();
        flash.setCreateId(Const.ADMIN_ID);
        flash.setStartTime(startTime);
        return flash;
    }

}
