package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.mapper.MallCouponClassifyMapper;
import com.shpun.mall.common.model.MallCouponClassify;
import com.shpun.mall.common.service.MallCouponClassifyService;
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
@Service("mallCouponClassifyService")
public class MallCouponClassifyServiceImpl implements MallCouponClassifyService {

    @Autowired
    private MallCouponClassifyMapper couponClassifyMapper;

    @Override
    public void deleteByPrimaryKey(Integer id) {
        couponClassifyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(MallCouponClassify record) {
        record.setCreateTime(new Date());
        couponClassifyMapper.insertSelective(record);
    }

    @Override
    public MallCouponClassify selectByPrimaryKey(Integer id) {
        return couponClassifyMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKeySelective(MallCouponClassify record) {
        couponClassifyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void insertBatch(List<Integer> classifyIdList, Integer couponId) {
        List<MallCouponClassify> couponClassifyList = new ArrayList<>(classifyIdList.size());
        classifyIdList.forEach(classifyId -> {
            MallCouponClassify couponClassify = new MallCouponClassify();
            couponClassify.setCreateTime(new Date());
            couponClassify.setCreateId(Const.ADMIN_ID);
            couponClassify.setClassifyId(classifyId);
            couponClassify.setCouponId(couponId);
            couponClassifyList.add(couponClassify);
        });
        couponClassifyMapper.insertBatch(couponClassifyList);
    }

    @Transactional
    @Override
    public void updateBatch(List<Integer> newClassifyIdList, Integer couponId) {
        List<Integer> oldClassifyIdList = couponClassifyMapper.getClassifyIdByCouponId(couponId);

        // 删除：循环旧list，每个id去新list中contain，没有存在就是删除
        List<Integer> deleteList = oldClassifyIdList.stream().filter(id -> !newClassifyIdList.contains(id)).collect(Collectors.toList());
        // 新增：循环新list，每个id去旧list中contain，没有存在就是新增的
        List<Integer> insertList = newClassifyIdList.stream().filter(id -> !oldClassifyIdList.contains(id)).collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(deleteList)) {
            couponClassifyMapper.deleteBatch(deleteList, couponId);
        }
        if (CollectionUtils.isNotEmpty(insertList)) {
            this.insertBatch(insertList, couponId);
        }
    }
}
