package com.shpun.mall.common.service;

import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.model.MallOrder;
import com.shpun.mall.common.model.MallProduct;
import com.shpun.mall.common.model.vo.MallCouponVo;
import com.shpun.mall.common.model.vo.MallOrderVo;
import com.shpun.mall.common.model.vo.MallUserCouponVo;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
     * @param userId
     * @param cartIdList
     * @return
     */
    MallOrder calculatePrice(Integer userId, List<Integer> cartIdList);

    /**
     * 根据优惠券计算价格
     * @param userId
     * @param cartIdList
     * @param couponId
     * @return
     */
    MallOrder calculatePrice(Integer userId, List<Integer> cartIdList, Integer couponId);

    /**
     * 根据优惠券计算价格
     * @param userId
     * @param cartIdList
     * @return
     */
    Map<String, List<MallUserCouponVo>> calculateWithUserCouponVo(Integer userId, List<Integer> cartIdList);

    /**
     * 适配前端修改
     * @param userId
     * @param cartIdList
     * @return
     */
    Map<String, List<MallCouponVo>> calculateWithCouponVo(Integer userId, List<Integer> cartIdList);

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
    void paySuccess(Integer orderId, String payNumber, Date payTime);

    /**
     * 待收货
     * @param orderId
     * @param deliveryId
     */
    void wait2Receive(Integer orderId, Integer deliveryId);

    /**
     * 收获成功
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

    MallOrderVo getDetailVo(Integer userId, Integer orderId);

    List<MallOrderVo> getVoByProductName(Integer userId, String productName);

    PageInfo<MallOrderVo> getVoByProductName(Integer userId, String productName, Integer offset, Integer limit);

    /**
     * @param status
     * @param orderTimeSort 1 顺序，2 逆序
     * @return
     */
    List<MallOrder> getList(Integer status, Integer orderTimeSort);

    /**
     * 待支付订单，选择支付订单
     * @param order 包括支付方式
     */
    void payOrder(MallOrder order, HttpServletResponse response);

    MallOrder getByFilter(String orderNumber, Integer status);

    /**
     * 添加订单超时延迟队列
     * @param userId
     * @param orderId
     * @param orderTime
     */
    void zAddOrderTimeout(Integer userId, Integer orderId, Date orderTime);

    /**
     * 添加待配送延迟队列
     * @param orderId
     * @param payTime
     */
    void zAddWait2DeliveryOrder(Integer orderId, Date payTime);

    List<Integer> getUserIdListByOrderIdList(List<Integer> orderIdList);

    /**
     * 下单成功，取消订单，评价订单，根据用户id删除缓存
     * @param userId
     */
    void deleteCache(Integer userId);

}
