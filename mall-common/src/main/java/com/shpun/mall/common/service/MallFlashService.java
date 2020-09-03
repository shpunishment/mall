package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallFlash;
import com.shpun.mall.common.model.vo.MallFlashVo;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:37
 */
public interface MallFlashService {

    void deleteByPrimaryKey(Integer flashId);

    void insertSelective(MallFlash record);

    MallFlash selectByPrimaryKey(Integer flashId);

    void updateByPrimaryKeySelective(MallFlash record);

    List<MallFlashVo> getTodayVoList();

    boolean isFlashing(Integer flashId);

    List<MallFlash> getList();

}
