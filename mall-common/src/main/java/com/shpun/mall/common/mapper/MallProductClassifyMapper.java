package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallProductClassify;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallProductClassifyMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(MallProductClassify record);

    MallProductClassify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MallProductClassify record);

    void insertBatch(@Param("productClassifyList") List<MallProductClassify> productClassifyList);

    List<Integer> getClassifyIdByProductId(Integer productId);

    void deleteBatch(@Param("classifyIdList") List<Integer> classifyIdList, @Param("productId") Integer productId);

}