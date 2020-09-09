package com.shpun.mall.back.prod;

import com.shpun.mall.common.model.MallProduct;
import com.shpun.mall.common.service.MallProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 11:10
 */
@SpringBootTest
public class MallProductServiceTest {

    @Autowired
    private MallProductService productService;

    /**
     * 造数据
     */
    /**
     * 水果
     * 1~15
     */
    @Test
    public void insert1() {
        List<Integer> classifyIdList;

        classifyIdList = Stream.of(11).collect(Collectors.toList());
        MallProduct product1 = buildMallProduct("福建龙眼", "肉质臾脆 味芳香", 10, new BigDecimal("13.5"),
                new BigDecimal("7.99"),100, 10, "0-4℃冷藏", "480-500g/份", "冷藏（0-4）℃","福建",10);
        productService.insertWithClassifyIdList(product1, classifyIdList);

        classifyIdList = Stream.of(11, 13).collect(Collectors.toList());
        MallProduct product2 = buildMallProduct("泰国进口龙眼", "皮薄 脆嫩 甜蜜", 11, new BigDecimal("9.9"),
                new BigDecimal("7.98"),100, 10, null, "500g/份", "冷藏（0-4）℃","泰国",11);
        productService.insertWithClassifyIdList(product2, classifyIdList);

        classifyIdList = Stream.of(11).collect(Collectors.toList());
        MallProduct product3 = buildMallProduct("龙岩百香果", "酸甜多汁 香味浓郁", 12, new BigDecimal("18.9"),
                new BigDecimal("15.9"),80, 10, null, "1kg/15-18个", "中温（8-12）℃","福建龙岩",12);
        productService.insertWithClassifyIdList(product3, classifyIdList);

        classifyIdList = Stream.of(11, 13).collect(Collectors.toList());
        MallProduct product4 = buildMallProduct("越南红肉火龙果", "清甜不腻 水分充足", 13, new BigDecimal("8.9"),
                new BigDecimal("6.5"),90, 10, null, "350g-450g/个", "冷藏（0-4）℃","越南",13);
        productService.insertWithClassifyIdList(product4, classifyIdList);

        classifyIdList = Stream.of(11, 13).collect(Collectors.toList());
        MallProduct product5 = buildMallProduct("泰国椰青", "怕油腻 喝椰青", 14, new BigDecimal("14.9"),
                new BigDecimal("8.9"),90, 5, null, "800g/个", "冷藏（0-4）℃","泰国",14);
        productService.insertWithClassifyIdList(product5, classifyIdList);

        classifyIdList = Stream.of(11, 13).collect(Collectors.toList());
        MallProduct product6 = buildMallProduct("越南白肉火龙果", "皮薄肉厚 细嫩爽滑", 15, new BigDecimal("7.9"),
                new BigDecimal("6.5"),90, 10, null, "440g-490g/个", "冷藏（0-4）℃","越南",15);
        productService.insertWithClassifyIdList(product6, classifyIdList);

        classifyIdList = Stream.of(11, 13).collect(Collectors.toList());
        MallProduct product7 = buildMallProduct("泰国山竹", "南部麻竹果皮更丑", 16, new BigDecimal("14.9"),
                new BigDecimal("9.9"),90, 10, null, "300g-350g/个", "中温（8-12）℃","泰国",16);
        productService.insertWithClassifyIdList(product7, classifyIdList);

        classifyIdList = Stream.of(11, 13).collect(Collectors.toList());
        MallProduct product8 = buildMallProduct("金枕榴莲", "香糯甜 可即食", 17, new BigDecimal("209"),
                new BigDecimal("209"),90, 5, null, "3kg-3.5kg/个", "常温","泰国",17);
        productService.insertWithClassifyIdList(product8, classifyIdList);

        classifyIdList = Stream.of(11).collect(Collectors.toList());
        MallProduct product9 = buildMallProduct("国产红心火龙果", "果已成熟 外观皱皮暗红", 18, new BigDecimal("6.98"),
                new BigDecimal("4.98"),90, 10, null, "295g-395g/个", "冷藏（0-4）℃","广西",18);
        productService.insertWithClassifyIdList(product9, classifyIdList);

        classifyIdList = Stream.of(11).collect(Collectors.toList());
        MallProduct product10 = buildMallProduct("软心红心芭乐", "香甜软糯（放置软口感更佳）", 19, new BigDecimal("6.98"),
                new BigDecimal("6.98"),90, 10, null, "400g-450g/个", "中温（12-16）℃","广东汕头",19);
        productService.insertWithClassifyIdList(product10, classifyIdList);

        classifyIdList = Stream.of(11).collect(Collectors.toList());
        MallProduct product11 = buildMallProduct("海南木瓜", "果皮转黄后再食用", 20, new BigDecimal("6.9"),
                new BigDecimal("6.9"),90, 10, null, "500g-550g/个", "中温（8-12）℃","海南",20);
        productService.insertWithClassifyIdList(product11, classifyIdList);

        classifyIdList = Stream.of(11, 12).collect(Collectors.toList());
        MallProduct product12 = buildMallProduct("鲜石榴果粒", "去皮石榴 食用方便", 21, new BigDecimal("7.9"),
                new BigDecimal("7.9"),20, 10, "0-4℃冷藏", "180g/盒", "冷藏（0-4）℃","云南蒙自县",21);
        productService.insertWithClassifyIdList(product12, classifyIdList);

        classifyIdList = Stream.of(11, 14).collect(Collectors.toList());
        MallProduct product13 = buildMallProduct("台湾文旦柚", "虽然我很丑 但我好吃", 22, new BigDecimal("15.9"),
                new BigDecimal("13.9"),20, 10, null, "400g-500g/个", "中温（8-12）℃","台湾",22);
        productService.insertWithClassifyIdList(product13, classifyIdList);

        classifyIdList = Stream.of(11, 14, 15).collect(Collectors.toList());
        MallProduct product14 = buildMallProduct("台湾文旦柚礼盒", "虽然我很丑 但我好吃", 23, new BigDecimal("89"),
                new BigDecimal("89"),20, 10, null, "2350g/5个", "中温（8-12）℃","台湾",23);
        productService.insertWithClassifyIdList(product14, classifyIdList);

        classifyIdList = Stream.of(11, 14).collect(Collectors.toList());
        MallProduct product15 = buildMallProduct("台湾莲雾", "清香脆甜", 24, new BigDecimal("19.9"),
                new BigDecimal("19.9"),20, 10, null, "180g-230g/个", "中温（8-12）℃","台湾",24);
        productService.insertWithClassifyIdList(product15, classifyIdList);
    }

