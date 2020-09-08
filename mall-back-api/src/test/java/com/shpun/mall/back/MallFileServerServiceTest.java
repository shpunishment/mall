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

    /**
     * 测试
     */
    @Test
    public void saveLocalFile() {
        fileServerService.saveLocalFile("F:\\img\\baidu.png");
        fileServerService.saveLocalFile("F:\\img\\fruit.jpg");
    }

    /**
     * 造数据，一级分类图
     */
    @Test
    public void insertFirstClassify() {
        fileServerService.saveLocalFile("E:\\pic\\一级分类_水果.png");
        fileServerService.saveLocalFile("E:\\pic\\一级分类_蔬菜.png");
        fileServerService.saveLocalFile("E:\\pic\\一级分类_肉禽蛋.png");
        fileServerService.saveLocalFile("E:\\pic\\一级分类_海鲜水产.png");
        fileServerService.saveLocalFile("E:\\pic\\一级分类_粮油调味.png");
        fileServerService.saveLocalFile("E:\\pic\\一级分类_熟食卤味.png");
        fileServerService.saveLocalFile("E:\\pic\\一级分类_冰品面包.png");
        fileServerService.saveLocalFile("E:\\pic\\一级分类_牛奶面包.png");
        fileServerService.saveLocalFile("E:\\pic\\一级分类_酒水冲饮.png");
    }

}
