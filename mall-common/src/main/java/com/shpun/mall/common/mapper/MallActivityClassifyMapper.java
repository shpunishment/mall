package com.shpun.mall.common.mapper;

import com.shpun.model.MallActivityClassify;

public interface MallActivityClassifyMapper {
    int deleteByPrimaryKey(Integer classifyId);

    int insert(MallActivityClassify record);

    int insertSelective(MallActivityClassify record);

    MallActivityClassify selectByPrimaryKey(Integer classifyId);

    int updateByPrimaryKeySelective(MallActivityClassify record);

    int updateByPrimaryKey(MallActivityClassify record);
}