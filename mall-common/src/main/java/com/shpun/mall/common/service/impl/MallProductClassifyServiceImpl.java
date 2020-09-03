package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.mapper.MallProductClassifyMapper;
import com.shpun.mall.common.model.MallProductClassify;
import com.shpun.mall.common.service.MallProductClassifyService;
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
 * @Date: 2020/8/22 10:41
 */
@Service("mallProductClassifyService")
public class MallProductClassifyServiceImpl implements MallProductClassifyService {

    @Autowired
    private MallProductClassifyMapper productClassifyMapper;

    @Override
    public void deleteByPrimaryKey(Integer id) {
        productClassifyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(MallProductClassify record) {
        productClassifyMapper.insertSelective(record);
    }

    @Override
    public MallProductClassify selectByPrimaryKey(Integer id) {
        return productClassifyMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKeySelective(MallProductClassify record) {
        productClassifyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void insertBatch(List<Integer> classifyIdList, Integer productId) {
        List<MallProductClassify> productClassifyList = new ArrayList<>(classifyIdList.size());
        classifyIdList.forEach(classifyId -> {
            MallProductClassify productClassify = new MallProductClassify();
            productClassify.setCreateTime(new Date());
            productClassify.setCreateId(Const.ADMIN_ID);
            productClassify.setClassifyId(classifyId);
            productClassify.setProductId(productId);
            productClassifyList.add(productClassify);
        });
        productClassifyMapper.insertBatch(productClassifyList);
    }

    @Transactional
    @Override
    public void updateBatch(List<Integer> newClassifyIdList, Integer productId) {
        List<Integer> oldClassifyIdList = this.getClassifyIdByProductId(productId);

        // 删除：循环旧list，每个id去新list中contain，没有存在就是删除
        List<Integer> deleteList = oldClassifyIdList.stream().filter(id -> !newClassifyIdList.contains(id)).collect(Collectors.toList());
        // 新增：循环新list，每个id去旧list中contain，没有存在就是新增的
        List<Integer> insertList = newClassifyIdList.stream().filter(id -> !oldClassifyIdList.contains(id)).collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(deleteList)) {
            productClassifyMapper.deleteBatch(deleteList, productId);
        }
        if (CollectionUtils.isNotEmpty(insertList)) {
            this.insertBatch(insertList, productId);
        }
    }

    @Override
    public List<Integer> getClassifyIdByProductId(Integer productId) {
        return productClassifyMapper.getClassifyIdByProductId(productId);
    }
}
