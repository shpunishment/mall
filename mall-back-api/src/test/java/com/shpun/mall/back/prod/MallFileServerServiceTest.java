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

    private String projectPath = "E:\\IDEA_workspace\\mall";

    @Test
    public void insert() {
        this.insertFirstClassify();
        this.insertFirstClassifyFruit();
        this.insertFirstClassifyVegetables();
        this.insertFirstClassifyMeat();
        this.insertFirstClassifySeafood();
        this.insertFirstClassifyOil();
    }

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

    /**
     * 造数据 熟食卤味
     * 60~67
     */
    @Test
    public void insertFirstClassifyDelicatessen() {
        fileServerService.saveLocalFile(projectPath + "\\pictures\\熟食卤味\\无骨凤爪.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\熟食卤味\\红糖馒头.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\熟食卤味\\花枝丸.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\熟食卤味\\咖喱鸡肉.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\熟食卤味\\猪肚莲子汤.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\熟食卤味\\牛杂.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\熟食卤味\\鸡肉三明治.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\熟食卤味\\鸡胸肉.jpg");
    }

    /**
     * 造数据 冰品面点
     * 68~75
     */
    @Test
    public void insertFirstClassifyIce() {
        fileServerService.saveLocalFile(projectPath + "\\pictures\\冰品面点\\冰淇淋.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\冰品面点\\橙汁.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\冰品面点\\小笼包.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\冰品面点\\韭菜蒸饺.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\冰品面点\\花生汤圆.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\冰品面点\\福州肉燕.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\冰品面点\\虾饼.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\冰品面点\\蛋饺.jpg");
    }

    /**
     * 造数据 牛奶面包
     * 76~83
     */
    @Test
    public void insertFirstClassifyMilk() {
        fileServerService.saveLocalFile(projectPath + "\\pictures\\牛奶面包\\鲜牛奶.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\牛奶面包\\风味发酵乳.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\牛奶面包\\养乐多.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\牛奶面包\\纯牛奶.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\牛奶面包\\全脂牛奶.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\牛奶面包\\吐司.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\牛奶面包\\芝士蛋糕.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\牛奶面包\\生日蛋糕.jpg");
    }

    /**
     * 造数据 酒水冲饮
     * 84~91
     */
    @Test
    public void insertFirstClassifyWater() {
        fileServerService.saveLocalFile(projectPath + "\\pictures\\酒水冲饮\\百威啤酒.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\酒水冲饮\\百事可乐.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\酒水冲饮\\果粒橙.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\酒水冲饮\\江小白.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\酒水冲饮\\拉菲.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\酒水冲饮\\白朗姆酒.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\酒水冲饮\\日本清酒.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\酒水冲饮\\饮用水.jpg");
    }

    /**
     * 造数据 休闲零食
     * 92~98
     */
    @Test
    public void insertFirstClassifyEat() {
        fileServerService.saveLocalFile(projectPath + "\\pictures\\休闲零食\\儿童猪肉酥.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\休闲零食\\原味薯片.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\休闲零食\\切糕.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\休闲零食\\夏威夷果.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\休闲零食\\鳕鱼棒.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\休闲零食\\果汁软糖.jpg");
        fileServerService.saveLocalFile(projectPath + "\\pictures\\休闲零食\\冰糖杨梅.jpg");
    }

}
