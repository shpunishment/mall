package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallBrand;
import com.shpun.mall.common.model.vo.MallBrandVo;

import java.util.List;

public interface MallBrandMapper {

    int deleteByPrimaryKey(Integer brandId);

    int insertSelective(MallBrand record);

    MallBrand selectByPrimaryKey(Integer brandId);

    int updateByPrimaryKeySelective(MallBrand record);

    MallBrandVo getVoByBrandId(Integer brandId);

    List<MallBrand> getList();

}