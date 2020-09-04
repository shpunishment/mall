package com.shpun.mall.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.aop.RedisCache;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.mapper.MallCouponMapper;
import com.shpun.mall.common.model.MallCoupon;
import com.shpun.mall.common.model.vo.MallCouponVo;
import com.shpun.mall.common.service.MallCouponClassifyService;
import com.shpun.mall.common.service.MallCouponProductService;
import com.shpun.mall.common.service.MallCouponService;
import com.shpun.mall.common.service.RedisService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/2 14:53
 */
@Service("mallCouponService")
public class MallCouponServiceImpl implements MallCouponService {

    @Autowired
    private MallCouponMapper couponMapper;

    @Autowired
    private MallCouponClassifyService couponClassifyService;

    @Autowired
    private MallCouponProductService couponProductService;

    @Autowired
    private RedisService redisService;

    @Override
    public void deleteByPrimaryKey(Integer couponId) {
        couponMapper.deleteByPrimaryKey(couponId);
    }

    @Override
    public void insertSelective(MallCoupon record) {
        record.setCreateTime(new Date());
        record.setCreateId(Const.ADMIN_ID);
        couponMapper.insertSelective(record);
    }

    @Override
    public MallCoupon selectByPrimaryKey(Integer couponId) {
        return couponMapper.selectByPrimaryKey(couponId);
    }

    @Override
    public void updateByPrimaryKeySelective(MallCoupon record) {
        record.setUpdateTime(new Date());
        record.setCreateId(Const.ADMIN_ID);
        couponMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void insertWithClassifyIdList(MallCoupon coupon, List<Integer> classifyIdList) {
        this.insertSelective(coupon);
        // 添加商品分类
        if (CollectionUtils.isNotEmpty(classifyIdList)) {
            couponClassifyService.insertBatch(classifyIdList, coupon.getCouponId());
        }
    }

    @Override
    public void updateWithClassifyIdList(MallCoupon coupon, List<Integer> classifyIdList) {
        this.updateByPrimaryKeySelective(coupon);
        // 添加商品分类
        if (CollectionUtils.isNotEmpty(classifyIdList)) {
            couponClassifyService.updateBatch(classifyIdList, coupon.getCouponId());
        }
    }

    @Override
    public void insertWithProductIdList(MallCoupon coupon, List<Integer> productIdList) {
        this.insertSelective(coupon);
        // 添加商品
        if (CollectionUtils.isNotEmpty(productIdList)) {
            couponProductService.insertBatch(productIdList, coupon.getCouponId());
        }
    }

    @Override
    public void updateWithProductIdList(MallCoupon coupon, List<Integer> productIdList) {
        this.updateByPrimaryKeySelective(coupon);
        // 添加商品
        if (CollectionUtils.isNotEmpty(productIdList)) {
            couponProductService.updateBatch(productIdList, coupon.getCouponId());
        }
    }

    @Override
    public List<MallCoupon> getList() {
        return couponMapper.getList();
    }

    @Override
    public List<MallCouponVo> getVoList() {
        return couponMapper.getVoList();
    }

    @RedisCache
    @Override
    public PageInfo<MallCouponVo> getVoPage(Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);
        return new PageInfo<>(this.getVoList());
    }

    @RedisCache
    @Override
    public List<Integer> getClassifyIdList(Integer couponId) {
        return couponMapper.getClassifyIdList(couponId);
    }

    @RedisCache
    @Override
    public List<Integer> getProductIdList(Integer couponId) {
        return couponMapper.getProductIdList(couponId);
    }

    @Override
    public MallCoupon getAvailable(Integer couponId) {
        return couponMapper.getAvailable(couponId);
    }

    @Override
    public MallCoupon lockCoupon(Integer couponId) {
        return couponMapper.lockCoupon(couponId);
    }

    @Override
    public void update(MallCoupon coupon) {
        couponMapper.updateByPrimaryKeySelective(coupon);
    }

    @Override
    public MallCoupon getForOrder(Integer couponId) {
        return couponMapper.getForOrder(couponId);
    }

    @Override
    public void deleteCache() {
        redisService.deleteByPrefix(MallCouponServiceImpl.class, "getVoPage");
    }
}
