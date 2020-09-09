package com.shpun.mall.back.test;

import com.shpun.mall.common.model.MallUserFavorite;
import com.shpun.mall.common.service.MallUserFavoriteService;
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
public class MallUserFavoriteServiceTest {

    @Autowired
    private MallUserFavoriteService userFavoriteService;

    @Test
    public void insert() {
        MallUserFavorite userFavorite = buildMallUserFavorite(1, 1);
        userFavoriteService.insertSelective(userFavorite);
    }

    private MallUserFavorite buildMallUserFavorite(Integer userId, Integer productId) {
        MallUserFavorite userFavorite = new MallUserFavorite();
        userFavorite.setUserId(userId);
        userFavorite.setProductId(productId);
        return userFavorite;
    }

    @Test
    public void delete() {
        userFavoriteService.deleteByPrimaryKey(1);
    }

    @Test
    public void getByUserId() {
        List<MallUserFavorite> userFavoriteList = userFavoriteService.getByUserId(1);

        System.out.println();
    }

}
