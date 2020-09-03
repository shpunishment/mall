package com.shpun.mall.common.service;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallCart;
import com.shpun.mall.common.model.vo.MallCartVo;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:36
 */
public interface MallCartService {

    void deleteByPrimaryKey(Integer cartId);

    void insertSelective(MallCart record);

    MallCart selectByPrimaryKey(Integer cartId);

    void updateByPrimaryKeySelective(MallCart record);

    void deleteAllByUserId(Integer userId);

    List<MallCart> getByCartIdList(List<Integer> cartIdList);

    void deleteBatch(List<Integer> cartIdList);

    List<MallCartVo> getVoByUserId(Integer userId);

    MallCartVo getVoByUserIdAndProductId(Integer userId, Integer productId);

    PageInfo<MallCartVo> getVoPageByUserId(Integer userId, Integer offset, Integer limit);

    void deleteCache(Integer userId);

}
