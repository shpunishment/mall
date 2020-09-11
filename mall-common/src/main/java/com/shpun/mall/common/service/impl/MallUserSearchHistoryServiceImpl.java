package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.aop.RedisCache;
import com.shpun.mall.common.mapper.MallUserSearchHistoryMapper;
import com.shpun.mall.common.model.MallUserSearchHistory;
import com.shpun.mall.common.service.MallUserSearchHistoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:41
 */
@Service("mallUserSearchHistoryService")
public class MallUserSearchHistoryServiceImpl implements MallUserSearchHistoryService {

    @Autowired
    private MallUserSearchHistoryMapper userSearchHistoryMapper;

    @Override
    public void deleteByPrimaryKey(Integer historyId) {
        userSearchHistoryMapper.deleteByPrimaryKey(historyId);
    }

    @Override
    public MallUserSearchHistory selectByPrimaryKey(Integer historyId) {
        return userSearchHistoryMapper.selectByPrimaryKey(historyId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertOrUpdate(Integer userId, String keyword, Integer type) {
        if (StringUtils.isNotBlank(keyword)) {
            MallUserSearchHistory userSearchHistory = userSearchHistoryMapper.getByUserIdAndKeywordAndType(userId, keyword, type);
            if (userSearchHistory != null) {
                // 获取最大，将 当前sn<   <=maxSn 之间的，减1
                Integer maxSn = userSearchHistoryMapper.getMaxSnByUserIdAndType(userId, type);
                userSearchHistoryMapper.goNext(userId, type, userSearchHistory.getSn(), maxSn);
                userSearchHistory.setSn(maxSn);
                userSearchHistory.setUpdateTime(new Date());
                userSearchHistoryMapper.updateByPrimaryKeySelective(userSearchHistory);
            } else {
                MallUserSearchHistory record = new MallUserSearchHistory();
                record.setUserId(userId);
                record.setKeyword(keyword);
                record.setCreateTime(new Date());
                // 新插入获取最大的sn
                Integer maxSn = userSearchHistoryMapper.getMaxSnByUserIdAndType(userId, type);
                record.setSn(maxSn == null ? 1 : maxSn + 1);
                record.setType(type);
                userSearchHistoryMapper.insertSelective(record);
            }
        }
    }

    @Override
    public List<MallUserSearchHistory> getByUserId(Integer userId, Integer type) {
        return userSearchHistoryMapper.getByUserId(userId, type);
    }

    @Override
    public void deleteByUserId(Integer userId, Integer type) {
        userSearchHistoryMapper.deleteByUserId(userId, type);
    }

    @Override
    public List<String> getTop10ByUserId(Integer userId, Integer type) {
        return userSearchHistoryMapper.getTop10ByUserId(userId, type);
    }

    @RedisCache
    @Override
    public List<String> getHotByType(Integer limit, Integer type) {
        return userSearchHistoryMapper.getHotByType(limit, type);
    }
}
