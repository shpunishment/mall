package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallCouponProduct;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/2 14:52
 */
public interface MallCouponProductService {

    void deleteByPrimaryKey(Integer id);

    void insertSelective(MallCouponProduct record);

    MallCouponProduct selectByPrimaryKey(Integer id);

    void updateByPrimaryKeySelective(MallCouponProduct record);

    void insertBatch(List<Integer> productIdList, Integer couponId);

    void updateBatch(List<Integer> newProductIdList, Integer couponId);

}
