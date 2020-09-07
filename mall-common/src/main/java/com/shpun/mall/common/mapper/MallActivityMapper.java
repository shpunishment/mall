package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallActivity;
import com.shpun.mall.common.model.vo.MallActivityVo;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface MallActivityMapper {

    int deleteByPrimaryKey(Integer activityId);

    int insertSelective(MallActivity record);

    MallActivity selectByPrimaryKey(Integer activityId);

    int updateByPrimaryKeySelective(MallActivity record);

    Integer getMaxSn();

    Integer getMinSn();

    MallActivity getPrev(MallActivity activity);

    MallActivity getNext(MallActivity activity);

    /**
     * 将[startSn,endSn)的sn都加1，向后移动一位
     * @param startSn
     * @param endSn
     */
    void goNext(@Param("startSn") Integer startSn, @Param("endSn") Integer endSn);

    List<MallActivityVo> getTodayVoList();

    Boolean isActive(Integer activityId);

}