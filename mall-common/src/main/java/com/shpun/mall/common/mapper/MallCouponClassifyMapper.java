package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallCouponClassify;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallCouponClassifyMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(MallCouponClassify record);

    MallCouponClassify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MallCouponClassify record);

    void insertBatch(@Param("couponClassifyList") List<MallCouponClassify> couponClassifyList);

    List<Integer> getClassifyIdByCouponId(Integer couponId);

    void deleteBatch(@Param("classifyIdList") List<Integer> classifyIdList, @Param("couponId") Integer couponId);

}