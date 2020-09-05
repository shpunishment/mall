package com.shpun.mall.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.aop.RedisCache;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.exception.MallError;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.mapper.MallProductMapper;
import com.shpun.mall.common.model.MallCart;
import com.shpun.mall.common.model.MallFlashItem;
import com.shpun.mall.common.model.MallProduct;
import com.shpun.mall.common.model.vo.MallCartVo;
import com.shpun.mall.common.model.vo.MallProductVo;
import com.shpun.mall.common.service.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:41
 */
@Service("mallProductService")
public class MallProductServiceImpl implements MallProductService {

    @Autowired
    private MallProductMapper productMapper;

    @Autowired
    private MallProductClassifyService productClassifyService;

    @Autowired
    private MallFlashItemService flashItemService;

    @Autowired
    private MallCartService cartService;

    @Autowired
    private MallUserFavoriteService userFavoriteService;

    @Autowired
    private RedisService redisService;

    @Override
    public void deleteByPrimaryKey(Integer productId) {
        productMapper.deleteByPrimaryKey(productId);
    }

    @Override
    public void insertSelective(MallProduct record) {
        record.setCreateTime(new Date());
        record.setCreateId(Const.ADMIN_ID);
        productMapper.insertSelective(record);
    }

    @Override
    public MallProduct selectByPrimaryKey(Integer productId) {
        return productMapper.selectByPrimaryKey(productId);
    }

    @Override
    public void updateByPrimaryKeySelective(MallProduct record) {
        record.setUpdateTime(new Date());
        record.setUpdateId(Const.ADMIN_ID);
        productMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void insertWithClassifyIdList(MallProduct product, List<Integer> classifyIdList) {
        this.insertSelective(product);
        // 添加商品分类
        if (CollectionUtils.isNotEmpty(classifyIdList)) {
            productClassifyService.insertBatch(classifyIdList, product.getProductId());
        }
    }

    @Override
    public void updateWithClassifyIdList(MallProduct product, List<Integer> classifyIdList) {
        this.updateByPrimaryKeySelective(product);
        // 添加商品分类
        if (CollectionUtils.isNotEmpty(classifyIdList)) {
            productClassifyService.updateBatch(classifyIdList, product.getProductId());
        }
    }

    @Override
    public MallProduct lockStock(Integer productId) {
        return productMapper.lockStock(productId);
    }

    @Override
    public void updateBatch(List<MallProduct> productList) {
        if (CollectionUtils.isNotEmpty(productList)) {
            for (MallProduct product : productList) {
                productMapper.updateByPrimaryKeySelective(product);
            }
        }
    }

    @Override
    public void replaceWithFlash(MallProduct product) {
        MallFlashItem flashItem = flashItemService.getFlashByProductId(product.getProductId());
        if (flashItem != null) {
            product.setFlashing(true);
            product.setStock(flashItem.getStock());
            product.setCurrentPrice(flashItem.getPrice());
            product.setLimit(flashItem.getLimit());
        } else {
            product.setFlashing(false);
        }
    }

    @Override
    public void replaceWithFlash(MallProductVo productVo) {
        MallFlashItem flashItem = flashItemService.getFlashByProductId(productVo.getProductId());
        if (flashItem != null) {
            productVo.setFlashing(true);
            productVo.setStock(flashItem.getStock());
            productVo.setCurrentPrice(flashItem.getPrice());
            productVo.setLimit(flashItem.getLimit());

            // 计算限时抢购商品库存剩余百分比
            BigDecimal stock = new BigDecimal(flashItem.getStock());
            BigDecimal sales = new BigDecimal(flashItem.getSales());
            BigDecimal total = stock.add(sales);
            BigDecimal remainStockRatio = stock.divide(total, 2, RoundingMode.HALF_UP);
            String remainStockPercent = remainStockRatio.multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString();
            productVo.setRemainStockPercent(remainStockPercent);
        } else {
            productVo.setFlashing(false);
        }
    }

    @Override
    public MallProduct checkProduct(Integer productId, Integer quantity) {
        Integer stock;
        Integer limit;

        MallProduct product = this.selectByPrimaryKey(productId);
        // 上架状态
        if (product.getPublishStatus() == 1) {
            // 替换限时抢购库存
            this.replaceWithFlash(product);

            stock = product.getStock();
            limit = product.getLimit();

            if (stock < quantity) {
                throw new MallException(MallError.MallErrorEnum.STOCK_NULL.format(product.getProductName()));
            } else {
                if (limit >= quantity) {
                    return product;
                } else {
                    throw new MallException(MallError.MallErrorEnum.LIMIT_ERROR.format(product.getProductName(), limit));
                }
            }
        } else {
            throw new MallException(MallError.MallErrorEnum.OFF_SHELF.format(product.getProductName()));
        }
    }

    @RedisCache
    @Override
    public MallProductVo getVoByProductId(Integer productId) {
        return productMapper.getVoByProductId(productId);
    }

    @Override
    public List<MallProductVo> getVoListByFilter(Integer classifyId, Integer inStock, Integer priceSort) {
        return productMapper.getVoListFilterByClassifyId(classifyId, inStock, priceSort);
    }

    @RedisCache
    @Override
    public PageInfo<MallProductVo> getVoPageByFilter(Integer classifyId, Integer inStock, Integer priceSort, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);
        return new PageInfo<>(this.getVoListByFilter(classifyId, inStock, priceSort));
    }

