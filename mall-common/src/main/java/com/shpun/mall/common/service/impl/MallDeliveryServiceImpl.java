package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.mapper.MallDeliveryMapper;
import com.shpun.mall.common.model.MallDelivery;
import com.shpun.mall.common.service.MallDeliveryService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/10 15:54
 */
@Service("mallDeliveryService")
public class MallDeliveryServiceImpl implements MallDeliveryService {

    @Autowired
    private MallDeliveryMapper deliveryMapper;

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
}
