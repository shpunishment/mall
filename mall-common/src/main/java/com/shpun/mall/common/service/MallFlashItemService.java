package com.shpun.mall.common.service;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallFlashItem;
import com.shpun.mall.common.model.vo.MallProductVo;

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

    /**
     * 根据限时抢购id获取商品
     * @param flashId
     * @return
     */
    List<MallProductVo> getVoListByFlashId(Integer flashId);

    PageInfo<MallProductVo> getVoPageByFlashId(Integer flashId, Integer offset, Integer limit);

    /**
     * 下单成功，删除缓存
     */
    void deleteCache();

}
