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

    List<Integer> getAvailableCouponIdList(Integer userId);

}