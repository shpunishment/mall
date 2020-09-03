package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.mapper.MallAdminMapper;
import com.shpun.mall.common.model.MallAdmin;
import com.shpun.mall.common.service.MallAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:39
 */
@Service("mallAdminService")
public class MallAdminServiceImpl implements MallAdminService {

    @Autowired
    private MallAdminMapper adminMapper;

    @Override
    public void deleteByPrimaryKey(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public void insertSelective(MallAdmin record) {
        record.setCreateTime(new Date());
        adminMapper.insertSelective(record);
    }

    @Override
    public MallAdmin selectByPrimaryKey(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public void updateByPrimaryKeySelective(MallAdmin record) {
        record.setUpdateTime(new Date());
        adminMapper.updateByPrimaryKeySelective(record);
    }
}
