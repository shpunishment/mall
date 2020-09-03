package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallUserSearchHistory;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:38
 */
public interface MallUserSearchHistoryService {

    void deleteByPrimaryKey(Integer historyId);

    MallUserSearchHistory selectByPrimaryKey(Integer historyId);

    void insertOrUpdate(Integer userId, String keyword, Integer type);

    List<MallUserSearchHistory> getByUserId(Integer userId, Integer type);

    void deleteByUserId(Integer userId, Integer type);

    List<String> getTop10ByUserId(Integer userId, Integer type);

    List<String> getHotByType(Integer limit, Integer type);

}
