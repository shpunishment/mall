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
     *
     * 限时抢购商品id：
     * 17,20,21,22,23
     * 31,32,33,34,35
     * 39,40,41,42,43
     * 45,48,51,55,56
     * 75,76,78,79.80
     * 81,83,84,85,86
     */
    @Test
    public void insert() {
        for (int i = 1; i <= 114; i+=6) {
            insertByFlashId(i);
        }
    }

    private void insertByFlashId(Integer flashId) {
        // 9:00
        MallFlashItem flashItem1 = buildMallFlashItem(flashId, 17, 20, new BigDecimal("3.98"), 3);
        flashItemService.insertSelective(flashItem1);
        MallFlashItem flashItem2 = buildMallFlashItem(flashId, 20, 20, new BigDecimal("7.9"), 3);
        flashItemService.insertSelective(flashItem2);
        MallFlashItem flashItem3 = buildMallFlashItem(flashId, 21, 20, new BigDecimal("4.98"), 3);
        flashItemService.insertSelective(flashItem3);
        MallFlashItem flashItem4 = buildMallFlashItem(flashId, 22, 20, new BigDecimal("3.8"), 3);
        flashItemService.insertSelective(flashItem4);
        MallFlashItem flashItem5 = buildMallFlashItem(flashId, 23, 20, new BigDecimal("3.98"), 3);
        flashItemService.insertSelective(flashItem5);

        // 11:00
        flashId ++;
        MallFlashItem flashItem6 = buildMallFlashItem(flashId, 31, 20, new BigDecimal("39.9"), 3);
        flashItemService.insertSelective(flashItem6);
        MallFlashItem flashItem7 = buildMallFlashItem(flashId, 32, 20, new BigDecimal("14.9"), 3);
        flashItemService.insertSelective(flashItem7);
        MallFlashItem flashItem8 = buildMallFlashItem(flashId, 33, 20, new BigDecimal("19.9"), 3);
        flashItemService.insertSelective(flashItem8);
        MallFlashItem flashItem9 = buildMallFlashItem(flashId, 34, 20, new BigDecimal("14.9"), 3);
        flashItemService.insertSelective(flashItem9);
        MallFlashItem flashItem10 = buildMallFlashItem(flashId, 35, 15, new BigDecimal("34.9"), 3);
        flashItemService.insertSelective(flashItem10);

        // 14:00
        flashId ++;
        MallFlashItem flashItem11 = buildMallFlashItem(flashId, 39, 20, new BigDecimal("7.9"), 3);
        flashItemService.insertSelective(flashItem11);
        MallFlashItem flashItem12 = buildMallFlashItem(flashId, 40, 20, new BigDecimal("59.9"), 1);
        flashItemService.insertSelective(flashItem12);
        MallFlashItem flashItem13 = buildMallFlashItem(flashId, 41, 20, new BigDecimal("15.9"), 3);
        flashItemService.insertSelective(flashItem13);
        MallFlashItem flashItem14 = buildMallFlashItem(flashId, 42, 20, new BigDecimal("8.9"), 3);
        flashItemService.insertSelective(flashItem14);
        MallFlashItem flashItem15 = buildMallFlashItem(flashId, 43, 20, new BigDecimal("12.9"), 3);
        flashItemService.insertSelective(flashItem15);

        // 16:00
        flashId ++;
        MallFlashItem flashItem16 = buildMallFlashItem(flashId, 45, 10, new BigDecimal("69.9"), 1);
        flashItemService.insertSelective(flashItem16);
        MallFlashItem flashItem17 = buildMallFlashItem(flashId, 48, 20, new BigDecimal("29.9"), 3);
        flashItemService.insertSelective(flashItem17);
        MallFlashItem flashItem18 = buildMallFlashItem(flashId, 51, 20, new BigDecimal("24.9"), 3);
        flashItemService.insertSelective(flashItem18);
        MallFlashItem flashItem19 = buildMallFlashItem(flashId, 55, 20, new BigDecimal("45.9"), 3);
        flashItemService.insertSelective(flashItem19);
        MallFlashItem flashItem20 = buildMallFlashItem(flashId, 56, 15, new BigDecimal("24.9"), 3);
        flashItemService.insertSelective(flashItem20);

        // 18:00
        flashId ++;
        MallFlashItem flashItem21 = buildMallFlashItem(flashId, 75, 10, new BigDecimal("85"), 1);
        flashItemService.insertSelective(flashItem21);
        MallFlashItem flashItem22 = buildMallFlashItem(flashId, 76, 20, new BigDecimal("12.9"), 3);
        flashItemService.insertSelective(flashItem22);
        MallFlashItem flashItem23 = buildMallFlashItem(flashId, 78, 20, new BigDecimal("108"), 1);
        flashItemService.insertSelective(flashItem23);
        MallFlashItem flashItem24 = buildMallFlashItem(flashId, 79, 20, new BigDecimal("189"), 1);
        flashItemService.insertSelective(flashItem24);
        MallFlashItem flashItem25 = buildMallFlashItem(flashId, 80, 15, new BigDecimal("69.9"), 3);
        flashItemService.insertSelective(flashItem25);

        // 20:00
        flashId ++;
        MallFlashItem flashItem26 = buildMallFlashItem(flashId, 81, 20, new BigDecimal("158"), 1);
        flashItemService.insertSelective(flashItem26);
        MallFlashItem flashItem27 = buildMallFlashItem(flashId, 83, 20, new BigDecimal("18.9"), 3);
        flashItemService.insertSelective(flashItem27);
        MallFlashItem flashItem28 = buildMallFlashItem(flashId, 84, 20, new BigDecimal("13.9"), 3);
        flashItemService.insertSelective(flashItem28);
        MallFlashItem flashItem29 = buildMallFlashItem(flashId, 85, 20, new BigDecimal("9.9"), 3);
        flashItemService.insertSelective(flashItem29);
        MallFlashItem flashItem30 = buildMallFlashItem(flashId, 86, 15, new BigDecimal("40.9"), 3);
        flashItemService.insertSelective(flashItem30);
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
