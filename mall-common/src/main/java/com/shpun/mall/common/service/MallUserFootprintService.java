package com.shpun.mall.common.service;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallUserFootprint;
import com.shpun.mall.common.model.vo.MallProductVo;

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

    List<MallProductVo> getVoListByFootprint(Integer userId);

    PageInfo<MallProductVo> getVoPageByFootprint(Integer userId, Integer offset, Integer limit);

    /**
     * 新增删除，根据用户id刷新缓存
     */
    void deleteCache(Integer userId);

}
