package com.shpun.mall.common.mapper;

import com.shpun.mall.common.model.MallClassify;
import com.shpun.mall.common.model.vo.MallClassifyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallClassifyMapper {

    int deleteByPrimaryKey(Integer classifyId);

    void deleteBatch(@Param("classifyIdList") List<Integer> classifyIdList);

    int insertSelective(MallClassify record);

    MallClassify selectByPrimaryKey(Integer classifyId);

    int updateByPrimaryKeySelective(MallClassify record);

    Integer getMaxSnByPid(Integer pid);

    Integer getMinSnByPid(Integer pid);

    List<Integer> getClassifyIdByPid(Integer pid);

    MallClassify getPrev(MallClassify classify);

    MallClassify getNext(MallClassify classify);

    /**
     * 将[startSn,endSn)的sn都加1，向后移动一位
     * @param pid
     * @param startSn
     * @param endSn
     */
    void goNext(@Param("pid") Integer pid, @Param("startSn") Integer startSn, @Param("endSn") Integer endSn);

    List<MallClassify> getByPid(Integer pid);

    List<MallClassifyVo> getAllVo();

    List<MallClassifyVo> getVoByPid(Integer pid);

}