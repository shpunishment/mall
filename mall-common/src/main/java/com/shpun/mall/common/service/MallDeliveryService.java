package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallDelivery;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/10 15:54
 */
public interface MallDeliveryService {

    void deleteByPrimaryKey(Integer deliveryId);

    void insertSelective(MallDelivery record);

    MallDelivery selectByPrimaryKey(Integer deliveryId);

    void updateByPrimaryKeySelective(MallDelivery record);

    Integer getIdleDeliveryId();

    List<Integer> getNeed2DeliveryIdList();

    List<Integer> getDeliveringIdList();

    List<Integer> wait2Receive(Integer deliveryId);

    List<Integer> receiveSuccess(Integer deliveryId);

}
