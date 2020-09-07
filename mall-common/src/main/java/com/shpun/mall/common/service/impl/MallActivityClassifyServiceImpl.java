package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.aop.RedisCache;
import com.shpun.mall.common.mapper.MallActivityClassifyMapper;
import com.shpun.mall.common.model.MallActivityClassify;
import com.shpun.mall.common.model.vo.MallActivityClassifyVo;
import com.shpun.mall.common.service.MallActivityClassifyService;
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
@Service("mallActivityClassifyService")
public class MallActivityClassifyServiceImpl implements MallActivityClassifyService {

    @Autowired
    private MallActivityClassifyMapper activityClassifyMapper;

    @Override
    public void deleteByPrimaryKey(Integer classifyId) {
        activityClassifyMapper.deleteByPrimaryKey(classifyId);
    }

    @Override
    public void insertSelective(MallActivityClassify record) {
        record.setCreateTime(new Date());
        Integer maxSn = activityClassifyMapper.getMaxSnByActivityId(record.getActivityId());
        record.setSn(maxSn == null ? 1 : maxSn + 1);
        activityClassifyMapper.insertSelective(record);
    }

    @Override
    public MallActivityClassify selectByPrimaryKey(Integer classifyId) {
        return activityClassifyMapper.selectByPrimaryKey(classifyId);
    }

    @Override
    public void updateByPrimaryKeySelective(MallActivityClassify record) {
        record.setUpdateTime(new Date());
        activityClassifyMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void up(Integer classifyId) {
        MallActivityClassify activityClassify = this.selectByPrimaryKey(classifyId);
        MallActivityClassify prev = activityClassifyMapper.getPrev(activityClassify);
        if (prev != null) {
            this.exchangeSn(prev, activityClassify);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void down(Integer classifyId) {
        MallActivityClassify activityClassify = this.selectByPrimaryKey(classifyId);
        MallActivityClassify next = activityClassifyMapper.getNext(activityClassify);
        if (next != null) {
            this.exchangeSn(next, activityClassify);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void top(Integer classifyId) {
        MallActivityClassify activityClassify = this.selectByPrimaryKey(classifyId);
        Integer topSn = activityClassifyMapper.getMinSnByActivityId(activityClassify.getActivityId());
        activityClassifyMapper.goNext(activityClassify.getActivityId(), topSn, activityClassify.getSn());

        activityClassify.setSn(topSn);
        this.updateByPrimaryKeySelective(activityClassify);
    }

    /**
     * 交换两个分类的排序号
     * @param oldActivityClassify
     * @param newActivityClassify
     */
    private void exchangeSn(MallActivityClassify oldActivityClassify, MallActivityClassify newActivityClassify) {
        Integer tempSn = oldActivityClassify.getSn();
        oldActivityClassify.setSn(newActivityClassify.getSn());
        newActivityClassify.setSn(tempSn);

        this.updateByPrimaryKeySelective(oldActivityClassify);
        this.updateByPrimaryKeySelective(newActivityClassify);
    }

    @RedisCache
    @Override
    public List<MallActivityClassifyVo> getVoByActivityId(Integer activityId) {
        return activityClassifyMapper.getVoByActivityId(activityId);
    }

}
