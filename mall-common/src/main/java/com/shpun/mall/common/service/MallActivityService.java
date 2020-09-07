package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallActivity;
import com.shpun.mall.common.model.vo.MallActivityVo;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/6 16:01
 */
public interface MallActivityService {

    void deleteByPrimaryKey(Integer activityId);

    void insertSelective(MallActivity record);

    MallActivity selectByPrimaryKey(Integer activityId);

    void updateByPrimaryKeySelective(MallActivity record);

    /**
     * 上移
     * @param activityId
     */
    void up(Integer activityId);

    /**
     * 下移
     * @param activityId
     */
    void down(Integer activityId);

    /**
     * 置顶
     * @param activityId
     */
    void top(Integer activityId);

    List<MallActivityVo> getTodayVoList();

    Boolean isActive(Integer activityId);

}
