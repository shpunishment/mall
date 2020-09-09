package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallFlash;
import com.shpun.mall.common.model.vo.MallFlashVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallFlashMapper {

    int deleteByPrimaryKey(Integer flashId);

    int insertSelective(MallFlash record);

    MallFlash selectByPrimaryKey(Integer flashId);

    int updateByPrimaryKeySelective(MallFlash record);

    List<MallFlashVo> getTodayAvailableVoList();

    List<MallFlashVo> getTomorrowAvailableVoList(@Param("year") Integer year,
                                                 @Param("month") Integer month,
                                                 @Param("day") Integer day,
                                                 @Param("hour") Integer hour);

    boolean isFlashing(@Param("flashId") Integer flashId, @Param("flashLimitMins") Integer flashLimitMins);

    List<MallFlash> getList();

}