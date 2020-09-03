package com.shpun.mall.common.service;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallUserAddress;
import com.shpun.mall.common.model.vo.MallUserAddressVo;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:37
 */
public interface MallUserAddressService {

    void deleteByPrimaryKey(Integer addressId);

    void insertSelective(MallUserAddress record);

    MallUserAddress selectByPrimaryKey(Integer addressId);

    void updateByPrimaryKeySelective(MallUserAddress record);

    List<MallUserAddress> getByUserId(Integer userId);

    List<MallUserAddressVo> getVoListByUserId(Integer userId);

    PageInfo<MallUserAddressVo> getVoPageByUserId(Integer userId, Integer offset, Integer limit);

    MallUserAddressVo getVoByAddressId(Integer addressId);

    void deleteCache(Integer userId);

}
