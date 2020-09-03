package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallFileServer;

public interface MallFileServerMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insertSelective(MallFileServer record);

    MallFileServer selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(MallFileServer record);

}