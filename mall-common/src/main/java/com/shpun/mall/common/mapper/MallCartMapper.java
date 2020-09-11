package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallCart;
import com.shpun.mall.common.model.vo.MallCartVo;
import com.shpun.mall.common.model.vo.MallProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallCartMapper {

    int deleteByPrimaryKey(Integer cartId);

    int insertSelective(MallCart record);

    MallCart selectByPrimaryKey(Integer cartId);

    int updateByPrimaryKeySelective(MallCart record);

    void deleteAllByUserId(Integer userId);

    List<MallCart> getByUserIdAndCartIdList(@Param("userId") Integer userId, @Param("cartIdList") List<Integer> cartIdList);

    void deleteBatch(@Param("cartIdList") List<Integer> cartIdList);

    List<MallProductVo> getVoByUserId(Integer userId);

    MallCartVo getVoByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    MallCart getByUserIdAndCartId(@Param("userId") Integer userId, @Param("cartId") Integer cartId);

    MallCart getByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

}