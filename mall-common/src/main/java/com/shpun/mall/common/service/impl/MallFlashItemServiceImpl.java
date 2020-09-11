package com.shpun.mall.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.aop.RedisCache;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.mapper.MallFlashItemMapper;
import com.shpun.mall.common.model.MallFlashItem;
import com.shpun.mall.common.model.vo.MallProductVo;
import com.shpun.mall.common.service.MallFlashItemService;
import com.shpun.mall.common.service.RedisService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:41
 */
@Service("mallFlashItemService")
public class MallFlashItemServiceImpl implements MallFlashItemService {

    @Autowired
    private MallFlashItemMapper flashItemMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public void deleteByPrimaryKey(Integer flashItemId) {
        flashItemMapper.deleteByPrimaryKey(flashItemId);
    }

    @Override
    public void insertSelective(MallFlashItem record) {
        record.setCreateTime(new Date());
        flashItemMapper.insertSelective(record);
    }

    @Override
    public MallFlashItem selectByPrimaryKey(Integer flashItemId) {
        return flashItemMapper.selectByPrimaryKey(flashItemId);
    }

    @Override
    public void updateByPrimaryKeySelective(MallFlashItem record) {
        record.setUpdateTime(new Date());
        flashItemMapper.updateByPrimaryKeySelective(record);
    }

    @RedisCache(expire = 30)
    @Override
    public MallFlashItem getFlashByProductId(Integer productId) {
        return flashItemMapper.getFlashByProductId(productId, Const.DEFAULT_FLASH_LIMIT_MINS);
    }

    @Override
    public MallFlashItem lockStock(Integer flashItemId) {
        return flashItemMapper.lockStock(flashItemId);
    }

    @Override
    public void updateBatch(List<MallFlashItem> flashItemList) {
        if (CollectionUtils.isNotEmpty(flashItemList)) {
            for (MallFlashItem flashItem : flashItemList) {
                flashItemMapper.updateByPrimaryKeySelective(flashItem);
            }
        }
    }

    @Override
    public List<MallFlashItem> getByFlashId(Integer flashId) {
        return flashItemMapper.getByFlashId(flashId);
    }

    @Override
    public List<MallProductVo> getVoListByFlashId(Integer flashId) {
        return flashItemMapper.getVoListByFlashId(flashId);
    }

    @RedisCache(expire = 60)
    @Override
    public PageInfo<MallProductVo> getVoPageByFlashId(Integer flashId, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);
        return new PageInfo<>(this.getVoListByFlashId(flashId));
    }

    @RedisCache
    @Override
    public List<Integer> getProductIdByFlashIdList(List<Integer> flashIdList) {
        return flashItemMapper.getProductIdByFlashIdList(flashIdList);
    }

    @Override
    public void deleteCache() {
        redisService.deleteByPrefix(MallFlashItemServiceImpl.class, null);
    }

}
