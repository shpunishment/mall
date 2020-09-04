package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.aop.RedisCache;
import com.shpun.mall.common.mapper.MallUserMapper;
import com.shpun.mall.common.model.MallUser;
import com.shpun.mall.common.model.vo.MallUserVo;
import com.shpun.mall.common.service.MallUserService;
import com.shpun.mall.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:41
 */
@Service("mallUserService")
public class MallUserServiceImpl implements MallUserService {

    @Autowired
    private MallUserMapper userMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public void deleteByPrimaryKey(Integer userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public void insertSelective(MallUser record) {
        record.setCreateTime(new Date());
        userMapper.insertSelective(record);
    }

    @Override
    public MallUser selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void updateByPrimaryKeySelective(MallUser record) {
        record.setUpdateTime(new Date());
        userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public MallUser getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @RedisCache
    @Override
    public MallUserVo getVoByUserId(Integer userId) {
        return userMapper.getVoByUserId(userId);
    }

    @Override
    public boolean isExist(String username) {
        return userMapper.isExist(username);
    }

    @Override
    public void deleteCache(Integer userId) {
        redisService.deleteByPrefix(MallUserServiceImpl.class, "getVoByUserId", userId);
    }
}
