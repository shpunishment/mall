package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallCoupon;
import com.shpun.mall.common.model.vo.MallCouponVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallCouponMapper {

    int deleteByPrimaryKey(Integer couponId);

    int insertSelective(MallCoupon record);

    MallCoupon selectByPrimaryKey(Integer couponId);

    int updateByPrimaryKeySelective(MallCoupon record);

    List<MallCoupon> getList();

    List<MallCouponVo> getVoList();

    List<Integer> getClassifyIdList(Integer couponId);

    List<Integer> getProductIdList(Integer couponId);

    MallCouponVo getAvailable(Integer couponId);

    MallCoupon lockCoupon(Integer couponId);

    MallCoupon getForOrder(Integer couponId);

    /**
     * 获取新人礼券
     * @return
     */
    List<Integer> getNewUserCouponId();

    List<MallCouponVo> getVoListByProductId(Integer productId);

}