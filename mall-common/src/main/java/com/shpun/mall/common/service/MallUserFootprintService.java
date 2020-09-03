package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallUserFootprint;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:37
 */
public interface MallUserFootprintService {

    void deleteByPrimaryKey(Integer footprintId);

    void insertSelective(MallUserFootprint record);

    MallUserFootprint selectByPrimaryKey(Integer footprintId);

    void updateByPrimaryKeySelective(MallUserFootprint record);

    List<MallUserFootprint> getByUserId(Integer userId);

}
