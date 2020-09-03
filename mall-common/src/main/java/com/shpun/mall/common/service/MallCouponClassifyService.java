package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallCouponClassify;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/2 14:52
 */
public interface MallCouponClassifyService {

    void deleteByPrimaryKey(Integer id);

    void insertSelective(MallCouponClassify record);

    MallCouponClassify selectByPrimaryKey(Integer id);

    void updateByPrimaryKeySelective(MallCouponClassify record);

    void insertBatch(List<Integer> classifyIdList, Integer couponId);

    void updateBatch(List<Integer> newClassifyIdList, Integer couponId);

}
