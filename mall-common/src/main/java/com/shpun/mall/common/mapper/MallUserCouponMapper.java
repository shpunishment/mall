package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallUserCoupon;
import com.shpun.mall.common.model.vo.MallUserCouponVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallUserCouponMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(MallUserCoupon record);

    MallUserCoupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MallUserCoupon record);

    List<MallUserCouponVo> getVoListByFilter(@Param("userId") Integer userId,
                                             @Param("status") Integer status);

    List<Integer> getReceivedCouponId(@Param("userId") Integer userId,
                                      @Param("couponIdList") List<Integer> couponIdList);

    MallUserCoupon getByUserIdAndCouponId(@Param("userId") Integer userId, @Param("couponId") Integer couponId);

    Integer getTodayUseCount(Integer userId);

    MallUserCoupon canUse(@Param("userId") Integer userId, @Param("couponId") Integer couponId);

    List<MallUserCouponVo> getAvailableVoList(Integer userId);

}