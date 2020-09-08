package com.shpun.mall.back;

import com.shpun.mall.common.enums.MallUserSearchHistoryTypeEnums;
import com.shpun.mall.common.model.MallUserSearchHistory;
import com.shpun.mall.common.service.MallUserSearchHistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 11:10
 */
@SpringBootTest
public class MallUserSearchHistoryServiceTest {

    @Autowired
    private MallUserSearchHistoryService userSearchHistoryService;

    @Test
    public void insertProduct() {
        userSearchHistoryService.insertOrUpdate(1, "菜1", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        userSearchHistoryService.insertOrUpdate(1, "菜2", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        userSearchHistoryService.insertOrUpdate(1, "菜3", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        userSearchHistoryService.insertOrUpdate(1, "菜4", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        userSearchHistoryService.insertOrUpdate(1, "菜5", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());

        userSearchHistoryService.insertOrUpdate(1, "菜6", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        userSearchHistoryService.insertOrUpdate(1, "菜7", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        userSearchHistoryService.insertOrUpdate(1, "菜8", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        userSearchHistoryService.insertOrUpdate(1, "菜9", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        userSearchHistoryService.insertOrUpdate(1, "菜10", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());

        userSearchHistoryService.insertOrUpdate(1, "菜11", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        userSearchHistoryService.insertOrUpdate(1, "菜12", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());

        userSearchHistoryService.insertOrUpdate(2, "菜1", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        userSearchHistoryService.insertOrUpdate(2, "菜2", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        userSearchHistoryService.insertOrUpdate(2, "菜3", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        userSearchHistoryService.insertOrUpdate(2, "菜4", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        userSearchHistoryService.insertOrUpdate(2, "菜5", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());

        userSearchHistoryService.insertOrUpdate(3, "菜1", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        userSearchHistoryService.insertOrUpdate(3, "菜2", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        userSearchHistoryService.insertOrUpdate(3, "菜8", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        userSearchHistoryService.insertOrUpdate(3, "菜9", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        userSearchHistoryService.insertOrUpdate(3, "菜10", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());

        userSearchHistoryService.insertOrUpdate(4, "菜1", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        userSearchHistoryService.insertOrUpdate(4, "菜12", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());

    }

    @Test
    public void insertOrder() {
        userSearchHistoryService.insertOrUpdate(1, "菜1", MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
    }


    @Test
    public void getByUserId() {
        List<MallUserSearchHistory> userSearchHistoryList = userSearchHistoryService.getByUserId(1, MallUserSearchHistoryTypeEnums.PRODUCT.getValue());

        System.out.println();
    }

    @Test
    public void getTop10ByUserId() {
        List<String> userSearchHistoryList = userSearchHistoryService.getTop10ByUserId(1, MallUserSearchHistoryTypeEnums.PRODUCT.getValue());

        System.out.println();
    }

    @Test
    public void deleteByProduct() {
        userSearchHistoryService.deleteByUserId(1, MallUserSearchHistoryTypeEnums.PRODUCT.getValue());
        System.out.println();
    }

}
