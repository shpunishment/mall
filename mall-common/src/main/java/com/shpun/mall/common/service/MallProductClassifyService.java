package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallProductClassify;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:37
 */
public interface MallProductClassifyService {

    void deleteByPrimaryKey(Integer id);

    void insertSelective(MallProductClassify record);

    MallProductClassify selectByPrimaryKey(Integer id);

    void updateByPrimaryKeySelective(MallProductClassify record);

    void insertBatch(List<Integer> classifyIdList, Integer productId);

    void updateBatch(List<Integer> newClassifyIdList, Integer productId);

    List<Integer> getClassifyIdByProductId(Integer productId);

}
