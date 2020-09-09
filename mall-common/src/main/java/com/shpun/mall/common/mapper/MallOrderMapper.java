package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallOrder;
import com.shpun.mall.common.model.vo.MallOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallOrderMapper {

    int deleteByPrimaryKey(Integer orderId);

    int insertSelective(MallOrder record);

    MallOrder selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(MallOrder record);

    List<MallOrder> getByUserId(Integer userId);

    List<MallOrderVo> getVoListByFilter(@Param("userId") Integer userId, @Param("status") Integer status);

    MallOrderVo getDetailVo(@Param("userId")Integer userId, @Param("orderId") Integer orderId);

    List<MallOrderVo> getVoByProductName(@Param("userId") Integer userId, @Param("productName") String productName);

    List<MallOrder> getList(Integer status);

    MallOrder getByOrderNumber(String orderNumber);

}