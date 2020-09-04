package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.aop.RedisCache;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.mapper.MallOrderItemMapper;
import com.shpun.mall.common.model.MallOrderItem;
import com.shpun.mall.common.model.vo.MallOrderItemVo;
import com.shpun.mall.common.service.MallOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:41
 */
@Service("mallOrderItemService")
public class MallOrderItemServiceImpl implements MallOrderItemService {

    @Autowired
    private MallOrderItemMapper orderItemMapper;

    @Override
    public void deleteByPrimaryKey(Integer orderItemId) {
        orderItemMapper.deleteByPrimaryKey(orderItemId);
    }

    @Override
    public void insertSelective(MallOrderItem record) {
        orderItemMapper.insertSelective(record);
    }

    @Override
    public MallOrderItem selectByPrimaryKey(Integer orderItemId) {
        return orderItemMapper.selectByPrimaryKey(orderItemId);
    }

    @Override
    public void updateByPrimaryKeySelective(MallOrderItem record) {
        orderItemMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void insertBatch(List<MallOrderItem> orderItemList) {
        orderItemMapper.insertBatch(orderItemList);
    }

    @RedisCache
    @Override
    public List<MallOrderItemVo> getVoByOrderId(Integer orderId) {
        return orderItemMapper.getVoByOrderId(orderId);
    }

    @Override
    public List<MallOrderItem> getByOrderId(Integer orderId) {
        return orderItemMapper.getByOrderId(orderId);
    }

    @RedisCache
    @Override
    public List<MallOrderItemVo> getLimitVoListByOrderId(Integer orderId) {
        return orderItemMapper.getLimitVoListByOrderId(orderId, Const.DEFAULT_LIMIT_ORDER_ITEM);
    }

    @RedisCache
    @Override
    public List<MallOrderItemVo> getLimitVoListBySearch(Integer orderId, String productName) {
        return orderItemMapper.getLimitVoListBySearch(orderId, productName);
    }
}
