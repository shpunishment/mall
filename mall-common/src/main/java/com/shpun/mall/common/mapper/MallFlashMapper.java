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

    List<MallFlashVo> getTodayVoList();

    boolean isFlashing(@Param("flashId") Integer flashId, @Param("flashLimitMins") Integer flashLimitMins);

    List<MallFlash> getList();

}