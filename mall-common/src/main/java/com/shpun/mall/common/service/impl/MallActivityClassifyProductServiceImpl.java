package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.mapper.MallActivityClassifyProductMapper;
import com.shpun.mall.common.model.MallActivityClassifyProduct;
import com.shpun.mall.common.model.vo.MallProductVo;
import com.shpun.mall.common.service.MallActivityClassifyProductService;
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
        Integer maxSn = activityClassifyProductMapper.getMaxSnByClassifyId(record.getClassifyId());
        record.setSn(maxSn == null ? 1 : maxSn + 1);
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

    @Transactional
    @Override
    public void up(Integer id) {
        MallActivityClassifyProduct activityClassifyProduct = this.selectByPrimaryKey(id);
        MallActivityClassifyProduct prev = activityClassifyProductMapper.getPrev(activityClassifyProduct);
        if (prev != null) {
            this.exchangeSn(prev, activityClassifyProduct);
        }
    }

    @Transactional
    @Override
    public void down(Integer id) {
        MallActivityClassifyProduct activityClassifyProduct = this.selectByPrimaryKey(id);
        MallActivityClassifyProduct next = activityClassifyProductMapper.getNext(activityClassifyProduct);
        if (next != null) {
            this.exchangeSn(next, activityClassifyProduct);
        }
    }

    @Transactional
    @Override
    public void top(Integer id) {
        MallActivityClassifyProduct activityClassifyProduct = this.selectByPrimaryKey(id);
        Integer topSn = activityClassifyProductMapper.getMinSnByClassifyId(activityClassifyProduct.getClassifyId());
        activityClassifyProductMapper.goNext(activityClassifyProduct.getClassifyId(), topSn, activityClassifyProduct.getSn());

        activityClassifyProduct.setSn(topSn);
        this.updateByPrimaryKeySelective(activityClassifyProduct);
    }

    /**
     * 交换两个分类的排序号
     * @param oldActivityClassifyProduct
     * @param newActivityClassifyProduct
     */
    private void exchangeSn(MallActivityClassifyProduct oldActivityClassifyProduct, MallActivityClassifyProduct newActivityClassifyProduct) {
        Integer tempSn = oldActivityClassifyProduct.getSn();
        oldActivityClassifyProduct.setSn(newActivityClassifyProduct.getSn());
        newActivityClassifyProduct.setSn(tempSn);

        this.updateByPrimaryKeySelective(oldActivityClassifyProduct);
        this.updateByPrimaryKeySelective(newActivityClassifyProduct);
    }

    @Override
    public List<MallProductVo> getVoListByClassifyId(Integer classifyId) {
        return activityClassifyProductMapper.getVoListByClassifyId(classifyId);
    }
}
