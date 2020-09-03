package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallBrand;
import com.shpun.mall.common.model.vo.MallBrandVo;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:36
 */
public interface MallBrandService {

    void deleteByPrimaryKey(Integer brandId);

    void insertSelective(MallBrand record);

    MallBrand selectByPrimaryKey(Integer brandId);

    void updateByPrimaryKeySelective(MallBrand record);

    MallBrandVo getVoByBrandId(Integer brandId);

    List<MallBrand> getList();

}
