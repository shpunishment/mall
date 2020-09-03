package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallUserAddress;
import com.shpun.mall.common.model.vo.MallUserAddressVo;

import java.util.List;

public interface MallUserAddressMapper {

    int deleteByPrimaryKey(Integer addressId);

    int insertSelective(MallUserAddress record);

    MallUserAddress selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(MallUserAddress record);

    List<MallUserAddress> getByUserId(Integer userId);

    List<MallUserAddressVo> getVoListByUserId(Integer userId);

    MallUserAddressVo getVoByAddressId(Integer addressId);

}