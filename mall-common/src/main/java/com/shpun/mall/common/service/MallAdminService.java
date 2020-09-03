package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallAdmin;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:36
 */
public interface MallAdminService {

    void deleteByPrimaryKey(Integer adminId);

    void insertSelective(MallAdmin record);

    MallAdmin selectByPrimaryKey(Integer adminId);

    void updateByPrimaryKeySelective(MallAdmin record);

}
