package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallActivityClassify;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/6 16:02
 */
public interface MallActivityClassifyService {

    void deleteByPrimaryKey(Integer classifyId);

    void insertSelective(MallActivityClassify record);

    MallActivityClassify selectByPrimaryKey(Integer classifyId);

    void updateByPrimaryKeySelective(MallActivityClassify record);

    /**
     * 上移
     * @param classifyId
     */
    void up(Integer classifyId);

    /**
     * 下移
     * @param classifyId
     */
    void down(Integer classifyId);

    /**
     * 置顶
     * @param classifyId
     */
    void top(Integer classifyId);

}