    /**
     * 蔬菜
     *
     */
    @Test
    public void insert2() {
        List<Integer> classifyIdList;

        classifyIdList = Stream.of(16).collect(Collectors.toList());
        MallProduct product1 = buildMallProduct("上海青苗", "基地种植 时令鲜蔬 清新脆嫩", 25, new BigDecimal("6.9"),
                new BigDecimal("6.9"),50, 5, null, "250g/份", "冷藏（0-4）℃","宁夏吴忠",25);
        productService.insertWithClassifyIdList(product1, classifyIdList);

        classifyIdList = Stream.of(16).collect(Collectors.toList());
        MallProduct product2 = buildMallProduct("油麦菜苗", "口感鲜嫩", 26, new BigDecimal("4.98"),
                new BigDecimal("4.98"),50, 5, null, "250g/份", "冷藏（0-4）℃","云南",26);
        productService.insertWithClassifyIdList(product2, classifyIdList);

        classifyIdList = Stream.of(16).collect(Collectors.toList());
        MallProduct product3 = buildMallProduct("快白苗", "质地鲜嫩 清新脆口", 27, new BigDecimal("4.98"),
                new BigDecimal("3.98"),50, 5, null, "250g/份", "中温（8-12）℃","福建南平/福建厦门",27);
        productService.insertWithClassifyIdList(product3, classifyIdList);

        classifyIdList = Stream.of(17).collect(Collectors.toList());
        MallProduct product4 = buildMallProduct("流星包", "嫩脆爽口", 28, new BigDecimal("3.8"),
                new BigDecimal("3.8"),50, 5, null, "400g/份", "冷藏（0-4）℃","福建",28);
        productService.insertWithClassifyIdList(product4, classifyIdList);

        classifyIdList = Stream.of(17).collect(Collectors.toList());
        MallProduct product5 = buildMallProduct("西生菜", "口感鲜嫩 清甜可口", 29, new BigDecimal("8.8"),
                new BigDecimal("8.8"),50, 5, null, "400g/份", "冷藏（0-4）℃","云南昆明",29);
        productService.insertWithClassifyIdList(product5, classifyIdList);

        classifyIdList = Stream.of(18).collect(Collectors.toList());
        MallProduct product6 = buildMallProduct("毛山药", "粘液丰富", 30, new BigDecimal("6.98"),
                new BigDecimal("5.98"),50, 5, null, "500g/份", "中温（8-12）℃","河北保定",30);
        productService.insertWithClassifyIdList(product6, classifyIdList);

        classifyIdList = Stream.of(18).collect(Collectors.toList());
        MallProduct product7 = buildMallProduct("黄粒甜玉米", "甜嫩软糯", 31, new BigDecimal("4.98"),
                new BigDecimal("4.58"),50, 10, null, "550g/份", "冷藏（0-4）℃","广东惠州",31);
        productService.insertWithClassifyIdList(product7, classifyIdList);

        classifyIdList = Stream.of(19).collect(Collectors.toList());
        MallProduct product8 = buildMallProduct("蒜苔", "蒜苔炒肉丝 经典美味", 32, new BigDecimal("4.98"),
                new BigDecimal("4.98"),50, 5, null, "200g/份", "冷藏（0-4）℃","山东临沂",32);
        productService.insertWithClassifyIdList(product8, classifyIdList);

        classifyIdList = Stream.of(20).collect(Collectors.toList());
        MallProduct product9 = buildMallProduct("黄瓜", "鲜香脆爽", 33, new BigDecimal("3.98"),
                new BigDecimal("2.98"),50, 5, null, "500g/份", "中温（8-12）℃","福州",33);
        productService.insertWithClassifyIdList(product9, classifyIdList);

        classifyIdList = Stream.of(21).collect(Collectors.toList());
        MallProduct product10 = buildMallProduct("白玉菇", "菇体洁白 脆嫩鲜滑", 34, new BigDecimal("3.98"),
                new BigDecimal("3.98"),50, 5, null, "150g/份", "冷藏（0-4）℃","上海市",34);
        productService.insertWithClassifyIdList(product10, classifyIdList);

        classifyIdList = Stream.of(17, 22).collect(Collectors.toList());
        MallProduct product11 = buildMallProduct("宁夏小白菜", "鲜嫩翠绿 营养爽口", 35, new BigDecimal("5.8"),
                new BigDecimal("5.8"),50, 5, null, "300g/个", "冷藏（0-4）℃","云南沪西",35);
        productService.insertWithClassifyIdList(product11, classifyIdList);

        classifyIdList = Stream.of(17, 22).collect(Collectors.toList());
        MallProduct product12 = buildMallProduct("连江管坂空心菜", "围海造田 盐碱地种植 带有咸味", 36, new BigDecimal("4.8"),
                new BigDecimal("2.99"),50, 5, null, "300g/份", "冷藏（0-4）℃","福建福州",36);
        productService.insertWithClassifyIdList(product12, classifyIdList);

        classifyIdList = Stream.of(23).collect(Collectors.toList());
        MallProduct product13 = buildMallProduct("油面筋", "大小均匀 酥脆可口", 37, new BigDecimal("6.8"),
                new BigDecimal("5.8"),50, 5, null, "65g/袋", "常温","福建厦门",37);
        productService.insertWithClassifyIdList(product13, classifyIdList);

        classifyIdList = Stream.of(23).collect(Collectors.toList());
        MallProduct product14 = buildMallProduct("卤水豆腐", "口感细腻 健康美味", 38, new BigDecimal("7"),
                new BigDecimal("5.9"),50, 5, "0-4℃冷藏", "350g/包", "冰鲜（0-4）℃","福建福州",38);
        productService.insertWithClassifyIdList(product14, classifyIdList);

        classifyIdList = Stream.of(23).collect(Collectors.toList());
        MallProduct product15 = buildMallProduct("日本豆腐", "爽滑鲜嫩 蛋味清香", 39, new BigDecimal("1.8"),
                new BigDecimal("0.99"),50, 5, null, "95g/条", "冷藏（0-10）℃","福建厦门",39);
        productService.insertWithClassifyIdList(product15, classifyIdList);
    }

