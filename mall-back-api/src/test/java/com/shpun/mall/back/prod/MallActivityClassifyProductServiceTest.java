package com.shpun.mall.back.prod;

import com.shpun.mall.common.model.MallActivityClassifyProduct;
import com.shpun.mall.common.service.MallActivityClassifyProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/6 16:09
 */
@SpringBootTest
public class MallActivityClassifyProductServiceTest {

    @Autowired
    private MallActivityClassifyProductService activityClassifyProductService;

    // todo 活动分类商品
    @Test
    public void insert() {
        MallActivityClassifyProduct activityClassifyProduct1 = buildMallActivityClassifyProduct(1, 1);
        activityClassifyProductService.insertSelective(activityClassifyProduct1);

        MallActivityClassifyProduct activityClassifyProduct2 = buildMallActivityClassifyProduct(1, 2);
        activityClassifyProductService.insertSelective(activityClassifyProduct2);

        MallActivityClassifyProduct activityClassifyProduct3 = buildMallActivityClassifyProduct(2, 1);
        activityClassifyProductService.insertSelective(activityClassifyProduct3);

        MallActivityClassifyProduct activityClassifyProduct4 = buildMallActivityClassifyProduct(2, 2);
        activityClassifyProductService.insertSelective(activityClassifyProduct4);

        MallActivityClassifyProduct activityClassifyProduct5 = buildMallActivityClassifyProduct(5, 1);
        activityClassifyProductService.insertSelective(activityClassifyProduct5);

        MallActivityClassifyProduct activityClassifyProduct6 = buildMallActivityClassifyProduct(5, 2);
        activityClassifyProductService.insertSelective(activityClassifyProduct6);

    }

    private MallActivityClassifyProduct buildMallActivityClassifyProduct(Integer classifyId, Integer productId) {
        MallActivityClassifyProduct activityClassifyProduct = new MallActivityClassifyProduct();
        activityClassifyProduct.setClassifyId(classifyId);
        activityClassifyProduct.setProductId(productId);
        return activityClassifyProduct;
    }

}
