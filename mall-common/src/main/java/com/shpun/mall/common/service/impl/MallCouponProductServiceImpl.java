package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.mapper.MallCouponProductMapper;
import com.shpun.mall.common.model.MallCouponClassify;
import com.shpun.mall.common.model.MallCouponProduct;
import com.shpun.mall.common.service.MallCouponProductService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/2 14:53
 */
@Service("mallCouponProductService")
public class MallCouponProductServiceImpl implements MallCouponProductService {

    @Autowired
    private MallCouponProductMapper couponProductMapper;

    @Override
    public void deleteByPrimaryKey(Integer id) {
        couponProductMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(MallCouponProduct record) {
        record.setCreateTime(new Date());
        couponProductMapper.insertSelective(record);
    }

    @Override
    public MallCouponProduct selectByPrimaryKey(Integer id) {
        return couponProductMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKeySelective(MallCouponProduct record) {
        couponProductMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void insertBatch(List<Integer> productIdList, Integer couponId) {
        List<MallCouponProduct> couponProductList = new ArrayList<>(productIdList.size());
        productIdList.forEach(productId -> {
            MallCouponProduct couponProduct = new MallCouponProduct();
            couponProduct.setCreateTime(new Date());
            couponProduct.setCreateId(Const.ADMIN_ID);
            couponProduct.setCouponId(couponId);
            couponProduct.setProductId(productId);

            couponProductList.add(couponProduct);
        });
        couponProductMapper.insertBatch(couponProductList);
    }

    @Transactional
    @Override
    public void updateBatch(List<Integer> newProductIdList, Integer couponId) {
        List<Integer> oldProductIdList = couponProductMapper.getProductIdByCouponId(couponId);

        // 删除：循环旧list，每个id去新list中contain，没有存在就是删除
        List<Integer> deleteList = oldProductIdList.stream().filter(id -> !newProductIdList.contains(id)).collect(Collectors.toList());
        // 新增：循环新list，每个id去旧list中contain，没有存在就是新增的
        List<Integer> insertList = newProductIdList.stream().filter(id -> !oldProductIdList.contains(id)).collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(deleteList)) {
            couponProductMapper.deleteBatch(deleteList, couponId);
        }
        if (CollectionUtils.isNotEmpty(insertList)) {
            this.insertBatch(insertList, couponId);
        }
    }
}
