package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.mapper.MallActivityClassifyProductMapper;
import com.shpun.mall.common.model.MallActivityClassifyProduct;
import com.shpun.mall.common.service.MallActivityClassifyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/6 16:02
 */
@Service("mallActivityClassifyProductService")
public class MallActivityClassifyProductServiceImpl implements MallActivityClassifyProductService {

    @Autowired
    private MallActivityClassifyProductMapper activityClassifyProductMapper;

    @Override
    public void deleteByPrimaryKey(Integer id) {
        activityClassifyProductMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(MallActivityClassifyProduct record) {
        record.setCreateTime(new Date());
        activityClassifyProductMapper.insertSelective(record);
    }

    @Override
    public MallActivityClassifyProduct selectByPrimaryKey(Integer id) {
        return activityClassifyProductMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKeySelective(MallActivityClassifyProduct record) {
        activityClassifyProductMapper.updateByPrimaryKeySelective(record);
    }
}
