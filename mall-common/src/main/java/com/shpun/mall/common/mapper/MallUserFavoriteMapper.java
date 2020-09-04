package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallUserFavorite;
import com.shpun.mall.common.model.vo.MallProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallUserFavoriteMapper {

    int deleteByPrimaryKey(Integer favoriteId);

    int insertSelective(MallUserFavorite record);

    MallUserFavorite selectByPrimaryKey(Integer favoriteId);

    int updateByPrimaryKeySelective(MallUserFavorite record);

    List<MallUserFavorite> getByUserId(Integer userId);

    Integer isFavorite(@Param("userId") Integer userId, @Param("productId") Integer productId);

    List<MallProductVo> getVoListByFavorite(Integer userId);

}