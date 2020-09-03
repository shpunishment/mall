package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.mapper.MallUserFavoriteMapper;
import com.shpun.mall.common.model.MallUserFavorite;
import com.shpun.mall.common.service.MallUserFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:41
 */
@Service("mallUserFavoriteService")
public class MallUserFavoriteServiceImpl implements MallUserFavoriteService {

    @Autowired
    private MallUserFavoriteMapper userFavoriteMapper;

    @Override
    public void deleteByPrimaryKey(Integer favoriteId) {
        userFavoriteMapper.deleteByPrimaryKey(favoriteId);
    }

    @Override
    public void insertSelective(MallUserFavorite record) {
        record.setFavoriteTime(new Date());
        userFavoriteMapper.insertSelective(record);
    }

    @Override
    public MallUserFavorite selectByPrimaryKey(Integer favoriteId) {
        return userFavoriteMapper.selectByPrimaryKey(favoriteId);
    }

    @Override
    public void updateByPrimaryKeySelective(MallUserFavorite record) {
        userFavoriteMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<MallUserFavorite> getByUserId(Integer userId) {
        return userFavoriteMapper.getByUserId(userId);
    }

    @Override
    public Integer isFavorite(Integer userId, Integer favoriteId) {
        return userFavoriteMapper.isFavorite(userId, favoriteId);
    }
}
