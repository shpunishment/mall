package com.shpun.mall.common.service;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallUserFavorite;
import com.shpun.mall.common.model.vo.MallProductVo;

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

    List<MallProductVo> getVoListByFavorite(Integer userId);

    PageInfo<MallProductVo> getVoPageByFavorite(Integer userId, Integer offset, Integer limit);

    /**
     * 新增删除，根据用户id删除缓存
     * @param userId
     */
    void deleteCache(Integer userId);

}