    /**
     * 肉禽蛋
     *
     */
    @Test
    public void insert3() {
        List<Integer> classifyIdList;

        classifyIdList = Stream.of(24).collect(Collectors.toList());
        MallProduct product1 = buildMallProduct("猪里脊", "肉质透明 富有弹性", 40, new BigDecimal("45.8"),
                new BigDecimal("41.22"),50, 5, null, "248g/份", "冷藏（0-4）℃","上杭",40);
        productService.insertWithClassifyIdList(product1, classifyIdList);

        classifyIdList = Stream.of(25).collect(Collectors.toList());
        MallProduct product2 = buildMallProduct("冻筒骨", "鲜香醇厚 炖汤佳品", 41, new BigDecimal("19.8"),
                new BigDecimal("16.8"),50, 5, "-18℃冷冻", "500g/份", "冷冻-18℃以下","福州",41);
        productService.insertWithClassifyIdList(product2, classifyIdList);

        classifyIdList = Stream.of(26).collect(Collectors.toList());
        MallProduct product3 = buildMallProduct("冷鲜牛肉", "选用冷鲜牛后腿肉", 42, new BigDecimal("25.8"),
                new BigDecimal("23.22"),50, 5, null, "250g/份", "冷藏（0-4）℃","福建福州",42);
        productService.insertWithClassifyIdList(product3, classifyIdList);

        classifyIdList = Stream.of(27).collect(Collectors.toList());
        MallProduct product4 = buildMallProduct("牛脖骨", "原切牛脖骨 适合炖汤", 43, new BigDecimal("18.8"),
                new BigDecimal("18.8"),50, 5, null, "350g/袋", "冷冻-18℃以下","福建福州",43);
        productService.insertWithClassifyIdList(product4, classifyIdList);

        classifyIdList = Stream.of(28).collect(Collectors.toList());
        MallProduct product5 = buildMallProduct("白鸽", "山林散养 口感嫩滑清甜", 44, new BigDecimal("39.9"),
                new BigDecimal("35.91"),50, 5, "0-4℃冷藏", "300g/份", "冷藏（0-4）℃","福建福州",44);
        productService.insertWithClassifyIdList(product5, classifyIdList);

        classifyIdList = Stream.of(29).collect(Collectors.toList());
        MallProduct product6 = buildMallProduct("鸡翅根", "肉厚骨细", 45, new BigDecimal("11.8"),
                new BigDecimal("9.9"),50, 5, "-18℃冷冻", "400g/袋", "冷冻-18℃以下","聊城",45);
        productService.insertWithClassifyIdList(product6, classifyIdList);

        classifyIdList = Stream.of(30).collect(Collectors.toList());
        MallProduct product7 = buildMallProduct("鲜鸡蛋", "营养品质", 46, new BigDecimal("14.9"),
                new BigDecimal("9.9"),50, 10, null, "700g/份", "常温","福建泉州",46);
        productService.insertWithClassifyIdList(product7, classifyIdList);

        classifyIdList = Stream.of(31).collect(Collectors.toList());
        MallProduct product8 = buildMallProduct("香煎牛排", "细嫩美味 十分傲“椒”", 47, new BigDecimal("13.8"),
                new BigDecimal("12.8"),50, 5, null, "100g/袋", "冷冻-18℃以下","福建南平",47);
        productService.insertWithClassifyIdList(product8, classifyIdList);
    }

