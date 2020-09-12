package com.shpun.mall.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.aop.RedisCache;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.mapper.MallFlashItemMapper;
import com.shpun.mall.common.model.MallFlashItem;
import com.shpun.mall.common.model.vo.MallCartVo;
import com.shpun.mall.common.model.vo.MallProductVo;
import com.shpun.mall.common.service.MallCartService;
import com.shpun.mall.common.service.MallFlashItemService;
import com.shpun.mall.common.service.MallFlashService;
import com.shpun.mall.common.service.RedisService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @Autowired
    private MallCartService cartService;

    @Autowired
    private MallFlashService flashService;

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
    public void additionalVoList(List<MallProductVo> productVoList, Integer userId, Integer flashId) {
        if (CollectionUtils.isNotEmpty(productVoList)) {
            boolean isFlashing = flashService.isFlashing(flashId);
            for(MallProductVo productVo : productVoList) {
                productVo.setFlashing(isFlashing);

                if (userId != null) {
                    MallCartVo cartVo = cartService.getVoByUserIdAndProductId(userId, productVo.getProductId());
                    productVo.setCartVo(cartVo);
                }

                if (isFlashing) {
                    // 赋值打折数
                    BigDecimal originalPrice = productVo.getOriginalPrice();
                    BigDecimal currentPrice = productVo.getCurrentPrice();
                    if (currentPrice.compareTo(originalPrice) < 0) {
                        BigDecimal discount = currentPrice.divide(originalPrice, 2, RoundingMode.HALF_UP);
                        productVo.setDiscountStr(discount.multiply(new BigDecimal("10")).stripTrailingZeros().toPlainString());
                    } else {
                        productVo.setDiscountStr(null);
                    }
                } else {
                    // 抢购还未开始，隐藏抢购价
                    productVo.setCurrentPrice(null);
                }

                // 计算限时抢购商品库存剩余百分比
                this.setRemainStockPercent(productVo);

                // 赋值库存标识
                Integer stock = productVo.getStock();
                if (stock != null) {
                    productVo.setHasStock(stock > 0);
                    productVo.setStock(null);
                } else {
                    productVo.setHasStock(false);
                }
            }
        }
    }

    /**
     * 计算限时抢购商品库存剩余百分比
     * @param productVo
     */
    private void setRemainStockPercent(MallProductVo productVo) {
        BigDecimal stock = new BigDecimal(productVo.getStock());
        BigDecimal sales = new BigDecimal(productVo.getSales());
        BigDecimal total = stock.add(sales);
        BigDecimal remainStockRatio = stock.divide(total, 2, RoundingMode.HALF_UP);
        String remainStockPercent = remainStockRatio.multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString();
        productVo.setRemainStockPercent(remainStockPercent);
    }

    @Override
    public void deleteCache() {
        redisService.deleteByPrefix(MallFlashItemServiceImpl.class, null);
    }

}
