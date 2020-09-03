package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallUserSearchHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallUserSearchHistoryMapper {

    int deleteByPrimaryKey(Integer historyId);

    int insertSelective(MallUserSearchHistory record);

    MallUserSearchHistory selectByPrimaryKey(Integer historyId);

    int updateByPrimaryKeySelective(MallUserSearchHistory record);

    MallUserSearchHistory getByUserIdAndKeywordAndType(@Param("userId") Integer userId,
                                                       @Param("keyword") String keyword,
                                                       @Param("type") Integer type);

    Integer getMaxSnByUserIdAndType(@Param("userId") Integer userId, @Param("type") Integer type);

    Integer getMinSnByUserIdAndType(@Param("userId") Integer userId, @Param("type") Integer type);

    /**
     * 将[startSn,endSn)的sn都加1，向后移动一位
     * @param userId
     * @param type
     * @param startSn
     * @param endSn
     */
    void goNext(@Param("userId") Integer userId,
                @Param("type") Integer type,
                @Param("startSn") Integer startSn,
                @Param("endSn") Integer endSn);

    List<MallUserSearchHistory> getByUserId(@Param("userId") Integer userId,
                                            @Param("type") Integer type);

    void deleteByUserId(@Param("userId") Integer userId,
                        @Param("type") Integer type);

    List<String> getTop10ByUserId(@Param("userId") Integer userId,
                                  @Param("type") Integer type);

    List<String> getHotByType(@Param("limit") Integer limit,
                              @Param("type") Integer type);

}