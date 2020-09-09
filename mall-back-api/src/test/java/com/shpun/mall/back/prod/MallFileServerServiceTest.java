package com.shpun.mall.back.prod;

import com.shpun.mall.common.service.MallFileServerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
     * 造数据，一级分类图
     * 1~9
     */
    @Test
    public void insertFirstClassify() {
        fileServerService.saveLocalFile(projectPath + "\\pictures\\一级分类\\水果.png");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\一级分类\\蔬菜.png");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\一级分类\\肉禽蛋.png");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\一级分类\\海鲜水产.png");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\一级分类\\粮油调味.png");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\一级分类\\熟食卤味.png");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\一级分类\\冰品面包.png");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\一级分类\\牛奶面包.png");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\一级分类\\酒水冲饮.png");
    }

    /**
     * 造数据，水果 商品图
     * 10~24
     */
    @Test
    public void insertFirstClassifyFruit() {
        fileServerService.saveLocalFile(projectPath + "\\pictures\\水果\\福建龙眼.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\水果\\泰国进口龙眼.jpg");
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

    /**
     * 造数据，蔬菜 商品图
     * 25~39
     */
    @Test
    public void insertFirstClassifyVegetables() {
        fileServerService.saveLocalFile(projectPath + "\\pictures\\蔬菜\\上海青苗.png");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\蔬菜\\油麦菜苗.png");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\蔬菜\\快白苗.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\蔬菜\\流星包.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\蔬菜\\西生菜.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\蔬菜\\毛山药.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\蔬菜\\黄粒甜玉米.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\蔬菜\\蒜苔.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\蔬菜\\黄瓜.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\蔬菜\\白玉菇.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\蔬菜\\宁夏小白菜.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\蔬菜\\连江管坂空心菜.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\蔬菜\\油面筋.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\蔬菜\\卤水豆腐.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\蔬菜\\日本豆腐.jpg");
    }

    /**
     * 造数据，肉禽蛋 商品图
     * 40~47
     */
    @Test
    public void insertFirstClassifyMeat() {
        fileServerService.saveLocalFile(projectPath + "\\pictures\\肉禽蛋\\猪里脊.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\肉禽蛋\\冻筒骨.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\肉禽蛋\\冷鲜牛肉.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\肉禽蛋\\牛脖骨.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\肉禽蛋\\白鸽.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\肉禽蛋\\鸡翅根.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\肉禽蛋\\鲜鸡蛋.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\肉禽蛋\\香煎牛排.jpg");
    }

    /**
     * 造数据，海鲜水产 商品图
     * 48~52
     */
    @Test
    public void insertFirstClassifySeafood() {
        fileServerService.saveLocalFile(projectPath + "\\pictures\\海鲜水产\\黄花鱼.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\海鲜水产\\九节虾.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\海鲜水产\\生蚝.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\海鲜水产\\大闸蟹.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\海鲜水产\\小鱿鱼.jpg");
    }

    /**
     * 造数据，粮油调味 商品图
     * 53~59
     */
    @Test
    public void insertFirstClassifyOil() {
        fileServerService.saveLocalFile(projectPath + "\\pictures\\粮油调味\\香米.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\粮油调味\\葵花籽油.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\粮油调味\\螺蛳粉.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\粮油调味\\芝麻油.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\粮油调味\\沙茶酱.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\粮油调味\\味精.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\粮油调味\\绿豆.jpg");
    }

}
