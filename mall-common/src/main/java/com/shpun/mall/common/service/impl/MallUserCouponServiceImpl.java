package com.shpun.mall.common.service.impl;

import com.github.pagehelper.PageException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.enums.MallCouponTimeTypeEnums;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.mapper.MallUserCouponMapper;
import com.shpun.mall.common.model.MallCoupon;
import com.shpun.mall.common.model.MallUserCoupon;
import com.shpun.mall.common.model.vo.MallUserCouponVo;
import com.shpun.mall.common.service.MallCouponService;
import com.shpun.mall.common.service.MallUserCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    @Override
    public void deleteByPrimaryKey(Integer id) {
        userCouponMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void insertSelective(MallUserCoupon record) {
        Integer couponId = record.getCouponId();
        MallCoupon coupon = couponService.lockCoupon(couponId);
        if (coupon == null) {
            throw new MallException("优惠券领取失败！");
        }

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

        // 优惠券减数量
        coupon.setTotal(coupon.getTotal() - 1);
        couponService.update(coupon);
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
            throw new MallException("系统内部异常");
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

    @Override
    public PageInfo<MallUserCouponVo> getVoPageByFilter(Integer userId, Integer status, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);
        return new PageInfo<>(this.getVoListByFilter(userId, status));
    }

    @Override
    public List<Integer> getAvailableCouponIdList(Integer userId) {
        return userCouponMapper.getAvailableCouponIdList(userId);
    }
}
