package com.shpun.mall.back;

import com.shpun.mall.common.service.MallFileServerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 11:10
 */
@SpringBootTest
public class MallFileServerServiceTest {

    @Autowired
    private MallFileServerService fileServerService;

    @Test
    public void saveLocalFile() throws IOException {
        fileServerService.saveLocalFile("F:\\img\\baidu.png");
        fileServerService.saveLocalFile("F:\\img\\fruit.jpg");
    }

}
