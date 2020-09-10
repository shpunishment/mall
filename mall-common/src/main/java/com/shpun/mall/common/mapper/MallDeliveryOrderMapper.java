package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallDeliveryOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallDeliveryOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(MallDeliveryOrder record);

    MallDeliveryOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MallDeliveryOrder record);

    List<MallDeliveryOrder> getListByFilter(@Param("deliveryId") Integer deliveryId, @Param("status") Integer status);

}