package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallDeliveryOrder;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/10 15:54
 */
public interface MallDeliveryOrderService {

    void deleteByPrimaryKey(Integer id);

    void insertSelective(MallDeliveryOrder record);

    MallDeliveryOrder selectByPrimaryKey(Integer id);

    void updateByPrimaryKeySelective(MallDeliveryOrder record);

    List<MallDeliveryOrder> getListByFilter(Integer deliveryId, Integer status);

    Integer getByOrderId(Integer orderId);

}
