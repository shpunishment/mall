package com.shpun.mall.common.mapper;

import com.shpun.model.MallActivityClassifyProduct;

public interface MallActivityClassifyProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MallActivityClassifyProduct record);

    int insertSelective(MallActivityClassifyProduct record);

    MallActivityClassifyProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MallActivityClassifyProduct record);

    int updateByPrimaryKey(MallActivityClassifyProduct record);
}