package com.shpun.mall.common.service;

import com.shpun.mall.common.model.MallClassify;
import com.shpun.mall.common.model.vo.MallClassifyVo;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:36
 */
public interface MallClassifyService {

    void deleteByPrimaryKey(Integer classifyId);

    void insertSelective(MallClassify record);

    MallClassify selectByPrimaryKey(Integer classifyId);

    void updateByPrimaryKeySelective(MallClassify record);

    /**
     * 上移
     * @param classifyId
     */
    void up(Integer classifyId);

    /**
     * 下移
     * @param classifyId
     */
    void down(Integer classifyId);

    /**
     * 置顶
     * @param classifyId
     */
    void top(Integer classifyId);

    List<Integer> getClassifyIdByPid(Integer pid);

    List<MallClassify> getByPid(Integer pid);

    List<MallClassifyVo> getVoList();

    List<MallClassifyVo> getVoByPid(Integer pid);

}
