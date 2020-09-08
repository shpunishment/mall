package com.shpun.mall.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.aop.RedisCache;
import com.shpun.mall.common.exception.MallError;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.mapper.MallUserAddressMapper;
import com.shpun.mall.common.model.MallUserAddress;
import com.shpun.mall.common.model.vo.MallUserAddressVo;
import com.shpun.mall.common.service.MallUserAddressService;
import com.shpun.mall.common.service.RedisService;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:41
 */
@Service("mallUserAddressService")
public class MallUserAddressServiceImpl implements MallUserAddressService {

    @Autowired
    private MallUserAddressMapper userAddressMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public void deleteByPrimaryKey(Integer addressId) {
        userAddressMapper.deleteByPrimaryKey(addressId);
    }

    @Override
    public void insertSelective(MallUserAddress record) {
        record.setCreateTime(new Date());
        userAddressMapper.insertSelective(record);
    }

    @Override
    public MallUserAddress selectByPrimaryKey(Integer addressId) {
        return userAddressMapper.selectByPrimaryKey(addressId);
    }

    @Override
    public void updateByPrimaryKeySelective(MallUserAddress record) {
        MallUserAddress isExist = userAddressMapper.getByUserIdAndAddressId(record.getUserId(), record.getAddressId());
        if (isExist == null) {
            throw new MallException(MallError.MallErrorEnum.INTERNAL_SYSTEM_ERROR);
        }

        record.setUpdateTime(new Date());
        userAddressMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<MallUserAddress> getByUserId(Integer userId) {
        return userAddressMapper.getByUserId(userId);
    }

    @Override
    public List<MallUserAddressVo> getVoListByUserId(Integer userId) {
        return userAddressMapper.getVoListByUserId(userId);
    }

    @RedisCache
    @Override
    public PageInfo<MallUserAddressVo> getVoPageByUserId(Integer userId, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);
        return new PageInfo<>(this.getVoListByUserId(userId));
    }

    @Override
    public MallUserAddressVo getVoByAddressId(Integer addressId) {
        return userAddressMapper.getVoByAddressId(addressId);
    }

    @Override
    public void deleteCache(Integer userId) {
        redisService.deleteByPrefix(MallUserAddressServiceImpl.class, "getVoPageByUserId", userId);
    }

    @Override
    public MallUserAddress getByUserIdAndAddressId(Integer userId, Integer addressId) {
        return userAddressMapper.getByUserIdAndAddressId(userId, addressId);
    }
}