    @Override
    public List<MallProductVo> getVoListByFilter(String productName, Integer inStock, Integer priceSort) {
        return productMapper.getVoListFilterByName(productName, inStock, priceSort);
    }

    @RedisCache
    @Override
    public PageInfo<MallProductVo> getVoPageByFilter(String productName, Integer inStock, Integer priceSort, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);
        return new PageInfo<>(this.getVoListByFilter(productName, inStock, priceSort));
    }

    @Override
    public List<MallProductVo> getHotVoList() {
        return productMapper.getHotVoList();
    }

    @RedisCache
    @Override
    public PageInfo<MallProductVo> getHotVoPage(Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);
        return new PageInfo<>(this.getHotVoList());
    }

    @Override
    public List<MallProductVo> getVoListByFlashId(Integer flashId) {
        return productMapper.getVoListByFlashId(flashId);
    }

    @RedisCache(expire = 60)
    @Override
    public PageInfo<MallProductVo> getVoPageByFlashId(Integer flashId, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);
        return new PageInfo<>(this.getVoListByFlashId(flashId));
    }

    @RedisCache
    @Override
    public MallProductVo getDetailVo(Integer productId) {
        return productMapper.getDetailVo(productId);
    }

    @Override
    public void additionalVo(MallProductVo productVo, Integer userId, boolean replaceWithFlash, boolean favorite) {
        if (replaceWithFlash) {
            // 判断商品是否限时抢购，替换库存，价格和限购数
            this.replaceWithFlash(productVo);
        }

        if (userId != null) {
            MallCartVo cartVo = cartService.getVoByUserIdAndProductId(userId, productVo.getProductId());
            productVo.setCartVo(cartVo);

            if (favorite) {
                productVo.setFavoriteId(userFavoriteService.isFavorite(userId, productVo.getProductId()));
            }
        }

        // 赋值库存标识
        Integer stock = productVo.getStock();
        if (stock != null) {
            productVo.setHasStock(stock > 0);
            productVo.setStock(null);
        }


        // 赋值打折数
        BigDecimal originalPrice = productVo.getOriginalPrice();
        BigDecimal currentPrice = productVo.getCurrentPrice();
        if (currentPrice.compareTo(originalPrice) < 0) {
            BigDecimal discount = currentPrice.divide(originalPrice, 2, RoundingMode.HALF_UP);
            productVo.setDiscountStr(discount.multiply(new BigDecimal("10")).stripTrailingZeros().toPlainString());
        }
    }

    @Override
    public void additionalVoList(List<MallProductVo> productVoList, Integer userId, boolean replaceWithFlash) {
        if (CollectionUtils.isNotEmpty(productVoList)) {
            for(MallProductVo productVo : productVoList) {
                this.additionalVo(productVo, userId, replaceWithFlash, false);
            }
        }
    }

    @Override
    public List<MallProduct> getListByClassifyId(Integer classifyId) {
        return productMapper.getListByClassifyId(classifyId);
    }

    @Override
    public void deleteCache() {
        redisService.deleteByPrefix(MallProductServiceImpl.class, null);
    }

    @Override
    public List<MallProductVo> getVoListByFilterClassifyId(List<Integer> classifyIdList, Integer inStock, Integer priceSort) {
        return productMapper.getVoListFilterByClassifyIdList(classifyIdList, inStock, priceSort);
    }

    @RedisCache
    @Override
    public PageInfo<MallProductVo> getVoPageByFilterClassifyId(List<Integer> classifyIdList, Integer inStock, Integer priceSort, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);
        return new PageInfo<>(this.getVoListByFilterClassifyId(classifyIdList, inStock, priceSort));
    }

    @RedisCache
    @Override
    public List<MallProductVo> getVoListByFilterProductId(List<Integer> productIdList, Integer inStock, Integer priceSort) {
        return productMapper.getVoListByProductIdList(productIdList, inStock, priceSort);
    }

    @Override
    public PageInfo<MallProductVo> getVoPageByFilterProductId(List<Integer> productIdList, Integer inStock, Integer priceSort, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);
        return new PageInfo<>(this.getVoListByFilterProductId(productIdList, inStock, priceSort));
    }

}
