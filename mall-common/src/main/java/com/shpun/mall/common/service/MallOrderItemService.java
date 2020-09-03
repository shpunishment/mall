package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallOrderItem;
import com.shpun.mall.common.model.vo.MallOrderItemVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:37
 */
public interface MallOrderItemService {

    void deleteByPrimaryKey(Integer orderItemId);

    void insertSelective(MallOrderItem record);

    MallOrderItem selectByPrimaryKey(Integer orderItemId);

    void updateByPrimaryKeySelective(MallOrderItem record);

    void insertBatch(List<MallOrderItem> orderItemList);

    List<MallOrderItemVo> getVoByOrderId(Integer orderId);

    List<MallOrderItem> getByOrderId(Integer orderId);

    List<MallOrderItemVo> getLimitVoListByOrderId(Integer orderId);

    List<MallOrderItemVo> getLimitVoListBySearch(Integer orderId, String productName);

}