    /**
     * 海鲜水产
     *
     */
    @Test
    public void insert4() {
        List<Integer> classifyIdList;

        classifyIdList = Stream.of(32).collect(Collectors.toList());
        MallProduct product1 = buildMallProduct("黄花鱼", "肉质鲜嫩 刺少肉多", 48, new BigDecimal("9.9"),
                new BigDecimal("8.5"),50, 5, null, "160g/条", "冰鲜（0-4）℃","福州",48);
        productService.insertWithClassifyIdList(product1, classifyIdList);

        classifyIdList = Stream.of(33).collect(Collectors.toList());
        MallProduct product2 = buildMallProduct("九节虾", "肉质肥厚爽嫩 鲜味十足", 49, new BigDecimal("88"),
                new BigDecimal("66"),50, 5, "-18℃冷冻", "250g±5g/份", "鱼池","山东",49);
        productService.insertWithClassifyIdList(product2, classifyIdList);

        classifyIdList = Stream.of(34).collect(Collectors.toList());
        MallProduct product3 = buildMallProduct("生蚝", "肉质鲜嫩 口感爽", 50, new BigDecimal("17.8"),
                new BigDecimal("16.9"),50, 5, null, "4个/盒", "冰鲜（0-4）℃","广东",50);
        productService.insertWithClassifyIdList(product3, classifyIdList);

        classifyIdList = Stream.of(35).collect(Collectors.toList());
        MallProduct product4 = buildMallProduct("大闸蟹", "蟹肉鲜甜 肉质饱满", 51, new BigDecimal("10.8"),
                new BigDecimal("9.9"),50, 5, null, "65-85g/只", null,"江苏省常州市",51);
        productService.insertWithClassifyIdList(product4, classifyIdList);

        classifyIdList = Stream.of(36).collect(Collectors.toList());
        MallProduct product5 = buildMallProduct("小鱿鱼", "肉质Q弹 肉质鲜嫩", 52, new BigDecimal("16.9"),
                new BigDecimal("13.9"),50, 5, "远洋捕捞", "300g/包", "冷冻-18℃以下","福清",52);
        productService.insertWithClassifyIdList(product5, classifyIdList);
    }

