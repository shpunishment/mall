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

}
