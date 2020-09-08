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

    private String projectPath = "E:\\IDEA-workspace\\mall";

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
        fileServerService.saveLocalFile(projectPath + "\\pictures\\一级分类_水果.png");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\一级分类_蔬菜.png");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\一级分类_肉禽蛋.png");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\一级分类_海鲜水产.png");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\一级分类_粮油调味.png");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\一级分类_熟食卤味.png");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\一级分类_冰品面包.png");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\一级分类_牛奶面包.png");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\一级分类_酒水冲饮.png");
    }

    /**
     * 造数据，水果
     */
    @Test
    public void insertFirstClassifyFruit() {
//        fileServerService.saveLocalFile(projectPath + "\\pictures\\水果\\福建龙眼.jpg");
//        fileServerService.saveLocalFile(projectPath + "\\pictures\\水果\\泰国进口龙眼.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\水果\\龙岩百香果.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\水果\\越南红肉火龙果.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\水果\\泰国椰青.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\水果\\越南白肉火龙果.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\水果\\泰国山竹.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\水果\\金枕榴莲.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\水果\\国产红心火龙果.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\水果\\软心红心芭乐.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\水果\\海南木瓜.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\水果\\鲜石榴果粒.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\水果\\台湾文旦柚.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\水果\\台湾文旦柚礼盒.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\水果\\台湾莲雾.jpg");
    }

}
