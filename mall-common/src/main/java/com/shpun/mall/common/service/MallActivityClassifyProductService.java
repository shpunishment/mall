package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallActivityClassifyProduct;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/6 16:02
 */
public interface MallActivityClassifyProductService {

    void deleteByPrimaryKey(Integer id);

    void insertSelective(MallActivityClassifyProduct record);

    MallActivityClassifyProduct selectByPrimaryKey(Integer id);

    void updateByPrimaryKeySelective(MallActivityClassifyProduct record);

    /**
     * 上移
     * @param id
     */
    void up(Integer id);

    /**
     * 下移
     * @param id
     */
    void down(Integer id);

    /**
     * 置顶
     * @param id
     */
    void top(Integer id);

}
