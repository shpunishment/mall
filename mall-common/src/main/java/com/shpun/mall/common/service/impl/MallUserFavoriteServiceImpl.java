package com.shpun.mall.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.aop.RedisCache;
import com.shpun.mall.common.mapper.MallUserFavoriteMapper;
import com.shpun.mall.common.model.MallUserFavorite;
import com.shpun.mall.common.model.vo.MallProductVo;
import com.shpun.mall.common.service.MallUserFavoriteService;
import com.shpun.mall.common.service.RedisService;
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

    @Autowired
    private RedisService redisService;

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

    @Override
    public List<MallProductVo> getVoListByFavorite(Integer userId) {
        return userFavoriteMapper.getVoListByFavorite(userId);
    }

    @RedisCache
    @Override
    public PageInfo<MallProductVo> getVoPageByFavorite(Integer userId, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);
        return new PageInfo<>(this.getVoListByFavorite(userId));
    }

    @Override
    public void deleteCache(Integer userId) {
        redisService.deleteByPrefix(MallUserFavoriteServiceImpl.class, "getVoPageByFavorite", userId);
    }
}
