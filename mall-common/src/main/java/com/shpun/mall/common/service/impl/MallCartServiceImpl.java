package com.shpun.mall.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.aop.RedisCache;
import com.shpun.mall.common.exception.MallError;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.mapper.MallCartMapper;
import com.shpun.mall.common.model.MallCart;
import com.shpun.mall.common.model.MallProduct;
import com.shpun.mall.common.model.vo.MallCartVo;
import com.shpun.mall.common.model.vo.MallProductVo;
import com.shpun.mall.common.service.MallCartService;
import com.shpun.mall.common.service.MallProductService;
import com.shpun.mall.common.service.RedisService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:40
 */
@Service("mallCartService")
public class MallCartServiceImpl implements MallCartService {

    @Autowired
    private MallCartMapper cartMapper;

    @Autowired
    private MallProductService productService;

    @Autowired
    private RedisService redisService;

    @Override
    public void deleteByPrimaryKey(Integer cartId) {
        cartMapper.deleteByPrimaryKey(cartId);
    }

    @Override
    public void insertSelective(MallCart record) {
        // 添加购物车前，检查商品的库存和限购
        if (productService.checkProduct(record.getProductId(), record.getQuantity()) != null) {
            record.setCreateTime(new Date());
            record.setUpdateTime(new Date());
            cartMapper.insertSelective(record);

            // 清空缓存
            this.deleteCache(record.getUserId());
        }
    }

    @Override
    public MallCart selectByPrimaryKey(Integer cartId) {
        return cartMapper.selectByPrimaryKey(cartId);
    }

    @Override
    public void updateByPrimaryKeySelective(MallCart record) {
        // 添加购物车前，检查商品的库存和限购
        if (productService.checkProduct(record.getProductId(), record.getQuantity()) != null) {
            record.setUpdateTime(new Date());
            cartMapper.updateByPrimaryKeySelective(record);

            // 清空缓存
            this.deleteCache(record.getUserId());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addOrUpdate(MallCart cart) {
        Integer cartId = cart.getCartId();
        if (cartId != null) {
            MallCart isExist = cartMapper.getByUserIdAndCartId(cart.getUserId(), cartId);
            if (isExist == null) {
                throw new MallException(MallError.MallErrorEnum.INTERNAL_SYSTEM_ERROR);
            } else {
                if (cart.getQuantity() == 0) {
                    this.deleteByPrimaryKey(isExist.getCartId());
                    // 清空缓存
                    this.deleteCache(cart.getUserId());
                } else {
                    this.updateByPrimaryKeySelective(cart);
                }
            }
        } else {
            MallCart isExist = cartMapper.getByUserIdAndProductId(cart.getUserId(), cart.getProductId());
            if (isExist == null) {
                this.insertSelective(cart);
            } else {
                cart.setCartId(isExist.getCartId());
                if (cart.getQuantity() == 0) {
                    this.deleteByPrimaryKey(isExist.getCartId());
                    // 清空缓存
                    this.deleteCache(cart.getUserId());
                } else {
                    this.updateByPrimaryKeySelective(cart);
                }
            }
        }
    }

    @Override
    public void deleteAllByUserId(Integer userId) {
        cartMapper.deleteAllByUserId(userId);
    }

    @RedisCache
    @Override
    public List<MallCart> getByUserIdAndCartIdList(Integer userId, List<Integer> cartIdList) {
        return cartMapper.getByUserIdAndCartIdList(userId, cartIdList);
    }

    @Override
    public void deleteBatch(List<Integer> cartIdList) {
        cartMapper.deleteBatch(cartIdList);
    }

    @RedisCache
    @Override
    public MallCartVo getVoByUserIdAndProductId(Integer userId, Integer productId) {
        return cartMapper.getVoByUserIdAndProductId(userId, productId);
    }

    @Override
    public List<MallProductVo> getVoListByUserId(Integer userId) {
        return cartMapper.getVoListByUserId(userId);
    }

    @Override
    public PageInfo<MallProductVo> getVoPageByUserId(Integer userId, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);
        return new PageInfo<>(this.getVoListByUserId(userId));
    }

    @RedisCache
    @Override
    public List<MallProductVo> getStockAndNoStockVoList(Integer userId) {
        List<MallProductVo> resultList = new ArrayList<>(1);
        List<MallProductVo> hasStockVoList = cartMapper.getHasStockVoList(userId);
        if (CollectionUtils.isNotEmpty(hasStockVoList)) {
            resultList.addAll(hasStockVoList);
        }
        List<MallProductVo> noStockVoList = cartMapper.getNoStockVoList(userId);
        if (CollectionUtils.isNotEmpty(noStockVoList)) {
            resultList.addAll(noStockVoList);
        }
        return resultList;
    }

    @Override
    public Integer getAvailableCartSum(Integer userId) {
        return cartMapper.getAvailableCartSum(userId);
    }

    @Override
    public void deleteCache(Integer userId) {
        redisService.deleteByPrefix(MallCartServiceImpl.class, "getByUserIdAndCartIdList", userId);
        redisService.deleteByPrefix(MallCartServiceImpl.class, "getVoByUserIdAndProductId", userId);
        redisService.deleteByPrefix(MallCartServiceImpl.class, "getStockAndNoStockVoList", userId);
    }

}
