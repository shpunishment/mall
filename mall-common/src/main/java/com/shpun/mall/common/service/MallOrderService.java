package com.shpun.mall.common.service;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallOrder;
import com.shpun.mall.common.model.MallProduct;
import com.shpun.mall.common.model.vo.MallOrderVo;
import com.shpun.mall.common.model.vo.MallUserCouponVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:37
 */
public interface MallOrderService {

    void deleteByPrimaryKey(Integer orderId);

    void insertSelective(MallOrder record);

    MallOrder selectByPrimaryKey(Integer orderId);

    void updateByPrimaryKeySelective(MallOrder record);

    /**
     * 计算订单价格
     * @param cartIdList
     * @return
     */
    MallOrder calculatePrice(List<Integer> cartIdList);

    /**
     * 根据优惠券计算价格
     * @param cartIdList
     * @param couponId
     * @return
     */
    MallOrder calculatePrice(List<Integer> cartIdList, Integer couponId);

    /**
     * 生成订单
     * @param order 需要用户id，地址id，订单备注，期望送达时间，支付方式，
     * @param cartIdList
     * @return
     */
    void generateOrder(MallOrder order, List<Integer> cartIdList);

    List<MallOrder> getByUserId(Integer userId);

    /**
     * 关闭订单
     * @param orderId
     */
    void closeOrder(Integer orderId, Integer userId);

    /**
     * 支付成功
     * @param orderId
     */
    void paySuccess(Integer orderId);

    /**
     * 待收货
     * @param order 配送员名称
     */
    void delivering(MallOrder order);

    /**
     * 收货成功
     * @param orderId
     */
    void receiveSuccess(Integer orderId);

    /**
     * 评价成功
     * @param order
     */
    void commentSuccess(MallOrder order);

    List<MallOrderVo> getVoListByFilter(Integer userId, Integer status);

    PageInfo<MallOrderVo> getVoPageByFilter(Integer userId, Integer status, Integer offset, Integer limit);

    MallOrderVo getDetailVo(Integer orderId);

    List<MallOrderVo> getVoByProductName(Integer userId, String productName);

    PageInfo<MallOrderVo> getVoByProductName(Integer userId, String productName, Integer offset, Integer limit);

    List<MallOrder> getList();

    /**
     * 下单成功，取消订单，评价订单，根据用户id删除缓存
     * @param userId
     */
    void deleteCache(Integer userId);

}
