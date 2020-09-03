package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallUser;
import com.shpun.mall.common.model.vo.MallUserVo;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:38
 */
public interface MallUserService {

    void deleteByPrimaryKey(Integer userId);

    void insertSelective(MallUser record);

    MallUser selectByPrimaryKey(Integer userId);

    void updateByPrimaryKeySelective(MallUser record);

    MallUser getByUsername(String username);

    MallUserVo getVoByUserId(Integer userId);

    boolean isExist(String username);

    void deleteCache(Integer userId);

}
