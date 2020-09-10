package com.shpun.mall.back.prod;

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

    /**
     * 造数据
     * todo 重新选择商品id
     */
    @Test
    public void insert() {
        for (int i = 1; i <= 132; i+=6) {
            insertByFlashId(i);
        }
    }

    private void insertByFlashId(Integer flashId) {
        MallFlashItem flashItem1 = buildMallFlashItem(flashId, 1, 20, new BigDecimal("7.99"), 2);
        MallFlashItem flashItem2 = buildMallFlashItem(flashId, 2, 10, new BigDecimal("5.99"), 1);
        flashItemService.insertSelective(flashItem1);
        flashItemService.insertSelective(flashItem2);

        MallFlashItem flashItem3 = buildMallFlashItem(flashId + 1, 1, 20, new BigDecimal("7.99"), 2);
        MallFlashItem flashItem4 = buildMallFlashItem(flashId + 1, 2, 10, new BigDecimal("5.99"), 1);
        flashItemService.insertSelective(flashItem3);
        flashItemService.insertSelective(flashItem4);

        MallFlashItem flashItem5 = buildMallFlashItem(flashId + 2, 1, 20, new BigDecimal("7.99"), 2);
        MallFlashItem flashItem6 = buildMallFlashItem(flashId + 2, 2, 10, new BigDecimal("5.99"), 1);
        flashItemService.insertSelective(flashItem5);
        flashItemService.insertSelective(flashItem6);

        MallFlashItem flashItem7 = buildMallFlashItem(flashId + 3, 1, 20, new BigDecimal("7.99"), 2);
        MallFlashItem flashItem8 = buildMallFlashItem(flashId + 3, 2, 10, new BigDecimal("5.99"), 1);
        flashItemService.insertSelective(flashItem7);
        flashItemService.insertSelective(flashItem8);

        MallFlashItem flashItem9 = buildMallFlashItem(flashId + 4, 1, 20, new BigDecimal("7.99"), 2);
        MallFlashItem flashItem10 = buildMallFlashItem(flashId + 4, 2, 10, new BigDecimal("5.99"), 1);
        flashItemService.insertSelective(flashItem9);
        flashItemService.insertSelective(flashItem10);

        MallFlashItem flashItem11 = buildMallFlashItem(flashId + 5, 1, 20, new BigDecimal("7.99"), 2);
        MallFlashItem flashItem12 = buildMallFlashItem(flashId + 6, 2, 10, new BigDecimal("5.99"), 1);
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

}
