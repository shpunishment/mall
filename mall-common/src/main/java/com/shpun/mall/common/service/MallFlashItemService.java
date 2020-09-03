package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallFlashItem;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:37
 */
public interface MallFlashItemService {

    void deleteByPrimaryKey(Integer flashItemId);

    void insertSelective(MallFlashItem record);

    MallFlashItem selectByPrimaryKey(Integer flashItemId);

    void updateByPrimaryKeySelective(MallFlashItem record);

    /**
     * 根据商品id，获取该商品当前时间的限时抢购信息
     * @param productId
     * @return
     */
    MallFlashItem getFlashByProductId(Integer productId);

    MallFlashItem lockStock(Integer flashItemId);

    void updateBatch(List<MallFlashItem> flashItemList);

    List<MallFlashItem> getByFlashId(Integer flashId);

    void deleteCache();

}
