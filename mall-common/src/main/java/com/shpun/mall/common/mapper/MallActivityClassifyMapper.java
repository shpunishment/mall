package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallActivityClassify;
import io.lettuce.core.dynamic.annotation.Param;

public interface MallActivityClassifyMapper {

    int deleteByPrimaryKey(Integer classifyId);

    int insertSelective(MallActivityClassify record);

    MallActivityClassify selectByPrimaryKey(Integer classifyId);

    int updateByPrimaryKeySelective(MallActivityClassify record);

    Integer getMaxSnByActivityId(Integer activityId);

    Integer getMinSnByActivityId(Integer activityId);

    MallActivityClassify getPrev(MallActivityClassify activityClassify);

    MallActivityClassify getNext(MallActivityClassify activityClassify);

    /**
     * 将[startSn,endSn)的sn都加1，向后移动一位
     * @param startSn
     * @param endSn
     */
    void goNext(@Param("activityId") Integer activityId, @Param("startSn") Integer startSn, @Param("endSn") Integer endSn);

}