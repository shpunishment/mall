package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallCouponProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallCouponProductMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(MallCouponProduct record);

    MallCouponProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MallCouponProduct record);

    void insertBatch(@Param("couponProductList") List<MallCouponProduct> couponProductList);

    List<Integer> getProductIdByCouponId(Integer couponId);

    void deleteBatch(@Param("productIdList") List<Integer> productIdList, @Param("couponId") Integer couponId);

}