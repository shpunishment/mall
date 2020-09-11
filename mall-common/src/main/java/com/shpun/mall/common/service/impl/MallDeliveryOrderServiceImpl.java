package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.mapper.MallDeliveryOrderMapper;
import com.shpun.mall.common.model.MallDeliveryOrder;
import com.shpun.mall.common.service.MallDeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/10 15:55
 */
@Service("mallDeliveryOrderService")
public class MallDeliveryOrderServiceImpl implements MallDeliveryOrderService {

    @Autowired
    private MallDeliveryOrderMapper deliveryOrderMapper;

    @Override
    public void deleteByPrimaryKey(Integer id) {
        deliveryOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(MallDeliveryOrder record) {
        record.setCreateTime(new Date());
        deliveryOrderMapper.insertSelective(record);
    }

    @Override
    public MallDeliveryOrder selectByPrimaryKey(Integer id) {
        return deliveryOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKeySelective(MallDeliveryOrder record) {
        deliveryOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<MallDeliveryOrder> getListByFilter(Integer deliveryId, Integer status) {
        return deliveryOrderMapper.getListByFilter(deliveryId, status);
    }

    @Override
    public Integer getByOrderId(Integer orderId) {
        return deliveryOrderMapper.getByOrderId(orderId);
    }
}
