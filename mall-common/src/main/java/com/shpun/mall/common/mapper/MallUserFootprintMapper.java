package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallUserFootprint;
import com.shpun.mall.common.model.vo.MallProductVo;

import java.util.List;

public interface MallUserFootprintMapper {

    int deleteByPrimaryKey(Integer footprintId);

    int insertSelective(MallUserFootprint record);

    MallUserFootprint selectByPrimaryKey(Integer footprintId);

    int updateByPrimaryKeySelective(MallUserFootprint record);

    List<MallUserFootprint> getByUserId(Integer userId);

    List<MallProductVo> getVoListByFootprint(Integer userId);

}