package com.shpun.mall.common.service.impl;

import com.github.pagehelper.PageException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.aop.RedisCache;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.enums.MallCouponTimeTypeEnums;
import com.shpun.mall.common.enums.MallCouponTypeEnums;
import com.shpun.mall.common.enums.MallCouponUseTypeEnums;
import com.shpun.mall.common.enums.MallUserCouponStatusEnums;
import com.shpun.mall.common.exception.MallError;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.mapper.MallUserCouponMapper;
import com.shpun.mall.common.model.MallCoupon;
import com.shpun.mall.common.model.MallUserCoupon;
import com.shpun.mall.common.model.vo.MallUserCouponVo;
import com.shpun.mall.common.service.MallCouponService;
import com.shpun.mall.common.service.MallUserCouponService;
import com.shpun.mall.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/2 14:53
 */
@Service("mallUserCouponService")
public class MallUserCouponServiceImpl implements MallUserCouponService {

    @Autowired
    private MallUserCouponMapper userCouponMapper;

    @Autowired
    private MallCouponService couponService;

    @Autowired
    private RedisService redisService;

    @Override
    public void deleteByPrimaryKey(Integer id) {
        userCouponMapper.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertSelective(MallUserCoupon record) {
        Integer couponId = record.getCouponId();
        MallCoupon coupon = couponService.lockCoupon(couponId);
        if (coupon == null) {
            throw new MallException(MallError.MallErrorEnum.COUPON_GET_ERROR);
        }

        MallUserCoupon isExist = this.getByUserIdAndCouponId(record.getUserId(), couponId);
        if (isExist != null) {
            throw new MallException(MallError.MallErrorEnum.COUPON_REPEAT_ERROR);
        }

        // 根据时间类型计算券使用期限
        if (MallCouponTimeTypeEnums.DAY.getValue().equals(coupon.getTimeType())) {
            Integer days = coupon.getDays();
            record.setStartTime(new Date());
            record.setEndTime(getEndTimeByDays(days));
        } else if (MallCouponTimeTypeEnums.PERIOD.getValue().equals(coupon.getTimeType())) {
            record.setStartTime(coupon.getStartTime());
            record.setEndTime(coupon.getEndTime());
        }

        record.setCreateTime(new Date());
        userCouponMapper.insertSelective(record);

        // 通用券减数量
        if (MallCouponTypeEnums.UNIVERSAL.getValue().equals(coupon.getType())) {
            coupon.setTotal(coupon.getTotal() - 1);
            couponService.update(coupon);
        }
    }

    /**
     * 根据天数获取结束时间
     * @param days
     * @return
     */
    private Date getEndTimeByDays(Integer days) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar addDays = Calendar.getInstance();
        addDays.add(Calendar.DAY_OF_MONTH, days);

        String date = dateFormat.format(addDays.getTime());
        SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date result;
        try {
            result = datetimeFormat.parse(date + " 23:59:59");
        } catch (ParseException e) {
            throw new MallException(MallError.MallErrorEnum.INTERNAL_SYSTEM_ERROR);
        }
        return result;
    }

    @Override
    public MallUserCoupon selectByPrimaryKey(Integer id) {
        return userCouponMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKeySelective(MallUserCoupon record) {
        userCouponMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<MallUserCouponVo> getVoListByFilter(Integer userId, Integer status) {
        return userCouponMapper.getVoListByFilter(userId, status);
    }

    @RedisCache
    @Override
    public PageInfo<MallUserCouponVo> getVoPageByFilter(Integer userId, Integer status, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);
        return new PageInfo<>(this.getVoListByFilter(userId, status));
    }

    @Override
    public List<Integer> getReceivedCouponId(Integer userId, List<Integer> couponIdList) {
        return userCouponMapper.getReceivedCouponId(userId, couponIdList);
    }

    @Override
    public MallUserCoupon getByUserIdAndCouponId(Integer userId, Integer couponId) {
        return userCouponMapper.getByUserIdAndCouponId(userId, couponId);
    }

    @RedisCache
    @Override
    public Integer getTodayUseCount(Integer userId) {
        return userCouponMapper.getTodayUseCount(userId);
    }

    @Override
    public MallUserCoupon canUse(Integer userId, Integer couponId) {
        Integer todayUseCount = this.getTodayUseCount(userId);
        if (todayUseCount < Const.TODAY_USE_COUPON_COUNT) {
            MallUserCoupon userCoupon = userCouponMapper.canUse(userId, couponId);
            if (userCoupon != null) {
                return userCoupon;
            }
        }
        return null;
    }

    @Override
    public void deleteCache(Integer userId) {
        redisService.deleteByPrefix(MallUserCouponServiceImpl.class, "getVoPageByFilter", userId);
        redisService.deleteByPrefix(MallUserCouponServiceImpl.class, "getTodayUseCount", userId);
    }

    @Override
    public List<MallUserCouponVo> getAvailableVoList(Integer userId) {
        return userCouponMapper.getAvailableVoList(userId);
    }
}
