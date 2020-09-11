package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallDelivery;

import java.util.List;

public interface MallDeliveryMapper {

    int deleteByPrimaryKey(Integer deliveryId);

    int insertSelective(MallDelivery record);

    MallDelivery selectByPrimaryKey(Integer deliveryId);

    int updateByPrimaryKeySelective(MallDelivery record);

    Integer getIdleDeliveryId(Integer status);

    List<Integer> getNeed2DeliveryIdList(Integer maxOrderAmount);

    List<Integer> getDeliveringIdList();

}