    /**
     * 粮油调味
     *
     */
    @Test
    public void insert5() {
        List<Integer> classifyIdList;

        classifyIdList = Stream.of(37).collect(Collectors.toList());
        MallProduct product1 = buildMallProduct("香米", "软糯适中 干稀饭均可", 53, new BigDecimal("15.8"),
                new BigDecimal("11.9"),50, 5, null, "1kg", null,"黑龙江牡丹江",48);
        productService.insertWithClassifyIdList(product1, classifyIdList);

        classifyIdList = Stream.of(38).collect(Collectors.toList());
        MallProduct product2 = buildMallProduct("葵花籽油", "好妈妈健康之选", 54, new BigDecimal("89.9"),
                new BigDecimal("79.5"),50, 5, null, "5L", "常温","广东深圳",54);
        productService.insertWithClassifyIdList(product2, classifyIdList);

        classifyIdList = Stream.of(39).collect(Collectors.toList());
        MallProduct product3 = buildMallProduct("螺蛳粉", "经典柳州螺蛳粉", 55, new BigDecimal("18.9"),
                new BigDecimal("13.9"),50, 5, null, "400g/包", "常温","广西",55);
        productService.insertWithClassifyIdList(product3, classifyIdList);

        classifyIdList = Stream.of(40).collect(Collectors.toList());
        MallProduct product4 = buildMallProduct("芝麻油", "纯芝麻油 持久留香", 56, new BigDecimal("15.8"),
                new BigDecimal("11.8"),50, 5, null, "145ml/瓶", "常温","上海",56);
        productService.insertWithClassifyIdList(product4, classifyIdList);

        classifyIdList = Stream.of(41).collect(Collectors.toList());
        MallProduct product5 = buildMallProduct("沙茶酱", "酱香浓郁 火锅蘸酱", 57, new BigDecimal("34.9"),
                new BigDecimal("32.8"),50, 5, null, "250g/瓶", "常温","中国台湾",57);
        productService.insertWithClassifyIdList(product5, classifyIdList);

        classifyIdList = Stream.of(42).collect(Collectors.toList());
        MallProduct product6 = buildMallProduct("味精", "增味提鲜", 58, new BigDecimal("19.8"),
                new BigDecimal("16.8"),50, 5, null, "908g/包", "常温","福建",58);
        productService.insertWithClassifyIdList(product6, classifyIdList);

        classifyIdList = Stream.of(43).collect(Collectors.toList());
        MallProduct product7 = buildMallProduct("绿豆", "皮薄芯糯 易熟好豆", 59, new BigDecimal("15.8"),
                new BigDecimal("12.8"),50, 5, null, "1000g/包", "常温","福州",59);
        productService.insertWithClassifyIdList(product7, classifyIdList);
    }

