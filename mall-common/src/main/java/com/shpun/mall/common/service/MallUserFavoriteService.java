package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallUserFavorite;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:37
 */
public interface MallUserFavoriteService {

    void deleteByPrimaryKey(Integer favoriteId);

    void insertSelective(MallUserFavorite record);

    MallUserFavorite selectByPrimaryKey(Integer favoriteId);

    void updateByPrimaryKeySelective(MallUserFavorite record);

    List<MallUserFavorite> getByUserId(Integer userId);

    Integer isFavorite(Integer userId, Integer favoriteId);

}
