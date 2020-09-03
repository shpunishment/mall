package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallOrderItem;
import com.shpun.mall.common.model.vo.MallOrderItemVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallOrderItemMapper {

    int deleteByPrimaryKey(Integer orderItemId);

    int insertSelective(MallOrderItem record);

    MallOrderItem selectByPrimaryKey(Integer orderItemId);

    int updateByPrimaryKeySelective(MallOrderItem record);

    void insertBatch(@Param("orderItemList") List<MallOrderItem> orderItemList);

    List<MallOrderItemVo> getVoByOrderId(Integer orderId);

    List<MallOrderItem> getByOrderId(Integer orderId);

    List<MallOrderItemVo> getLimitVoListByOrderId(@Param("orderId") Integer orderId,
                                                  @Param("limit") Integer limit);

    List<MallOrderItemVo> getLimitVoListBySearch(@Param("orderId") Integer orderId,
                                                 @Param("productName") String productName);

}