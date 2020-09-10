package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.enums.MallDeliveryOrderStatusEnums;
import com.shpun.mall.common.enums.MallDeliveryStatusEnums;
import com.shpun.mall.common.enums.MallOrderStatusEnums;
import com.shpun.mall.common.mapper.MallDeliveryMapper;
import com.shpun.mall.common.model.MallDelivery;
import com.shpun.mall.common.model.MallDeliveryOrder;
import com.shpun.mall.common.model.MallOrder;
import com.shpun.mall.common.service.MallDeliveryOrderService;
import com.shpun.mall.common.service.MallDeliveryService;
import com.shpun.mall.common.service.MallOrderService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/10 15:54
 */
@Service("mallDeliveryService")
public class MallDeliveryServiceImpl implements MallDeliveryService {

    @Autowired
    private MallDeliveryMapper deliveryMapper;

    @Autowired
    private MallDeliveryOrderService deliveryOrderService;

    @Autowired
    private MallOrderService orderService;

    @Override
    public void deleteByPrimaryKey(Integer deliveryId) {
        deliveryMapper.deleteByPrimaryKey(deliveryId);
    }

    @Override
    public void insertSelective(MallDelivery record) {
        record.setCreateTime(new Date());
        deliveryMapper.insertSelective(record);
    }

    @Override
    public MallDelivery selectByPrimaryKey(Integer deliveryId) {
        return deliveryMapper.selectByPrimaryKey(deliveryId);
    }

    @Override
    public void updateByPrimaryKeySelective(MallDelivery record) {
        record.setUpdateTime(new Date());
        deliveryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer getIdleDeliveryId() {
        return deliveryMapper.getIdleDeliveryId();
    }

    @Override
    public List<Integer> getNeed2DeliveryIdList() {
        return deliveryMapper.getNeed2DeliveryIdList(Const.DEFAULT_NEED_2_DELIVERY_AMOUNT);
    }

    @Override
    public List<Integer> getDeliveringIdList() {
        return deliveryMapper.getDeliveringIdList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Integer> wait2Receive(Integer deliveryId) {
        MallDelivery delivery = new MallDelivery();
        delivery.setDeliveryId(deliveryId);
        delivery.setStatus(MallDeliveryStatusEnums.DELIVERING.getValue());
        this.updateByPrimaryKeySelective(delivery);

        // 获取该配送员待配送的订单，将订单都更新为待收货
        List<MallDeliveryOrder> deliveryOrderList = deliveryOrderService.getListByFilter(deliveryId, MallDeliveryOrderStatusEnums.WAIT2DELIVERY.getValue());
        deliveryOrderList.forEach(deliveryOrder -> {
            deliveryOrder.setStatus(MallDeliveryOrderStatusEnums.DELIVERING.getValue());
            deliveryOrderService.updateByPrimaryKeySelective(deliveryOrder);

            orderService.wait2Receive(deliveryOrder.getOrderId(), deliveryId);
        });
        return deliveryOrderList.stream().map(MallDeliveryOrder::getOrderId).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Integer> receiveSuccess(Integer deliveryId) {
        MallDelivery delivery = new MallDelivery();
        delivery.setDeliveryId(deliveryId);
        delivery.setStatus(MallDeliveryStatusEnums.WAIT2DELIVERY.getValue());
        this.updateByPrimaryKeySelective(delivery);

        // 获取该配送员配送中的订单，将订单都更新为已收货
        List<MallDeliveryOrder> deliveryOrderList = deliveryOrderService.getListByFilter(deliveryId, MallDeliveryOrderStatusEnums.DELIVERING.getValue());
        deliveryOrderList.forEach(deliveryOrder -> {
            deliveryOrder.setStatus(MallDeliveryOrderStatusEnums.COMPLETED.getValue());
            deliveryOrder.setReceiveTime(new Date());
            deliveryOrderService.updateByPrimaryKeySelective(deliveryOrder);

            orderService.receiveSuccess(deliveryOrder.getOrderId());
        });
        return deliveryOrderList.stream().map(MallDeliveryOrder::getOrderId).collect(Collectors.toList());
    }
}
