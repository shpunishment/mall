package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallActivityClassifyProduct;
import io.lettuce.core.dynamic.annotation.Param;

public interface MallActivityClassifyProductMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(MallActivityClassifyProduct record);

    MallActivityClassifyProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MallActivityClassifyProduct record);

    Integer getMaxSnByClassifyId(Integer classifyId);

    Integer getMinSnByClassifyId(Integer classifyId);

    MallActivityClassifyProduct getPrev(MallActivityClassifyProduct activityClassifyProduct);

    MallActivityClassifyProduct getNext(MallActivityClassifyProduct activityClassifyProduct);

    /**
     *      * 将[startSn,endSn)的sn都加1，向后移动一位
     * @param classifyId
     * @param startSn
     * @param endSn
     */
    void goNext(@Param("classifyId") Integer classifyId, @Param("startSn") Integer startSn, @Param("endSn") Integer endSn);

}