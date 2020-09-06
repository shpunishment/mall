package com.shpun.mall.back;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.model.MallFlashItem;
import com.shpun.mall.common.service.MallFlashItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 11:10
 */
@SpringBootTest
public class MallFlashItemServiceTest {

    @Autowired
    private MallFlashItemService flashItemService;

    @Test
    public void insert() {
        // 2020-08-29 18:00:00
        MallFlashItem flashItem1 = buildMallFlashItem(7, 1, 20, new BigDecimal("7.99"), 2);
        MallFlashItem flashItem2 = buildMallFlashItem(7, 2, 10, new BigDecimal("5.99"), 1);
        flashItemService.insertSelective(flashItem1);
        flashItemService.insertSelective(flashItem2);

        // 2020-08-30 09:00:00
        MallFlashItem flashItem3 = buildMallFlashItem(8, 1, 20, new BigDecimal("7.99"), 2);
        MallFlashItem flashItem4 = buildMallFlashItem(8, 2, 10, new BigDecimal("5.99"), 1);
        flashItemService.insertSelective(flashItem3);
        flashItemService.insertSelective(flashItem4);

        // 2020-08-30 11:00:00
        MallFlashItem flashItem5 = buildMallFlashItem(9, 1, 20, new BigDecimal("7.99"), 2);
        MallFlashItem flashItem6 = buildMallFlashItem(9, 2, 10, new BigDecimal("5.99"), 1);
        flashItemService.insertSelective(flashItem5);
        flashItemService.insertSelective(flashItem6);

        // 2020-08-30 14:00:00
        MallFlashItem flashItem7 = buildMallFlashItem(10, 1, 20, new BigDecimal("7.99"), 2);
        MallFlashItem flashItem8 = buildMallFlashItem(10, 2, 10, new BigDecimal("5.99"), 1);
        flashItemService.insertSelective(flashItem7);
        flashItemService.insertSelective(flashItem8);

        // 2020-08-30 16:00:00
        MallFlashItem flashItem9 = buildMallFlashItem(11, 1, 20, new BigDecimal("7.99"), 2);
        MallFlashItem flashItem10 = buildMallFlashItem(11, 2, 10, new BigDecimal("5.99"), 1);
        flashItemService.insertSelective(flashItem9);
        flashItemService.insertSelective(flashItem10);

        // 2020-08-30 18:00:00
        MallFlashItem flashItem11 = buildMallFlashItem(12, 1, 20, new BigDecimal("7.99"), 2);
        MallFlashItem flashItem12 = buildMallFlashItem(12, 2, 10, new BigDecimal("5.99"), 1);
        flashItemService.insertSelective(flashItem11);
        flashItemService.insertSelective(flashItem12);

    }

    private MallFlashItem buildMallFlashItem(Integer flashId, Integer productId, Integer stock, BigDecimal price, Integer limit) {
        MallFlashItem flashItem = new MallFlashItem();
        flashItem.setCreateId(Const.ADMIN_ID);
        flashItem.setFlashId(flashId);
        flashItem.setProductId(productId);
        flashItem.setStock(stock);
        flashItem.setPrice(price);
        flashItem.setLimit(limit);
        return flashItem;
    }

    @Test
    public void getFlashByProductId() {
        BigDecimal stock = new BigDecimal(18);
        BigDecimal sales = new BigDecimal(2);
        BigDecimal total = stock.add(sales);
        BigDecimal remainStockRatio = stock.divide(total, 2, RoundingMode.HALF_UP);
        System.out.println(remainStockRatio.multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString());
    }

}
