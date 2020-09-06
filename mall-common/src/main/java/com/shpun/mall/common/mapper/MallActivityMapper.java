package com.shpun.mall.common.mapper;

import com.shpun.model.MallActivity;

public interface MallActivityMapper {
    int deleteByPrimaryKey(Integer activityId);

    int insert(MallActivity record);

    int insertSelective(MallActivity record);

    MallActivity selectByPrimaryKey(Integer activityId);

    int updateByPrimaryKeySelective(MallActivity record);

    int updateByPrimaryKey(MallActivity record);
}