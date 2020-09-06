package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallActivityClassify;

public interface MallActivityClassifyMapper {

    int deleteByPrimaryKey(Integer classifyId);

    int insertSelective(MallActivityClassify record);

    MallActivityClassify selectByPrimaryKey(Integer classifyId);

    int updateByPrimaryKeySelective(MallActivityClassify record);

}