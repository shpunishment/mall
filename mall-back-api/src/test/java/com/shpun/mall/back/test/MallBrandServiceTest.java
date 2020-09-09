package com.shpun.mall.back.test;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.model.MallBrand;
import com.shpun.mall.common.service.MallBrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 11:10
 */
@SpringBootTest
public class MallBrandServiceTest {

    @Autowired
    private MallBrandService brandService;

    @Test
    public void insert() {
        MallBrand brand1 = buildMallBrand("品牌1");
        brandService.insertSelective(brand1);

        MallBrand brand2 = buildMallBrand("品牌2");
        brandService.insertSelective(brand2);
    }

    private MallBrand buildMallBrand(String brandName) {
        MallBrand brand = new MallBrand();
        brand.setCreateId(Const.ADMIN_ID);
        brand.setBrandName(brandName);
        return brand;
    }

}
