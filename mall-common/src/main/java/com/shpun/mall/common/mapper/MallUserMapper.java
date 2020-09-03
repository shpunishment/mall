package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallUser;
import com.shpun.mall.common.model.vo.MallUserVo;

public interface MallUserMapper {

    int deleteByPrimaryKey(Integer userId);

    int insertSelective(MallUser record);

    MallUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(MallUser record);

    MallUser getByUsername(String username);

    MallUserVo getVoByUserId(Integer userId);

    boolean isExist(String username);

}