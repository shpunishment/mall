package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallFlashItem;
import com.shpun.mall.common.model.vo.MallProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface MallFlashItemMapper {

    int deleteByPrimaryKey(Integer flashItemId);

    int insertSelective(MallFlashItem record);

    MallFlashItem selectByPrimaryKey(Integer flashItemId);

    int updateByPrimaryKeySelective(MallFlashItem record);

    /**
     * 根据商品id，获取该商品当前时间的限时抢购信息
     * @param productId
     * @return
     */
    MallFlashItem getFlashByProductId(@Param("productId") Integer productId, @Param("flashLimitMins") Integer flashLimitMins);

    MallFlashItem lockStock(Integer flashItemId);

    List<MallFlashItem> getByFlashId(Integer flashId);

    List<MallProductVo> getVoListByFlashId(Integer flashId);

    List<Integer> getProductIdByFlashIdList(@Param("flashIdList") List<Integer> flashIdList);

}