package com.shpun.mall.common.service;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallUserCoupon;
import com.shpun.mall.common.model.vo.MallUserCouponVo;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/2 14:53
 */
public interface MallUserCouponService {

    void deleteByPrimaryKey(Integer id);

    void insertSelective(MallUserCoupon record);

    MallUserCoupon selectByPrimaryKey(Integer id);

    void updateByPrimaryKeySelective(MallUserCoupon record);

    List<MallUserCouponVo> getVoListByFilter(Integer userId, Integer status);

    PageInfo<MallUserCouponVo> getVoPageByFilter(Integer userId, Integer status, Integer offset, Integer limit);

    List<Integer> getAvailableCouponIdList(Integer userId);

}
