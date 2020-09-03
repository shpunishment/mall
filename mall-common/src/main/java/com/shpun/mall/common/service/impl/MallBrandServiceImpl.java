package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.mapper.MallBrandMapper;
import com.shpun.mall.common.model.MallBrand;
import com.shpun.mall.common.model.vo.MallBrandVo;
import com.shpun.mall.common.service.MallBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:40
 */
@Service("mallBrandService")
public class MallBrandServiceImpl implements MallBrandService {

    @Autowired
    private MallBrandMapper brandMapper;

    @Override
    public void deleteByPrimaryKey(Integer brandId) {
        brandMapper.deleteByPrimaryKey(brandId);
    }

    @Override
    public void insertSelective(MallBrand record) {
        record.setCreateTime(new Date());
        brandMapper.insertSelective(record);
    }

    @Override
    public MallBrand selectByPrimaryKey(Integer brandId) {
        return brandMapper.selectByPrimaryKey(brandId);
    }

    @Override
    public void updateByPrimaryKeySelective(MallBrand record) {
        record.setUpdateTime(new Date());
        brandMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public MallBrandVo getVoByBrandId(Integer brandId) {
        return brandMapper.getVoByBrandId(brandId);
    }

    @Override
    public List<MallBrand> getList() {
        return brandMapper.getList();
    }
}
