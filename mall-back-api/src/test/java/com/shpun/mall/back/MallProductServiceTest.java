package com.shpun.mall.back;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.mapper.MallFlashItemMapper;
import com.shpun.mall.common.model.MallFlashItem;
import com.shpun.mall.common.model.MallProduct;
import com.shpun.mall.common.service.MallProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
     * 测试
     */
    @Test
    public void insert(){
        MallProduct product1 = buildMallProduct("泰国进口龙眼", "皮薄 脆嫩 甜蜜", 1, new BigDecimal("9.9"),
                new BigDecimal("8.98"),100, 10, "0-4度冷藏", "500g/份", "冷藏（0-4）度","泰国",2);
        MallProduct product2 = buildMallProduct("黄花梨", "清甜多汁 肉脆爽口", 1, new BigDecimal("6.98"),
                new BigDecimal("6.98"),80, 20, "", "750g-800g/份", "0-4度","建宁",2);
        MallProduct product3 = buildMallProduct("芒果", "清甜多汁 肉脆爽口", 1, new BigDecimal("8.99"),
                new BigDecimal("5.99"),90, 10, "", "750g-800g/份", "0-4度","台湾",2);
        MallProduct product4 = buildMallProduct("芒果-1", "清甜多汁 肉脆爽口", 1, new BigDecimal("8.99"),
                new BigDecimal("5.99"),90, 10, "", "750g-800g/份", "0-4度","台湾",2);

        List<Integer> product1ClassifyIdList = new ArrayList<>();
        // 热带
        product1ClassifyIdList.add(4);
        // 进口
        product1ClassifyIdList.add(8);

        List<Integer> product2ClassifyIdList = new ArrayList<>();
        // 果切拼盘
        product2ClassifyIdList.add(5);

        List<Integer> product3ClassifyIdList = new ArrayList<>();
        // 热带
        product3ClassifyIdList.add(4);
        // 台湾水果
        product3ClassifyIdList.add(6);

        productService.insertWithClassifyIdList(product1, product1ClassifyIdList);
        productService.insertWithClassifyIdList(product2, product2ClassifyIdList);
        productService.insertWithClassifyIdList(product3, product3ClassifyIdList);

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

    @Test
    public void update() {
        MallProduct product1 = buildUpdateMallProduct(1, 1);
        productService.updateByPrimaryKeySelective(product1);

        MallProduct product2 = buildUpdateMallProduct(2, 2);
        productService.updateByPrimaryKeySelective(product2);

        MallProduct product3 = buildUpdateMallProduct(3, 1);
        productService.updateByPrimaryKeySelective(product3);
    }

    private MallProduct buildUpdateMallProduct(Integer productId, Integer brandId) {
        MallProduct product = new MallProduct();
        product.setProductId(productId);
        product.setBrandId(brandId);
        return product;
    }

    /**
     * 造数据
     */
    /**
     * 水果
     */
    @Test
    public void insert2() {
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

}
