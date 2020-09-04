package com.shpun.mall.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.aop.RedisCache;
import com.shpun.mall.common.mapper.MallUserFootprintMapper;
import com.shpun.mall.common.model.MallUserFootprint;
import com.shpun.mall.common.model.vo.MallProductVo;
import com.shpun.mall.common.service.MallUserFootprintService;
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
@Service("mallUserFootprintService")
public class MallUserFootprintServiceImpl implements MallUserFootprintService {

    @Autowired
    private MallUserFootprintMapper userFootprintMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public void deleteByPrimaryKey(Integer footprintId) {
        userFootprintMapper.deleteByPrimaryKey(footprintId);
    }

    @Override
    public void insertSelective(MallUserFootprint record) {
        record.setViewTime(new Date());
        userFootprintMapper.insertSelective(record);
    }

    @Override
    public MallUserFootprint selectByPrimaryKey(Integer footprintId) {
        return userFootprintMapper.selectByPrimaryKey(footprintId);
    }

    @Override
    public void updateByPrimaryKeySelective(MallUserFootprint record) {
        userFootprintMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<MallUserFootprint> getByUserId(Integer userId) {
        return userFootprintMapper.getByUserId(userId);
    }

    @Override
    public List<MallProductVo> getVoListByFootprint(Integer userId) {
        return userFootprintMapper.getVoListByFootprint(userId);
    }

    @RedisCache
    @Override
    public PageInfo<MallProductVo> getVoPageByFootprint(Integer userId, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);
        return new PageInfo<>(this.getVoListByFootprint(userId));
    }

    @Override
    public void deleteCache(Integer userId) {
        redisService.deleteByPrefix(MallUserFootprintServiceImpl.class, "getVoPageByFootprint", userId);
    }
}
