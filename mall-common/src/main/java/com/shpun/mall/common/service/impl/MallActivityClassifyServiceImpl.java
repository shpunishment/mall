package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.mapper.MallActivityClassifyMapper;
import com.shpun.mall.common.model.MallActivityClassify;
import com.shpun.mall.common.service.MallActivityClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
