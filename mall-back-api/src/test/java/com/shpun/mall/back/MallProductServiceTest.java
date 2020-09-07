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

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 11:10
 */
@SpringBootTest
public class MallProductServiceTest {

    @Autowired
    private MallProductService productService;

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

}
