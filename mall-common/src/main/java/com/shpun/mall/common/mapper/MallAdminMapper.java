package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallAdmin;

public interface MallAdminMapper {

    int deleteByPrimaryKey(Integer adminId);

    int insertSelective(MallAdmin record);

    MallAdmin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(MallAdmin record);

}