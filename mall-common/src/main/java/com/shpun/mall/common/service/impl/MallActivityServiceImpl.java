package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.mapper.MallActivityMapper;
import com.shpun.mall.common.model.MallActivity;
import com.shpun.mall.common.model.vo.MallActivityVo;
import com.shpun.mall.common.service.MallActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/6 16:02
 */
@Service("mallActivityService")
public class MallActivityServiceImpl implements MallActivityService {

    @Autowired
    private MallActivityMapper activityMapper;

    @Override
    public void deleteByPrimaryKey(Integer activityId) {
        activityMapper.deleteByPrimaryKey(activityId);
    }

    @Override
    public void insertSelective(MallActivity record) {
        record.setCreateTime(new Date());
        Integer maxSn = activityMapper.getMaxSn();
        record.setSn(maxSn == null ? 1 : maxSn + 1);
        activityMapper.insertSelective(record);
    }

    @Override
    public MallActivity selectByPrimaryKey(Integer activityId) {
        return activityMapper.selectByPrimaryKey(activityId);
    }

    @Override
    public void updateByPrimaryKeySelective(MallActivity record) {
        record.setUpdateTime(new Date());
        activityMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public void up(Integer activityId) {
        MallActivity activity = this.selectByPrimaryKey(activityId);
        MallActivity prev = activityMapper.getPrev(activity);
        if (prev != null) {
            this.exchangeSn(prev, activity);
        }
    }

    @Transactional
    @Override
    public void down(Integer activityId) {
        MallActivity activity = this.selectByPrimaryKey(activityId);
        MallActivity next = activityMapper.getNext(activity);
        if (next != null) {
            this.exchangeSn(next, activity);
        }
    }

    @Transactional
    @Override
    public void top(Integer activityId) {
        MallActivity activity = this.selectByPrimaryKey(activityId);
        Integer topSn = activityMapper.getMinSn();
        activityMapper.goNext(topSn, activity.getSn());

        activity.setSn(topSn);
        this.updateByPrimaryKeySelective(activity);
    }

    /**
     * 交换两个分类的排序号
     * @param oldActivity
     * @param newActivity
     */
    private void exchangeSn(MallActivity oldActivity, MallActivity newActivity) {
        Integer tempSn = oldActivity.getSn();
        oldActivity.setSn(newActivity.getSn());
        newActivity.setSn(tempSn);

        this.updateByPrimaryKeySelective(oldActivity);
        this.updateByPrimaryKeySelective(newActivity);
    }

    @Override
    public List<MallActivityVo> getTodayVoList() {
        return activityMapper.getTodayVoList();
    }

    @Override
    public Boolean isActive(Integer activityId) {
        return activityMapper.isActive(activityId);
    }
}