    /**
     * 熟食卤味
     * todo
     * 
     */
    @Test
    public void insert6() {
        List<Integer> classifyIdList;

        classifyIdList = Stream.of(37).collect(Collectors.toList());
        MallProduct product1 = buildMallProduct("香米", "软糯适中 干稀饭均可", 53, new BigDecimal("15.8"),
                new BigDecimal("11.9"),50, 5, null, "1kg", null,"黑龙江牡丹江",48);
        productService.insertWithClassifyIdList(product1, classifyIdList);

        classifyIdList = Stream.of(38).collect(Collectors.toList());
        MallProduct product2 = buildMallProduct("葵花籽油", "好妈妈健康之选", 54, new BigDecimal("89.9"),
                new BigDecimal("79.5"),50, 5, null, "5L", "常温","广东深圳",54);
        productService.insertWithClassifyIdList(product2, classifyIdList);

        classifyIdList = Stream.of(39).collect(Collectors.toList());
        MallProduct product3 = buildMallProduct("螺蛳粉", "经典柳州螺蛳粉", 55, new BigDecimal("18.9"),
                new BigDecimal("13.9"),50, 5, null, "400g/包", "常温","广西",55);
        productService.insertWithClassifyIdList(product3, classifyIdList);

        classifyIdList = Stream.of(40).collect(Collectors.toList());
        MallProduct product4 = buildMallProduct("芝麻油", "纯芝麻油 持久留香", 56, new BigDecimal("15.8"),
                new BigDecimal("11.8"),50, 5, null, "145ml/瓶", "常温","上海",56);
        productService.insertWithClassifyIdList(product4, classifyIdList);

        classifyIdList = Stream.of(41).collect(Collectors.toList());
        MallProduct product5 = buildMallProduct("沙茶酱", "酱香浓郁 火锅蘸酱", 57, new BigDecimal("34.9"),
                new BigDecimal("32.8"),50, 5, null, "250g/瓶", "常温","中国台湾",57);
        productService.insertWithClassifyIdList(product5, classifyIdList);

        classifyIdList = Stream.of(42).collect(Collectors.toList());
        MallProduct product6 = buildMallProduct("味精", "增味提鲜", 58, new BigDecimal("19.8"),
                new BigDecimal("16.8"),50, 5, null, "908g/包", "常温","福建",58);
        productService.insertWithClassifyIdList(product6, classifyIdList);

        classifyIdList = Stream.of(43).collect(Collectors.toList());
        MallProduct product7 = buildMallProduct("绿豆", "皮薄芯糯 易熟好豆", 59, new BigDecimal("15.8"),
                new BigDecimal("12.8"),50, 5, null, "1000g/包", "常温","福州",59);
        productService.insertWithClassifyIdList(product7, classifyIdList);
    }

    private MallProduct buildMallProduct(String productName, String desc, Integer pic,
                                         BigDecimal originPrice, BigDecimal currentPrice,
                                         Integer stock, Integer limit, String label,
                                         String format, String storage, String origin, Integer detail){
        MallProduct product = new MallProduct();
        product.setProductName(productName);
        product.setDesc(desc);
        product.setPic(pic);
        product.setOriginalPrice(originPrice);
        product.setCurrentPrice(currentPrice);
        product.setStock(stock);
        product.setLimit(limit);
        product.setLabel(label);
        product.setFormat(format);
        product.setStorage(storage);
        product.setOrigin(origin);
        product.setDetail(detail);
        return product;
    }

}
