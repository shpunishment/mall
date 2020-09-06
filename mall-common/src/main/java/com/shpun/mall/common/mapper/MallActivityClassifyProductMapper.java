package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallActivityClassifyProduct;

public interface MallActivityClassifyProductMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(MallActivityClassifyProduct record);

    MallActivityClassifyProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MallActivityClassifyProduct record);

}