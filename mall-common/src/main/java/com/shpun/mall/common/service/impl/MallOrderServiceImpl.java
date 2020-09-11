package com.shpun.mall.common.service.impl;

import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.wap.models.AlipayTradeWapPayResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.aop.RedisCache;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.config.AlipayConfig;
import com.shpun.mall.common.config.ProfileConfig;
import com.shpun.mall.common.enums.*;
import com.shpun.mall.common.exception.MallError;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.mapper.MallOrderMapper;
import com.shpun.mall.common.model.*;
import com.shpun.mall.common.model.vo.MallCouponVo;
import com.shpun.mall.common.model.vo.MallOrderVo;
import com.shpun.mall.common.model.vo.MallUserCouponVo;
import com.shpun.mall.common.service.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 10:41
 */
@Service("mallOrderService")
public class MallOrderServiceImpl implements MallOrderService {

    @Autowired
    private MallOrderMapper orderMapper;

    @Autowired
    private MallCartService cartService;

    @Autowired
    private MallProductService productService;

    @Autowired
    private MallFlashItemService flashItemService;

    @Autowired
    private MallOrderItemService orderItemService;

    @Autowired
    private MallFlashService flashService;

    @Autowired
    private MallUserAddressService userAddressService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MallCouponService couponService;

    @Autowired
    private MallProductClassifyService productClassifyService;

    @Autowired
    private MallUserCouponService userCouponService;

    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private ProfileConfig profileConfig;

    /**
     * 订单编号自增后缀
     */
    private static final AtomicInteger ORDER_SEQ = new AtomicInteger(1000);

    @Override
    public void deleteByPrimaryKey(Integer orderId) {
        orderMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public void insertSelective(MallOrder record) {
        orderMapper.insertSelective(record);
    }

    @Override
    public MallOrder selectByPrimaryKey(Integer orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public void updateByPrimaryKeySelective(MallOrder record) {
        orderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public MallOrder calculatePrice(Integer userId, List<Integer> cartIdList) {
        if (CollectionUtils.isNotEmpty(cartIdList)) {
            List<MallCart> cartList = cartService.getByUserIdAndCartIdList(userId, cartIdList);
            BigDecimal productPrice = new BigDecimal("0.00");

            for(MallCart cart : cartList) {
                Integer productId = cart.getProductId();
                Integer quantity = cart.getQuantity();

                MallProduct product = productService.checkProduct(productId, quantity);
                BigDecimal price = product.getCurrentPrice().multiply(new BigDecimal(quantity));
                productPrice = productPrice.add(price);
            }

            MallOrder order = new MallOrder();
            this.setPrice(order, productPrice, null);
            return order;
        } else {
            throw new MallException(MallError.MallErrorEnum.CART_NULL);
        }
    }

    @Override
    public MallOrder calculatePrice(Integer userId, List<Integer> cartIdList, Integer couponId) {
        if (CollectionUtils.isNotEmpty(cartIdList)) {
            List<MallCart> cartList = cartService.getByUserIdAndCartIdList(userId, cartIdList);
            BigDecimal productPrice = new BigDecimal("0.00");

            // 获取优惠券中规定的分类id、商品id
            MallCoupon coupon = couponService.getForOrder(couponId);
            BigDecimal meetPrice = new BigDecimal("0.00");
            Integer useType = null;
            Set<Integer> meetIdSet = null;
            if (coupon != null) {
                useType = coupon.getUseType();
                if (MallCouponUseTypeEnums.CLASSIFY.getValue().equals(useType)) {
                    List<Integer> classifyIdList = couponService.getClassifyIdList(couponId);
                    meetIdSet = new HashSet<>(classifyIdList);
                } else if (MallCouponUseTypeEnums.PRODUCT.getValue().equals(useType)) {
                    List<Integer> productIdList = couponService.getProductIdList(couponId);
                    meetIdSet = new HashSet<>(productIdList);
                }
            }

            for(MallCart cart : cartList) {
                Integer productId = cart.getProductId();
                Integer quantity = cart.getQuantity();
                MallProduct product = productService.checkProduct(productId, quantity);
                BigDecimal price = product.getCurrentPrice().multiply(new BigDecimal(quantity));
                productPrice = productPrice.add(price);

                // 优惠券不为空，并且商品非限时抢购
                if (coupon != null && useType != null && !product.getFlashing()) {
                    if (MallCouponUseTypeEnums.ALL.getValue().equals(useType)) {
                        meetPrice = meetPrice.add(price);
                    } else if (MallCouponUseTypeEnums.CLASSIFY.getValue().equals(useType) && meetIdSet != null) {
                        // 判断商品的分类，是否存在于优惠券的分类中
                        List<Integer> classifyIdList = productClassifyService.getClassifyIdByProductId(productId);
                        List<Integer> meetClassifyIdList = classifyIdList.stream().filter(meetIdSet::contains).collect(Collectors.toList());
                        if (CollectionUtils.isNotEmpty(meetClassifyIdList)) {
                            meetPrice = meetPrice.add(price);
                        }
                    } else if (MallCouponUseTypeEnums.PRODUCT.getValue().equals(useType) && meetIdSet != null) {
                        // 判断商品，是否存在于优惠券的商品中
                        if (meetIdSet.contains(productId)) {
                            meetPrice = meetPrice.add(price);
                        }
                    }
                }
            }

            MallOrder order = new MallOrder();
            BigDecimal discount = null;
            if (coupon != null) {
                BigDecimal minPrice = coupon.getMinPrice();
                if (meetPrice.compareTo(minPrice) > -1) {
                    order.setCouponId(couponId);
                    discount = coupon.getDiscount();
                }
            }
            this.setPrice(order, productPrice, discount);
            return order;
        } else {
            throw new MallException(MallError.MallErrorEnum.CART_NULL);
        }
    }

    @Override
    public Map<String, List<MallUserCouponVo>> calculateWithUserCouponVo(Integer userId, List<Integer> cartIdList) {
        // 获取用户所有未使用的优惠券
        List<MallUserCouponVo> userCouponVoList = userCouponService.getVoListByFilter(userId, MallUserCouponStatusEnums.UNUSED.getValue());

        Map<String, List<MallUserCouponVo>> resultMap = null;
        if (CollectionUtils.isNotEmpty(userCouponVoList)) {
            resultMap = new HashMap<>(2);
            resultMap.put("can", new ArrayList<>(userCouponVoList.size()));
            resultMap.put("cannot", new ArrayList<>(userCouponVoList.size()));

            // 今日是否可用优惠券
            boolean canTodayUse = userCouponService.getTodayUseCount(userId) < Const.TODAY_USE_COUPON_COUNT;
            for (MallUserCouponVo userCouponVo : userCouponVoList) {
                if (canTodayUse) {
                    // 判断优惠券使用时间，是否可用
                    Date startTime = userCouponVo.getStartTime();
                    Date endTime = userCouponVo.getEndTime();
                    Date now = new Date();
                    if (startTime.compareTo(now) <= 0 && endTime.compareTo(now) >= 0) {
                        MallOrder order = this.calculatePrice(userId, cartIdList, userCouponVo.getCouponId());
                        if (order.getCouponId() != null) {
                            MallOrderVo orderVo = new MallOrderVo();
                            BeanUtils.copyProperties(order, orderVo);
                            userCouponVo.setOrderVo(orderVo);
                            resultMap.get("can").add(userCouponVo);
                        } else {
                            resultMap.get("cannot").add(userCouponVo);
                        }
                    } else {
                        resultMap.get("cannot").add(userCouponVo);
                    }
                } else {
                    resultMap.get("cannot").add(userCouponVo);
                }
            }
        }
        return resultMap;
    }

    @Override
    public Map<String, List<MallCouponVo>> calculateWithCouponVo(Integer userId, List<Integer> cartIdList) {
        // 获取用户所有未使用的优惠券
        List<MallCouponVo> couponVoList = userCouponService.getCouponVoListByFilter(userId, MallUserCouponStatusEnums.UNUSED.getValue());

        Map<String, List<MallCouponVo>> resultMap = null;
        if (CollectionUtils.isNotEmpty(couponVoList)) {
            resultMap = new HashMap<>(2);
            resultMap.put("can", new ArrayList<>(couponVoList.size()));
            resultMap.put("cannot", new ArrayList<>(couponVoList.size()));

            // 今日是否可用优惠券
            boolean canTodayUse = userCouponService.getTodayUseCount(userId) < Const.TODAY_USE_COUPON_COUNT;
            for (MallCouponVo couponVo : couponVoList) {
                if (canTodayUse) {
                    // 判断优惠券使用时间，是否可用
                    Date startTime = couponVo.getStartTime();
                    Date endTime = couponVo.getEndTime();
                    Date now = new Date();
                    if (startTime.compareTo(now) <= 0 && endTime.compareTo(now) >= 0) {
                        MallOrder order = this.calculatePrice(userId, cartIdList, couponVo.getCouponId());
                        if (order.getCouponId() != null) {
                            MallOrderVo orderVo = new MallOrderVo();
                            BeanUtils.copyProperties(order, orderVo);
                            couponVo.setOrderVo(orderVo);
                            resultMap.get("can").add(couponVo);
                        } else {
                            resultMap.get("cannot").add(couponVo);
                        }
                    } else {
                        resultMap.get("cannot").add(couponVo);
                    }
                } else {
                    resultMap.get("cannot").add(couponVo);
                }
            }
        }
        return resultMap;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void generateOrder(MallOrder order, List<Integer> cartIdList) {
        if (CollectionUtils.isNotEmpty(cartIdList)) {
            // 订单地址
            this.setAddress(order);

            List<MallCart> cartList = cartService.getByUserIdAndCartIdList(order.getUserId(), cartIdList);
            List<MallProduct> productList = new ArrayList<>(cartList.size());
            List<MallFlashItem> flashItemList = new ArrayList<>(cartList.size());
            List<MallOrderItem> orderItemList = new ArrayList<>(cartList.size());
            BigDecimal productPrice = new BigDecimal("0.00");

            // 判断优惠券是否可用，获取优惠券中规定的分类id、商品id
            Integer couponId = order.getCouponId();
            BigDecimal meetPrice = new BigDecimal("0.00");
            MallCoupon coupon = null;
            MallUserCoupon userCoupon = null;
            Integer useType = null;
            Set<Integer> meetIdSet = null;
            boolean canUseCoupon = false;
            if (couponId != null) {
                coupon = couponService.getForOrder(couponId);
                userCoupon = userCouponService.canUse(order.getUserId(), couponId);
                if (coupon != null && userCoupon != null) {
                    canUseCoupon = true;
                    useType = coupon.getUseType();
                    if (MallCouponUseTypeEnums.CLASSIFY.getValue().equals(useType)) {
                        List<Integer> classifyIdList = couponService.getClassifyIdList(couponId);
                        meetIdSet = new HashSet<>(classifyIdList);
                    } else if (MallCouponUseTypeEnums.PRODUCT.getValue().equals(useType)) {
                        List<Integer> productIdList = couponService.getProductIdList(couponId);
                        meetIdSet = new HashSet<>(productIdList);
                    }
                }
            }

            Integer productAmount = 0;

            for(MallCart cart : cartList) {
                Integer productId = cart.getProductId();
                Integer quantity = cart.getQuantity();
                productAmount += quantity;

                // 锁定商品库存
                MallProduct product = productService.lockStock(productId);
                if (product.getPublishStatus() == 1) {
                    Integer stock;
                    BigDecimal unitPrice;

                    // 判断当前商品是否限时抢购
                    boolean flashing = false;
                    MallFlashItem flashItemTemp = flashItemService.getFlashByProductId(product.getProductId());
                    MallFlashItem flashItem = null;
                    if (flashItemTemp != null) {
                        flashing = true;
                        // 锁定限时抢购商品库存
                        flashItem = flashItemService.lockStock(flashItemTemp.getFlashItemId());
                        stock = flashItem.getStock();
                        unitPrice = flashItem.getPrice();
                    } else {
                        stock = product.getStock();
                        unitPrice = product.getCurrentPrice();
                    }

                    if (stock < quantity) {
                        throw new MallException(MallError.MallErrorEnum.STOCK_NULL.format(product.getProductName()));
                    } else {
                        // 减库存，加销量
                        if (flashing) {
                            flashItem.setStock(stock - quantity);
                            flashItem.setSales(flashItem.getSales() + quantity);
                            flashItemList.add(flashItem);
                        } else {
                            product.setStock(stock - quantity);
                            product.setSales(product.getSales() + quantity);
                            productList.add(product);
                        }

                        // 计算价格
                        BigDecimal price = unitPrice.multiply(new BigDecimal(quantity));
                        productPrice = productPrice.add(price);

                        // 优惠券不为空，并且商品非限时抢购
                        if (canUseCoupon && coupon != null && useType != null && !flashing) {
                            if (MallCouponUseTypeEnums.ALL.getValue().equals(useType)) {
                                meetPrice = meetPrice.add(price);
                            } else if (MallCouponUseTypeEnums.CLASSIFY.getValue().equals(useType) && meetIdSet != null) {
                                // 判断商品的分类，是否存在于优惠券的分类中
                                List<Integer> classifyIdList = productClassifyService.getClassifyIdByProductId(productId);
                                List<Integer> meetClassifyIdList = classifyIdList.stream().filter(meetIdSet::contains).collect(Collectors.toList());
                                if (CollectionUtils.isNotEmpty(meetClassifyIdList)) {
                                    meetPrice = meetPrice.add(price);
                                }
                            } else if (MallCouponUseTypeEnums.PRODUCT.getValue().equals(useType) && meetIdSet != null) {
                                // 判断商品，是否存在于优惠券的商品中
                                if (meetIdSet.contains(productId)) {
                                    meetPrice = meetPrice.add(price);
                                }
                            }
                        }

                        // 订单商品
                        MallOrderItem orderItem = new MallOrderItem();
                        orderItem.setProductId(productId);
                        orderItem.setProductName(product.getProductName());
                        orderItem.setPic(product.getPic());
                        orderItem.setPrice(unitPrice);
                        orderItem.setQuantity(quantity);
                        orderItem.setFlashItemId(flashing ? flashItem.getFlashItemId() : Const.DEFAULT_NO_FLASH_ITEM_ID);
                        orderItemList.add(orderItem);
                    }
                } else {
                    throw new MallException(MallError.MallErrorEnum.OFF_SHELF.format(product.getProductName()));
                }
            }

            // 删除购物车
            cartService.deleteBatch(cartIdList);
            // 更新商品，减库存，加销量
            productService.updateBatch(productList);
            // 更新限时抢购商品，减库存，加销量
            flashItemService.updateBatch(flashItemList);

            // 计算价格
            BigDecimal discount = null;
            if (canUseCoupon && coupon != null) {
                BigDecimal minPrice = coupon.getMinPrice();
                if (meetPrice.compareTo(minPrice) > -1) {
                    order.setCouponId(couponId);
                    discount = coupon.getDiscount();
                }
            }
            this.setPrice(order, productPrice, discount);

            String orderNumber = generateOrderNumber();
            order.setOrderNumber(orderNumber);
            order.setOrderTime(new Date());
            order.setProductAmount(productAmount);
            orderMapper.insertSelective(order);

            // 用户优惠券更新
            if (canUseCoupon && userCoupon != null) {
                userCoupon.setStatus(MallUserCouponStatusEnums.USED.getValue());
                userCoupon.setUseTime(new Date());
                userCoupon.setOrderId(order.getOrderId());
                userCouponService.updateByPrimaryKeySelective(userCoupon);
            }

            // 订单商品
            for(MallOrderItem orderItem : orderItemList) {
                orderItem.setOrderId(order.getOrderId());
                orderItem.setOrderNumber(orderNumber);
            }
            orderItemService.insertBatch(orderItemList);
        } else {
            throw new MallException(MallError.MallErrorEnum.CART_NULL);
        }
    }

    /**
     * 设值商品价格，运费价格，优惠价格和总价
     * @param order
     * @param productPrice
     */
    private void setPrice(MallOrder order, BigDecimal productPrice, BigDecimal couponPrice) {
        order.setProductPrice(productPrice);
        order.setCouponPrice(couponPrice);
        // 商品价格超过，则不用运费
        BigDecimal totalPrice;
        if (productPrice.compareTo(Const.PRODUCT_PRICE) > -1) {
            order.setDeliveryPrice(Const.FREE_DELIVERY);
            totalPrice = productPrice;
        } else {
            order.setDeliveryPrice(Const.DELIVERY_PRICE);
            totalPrice = productPrice.add(Const.DELIVERY_PRICE);
        }
        if (couponPrice != null) {
            order.setTotalPrice(totalPrice.subtract(couponPrice));
        } else {
            order.setTotalPrice(totalPrice);
        }
    }

    /**
     * 设值订单地址
     * @param order
     */
    private void setAddress(MallOrder order) {
        MallUserAddress userAddress = userAddressService.getByUserIdAndAddressId(order.getUserId(), order.getAddressId());
        if (userAddress == null) {
            throw new MallException(MallError.MallErrorEnum.INTERNAL_SYSTEM_ERROR);
        }

        StringBuilder receiveNameSb = new StringBuilder(userAddress.getName());
        if (!userAddress.getSex().equals(MallUserAddressSexEnums.NO.getValue())) {
            receiveNameSb.append("(").append(MallUserAddressSexEnums.getEnum(userAddress.getSex()).getName()).append(")");
        }
        order.setReceiveName(receiveNameSb.toString());
        order.setReceivePhone(userAddress.getPhone());
        order.setReceiveAddress(userAddress.getAddress());
    }

    /**
     * 生成订单号
     * @return
     */
    private String generateOrderNumber() {
        if(ORDER_SEQ.intValue() > 9990){
            ORDER_SEQ.getAndSet(1000);
        }
        return new Date().getTime() + "" + ORDER_SEQ.getAndIncrement();
    }

    @Override
    public List<MallOrder> getByUserId(Integer userId) {
        return orderMapper.getByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void closeOrder(Integer orderId, Integer userId) {
        // 查找订单是否存在
        MallOrder order = this.selectByPrimaryKey(orderId);
        if (!order.getUserId().equals(userId)) {
            throw new MallException(MallError.MallErrorEnum.INTERNAL_SYSTEM_ERROR);
        }

        order.setStatus(MallOrderStatusEnums.CLOSE.getValue());
        order.setEndTime(new Date());
        this.updateByPrimaryKeySelective(order);

        // 商品库存回滚
        List<MallOrderItem> orderItemList = orderItemService.getByOrderId(orderId);
        List<MallProduct> productList = new ArrayList<>(orderItemList.size());
        List<MallFlashItem> flashItemList = new ArrayList<>(orderItemList.size());
        for (MallOrderItem orderItem : orderItemList) {
            Integer quantity = orderItem.getQuantity();
            Integer flashItemId = orderItem.getFlashItemId();
            if (flashItemId.equals(Const.DEFAULT_NO_FLASH_ITEM_ID)) {
                Integer productId = orderItem.getProductId();
                MallProduct product = productService.lockStock(productId);

                // 加库存，减销量
                product.setStock(product.getStock() + quantity);
                product.setSales(product.getSales() - quantity);
                productList.add(product);
            } else {
                MallFlashItem flashItem = flashItemService.lockStock(flashItemId);
                // 判断该限时抢购是否还在进行
                if (flashService.isFlashing(flashItem.getFlashId())) {
                    // 加库存，减销量
                    flashItem.setStock(flashItem.getStock() + quantity);
                    flashItem.setSales(flashItem.getSales() - quantity);
                    flashItemList.add(flashItem);
                }
            }
        }

        // 更新商品，加库存，减销量
        productService.updateBatch(productList);
        // 更新限时抢购商品，加库存，减销量
        flashItemService.updateBatch(flashItemList);
        // 返还优惠券
        Integer couponId = order.getCouponId();
        if (couponId != null) {
            MallUserCoupon userCoupon = userCouponService.getByUserIdAndCouponId(userId, couponId);
            userCoupon.setStatus(MallUserCouponStatusEnums.UNUSED.getValue());
            userCoupon.setUseTime(null);
            userCoupon.setOrderId(null);
            userCouponService.updateByPrimaryKeySelective(userCoupon);
        }
    }

    @Override
    public void paySuccess(Integer orderId, String payNumber, Date payTime) {
        MallOrder order = new MallOrder();
        order.setOrderId(orderId);
        order.setStatus(MallOrderStatusEnums.PAID.getValue());
        order.setPayNumber(payNumber);
        order.setPayTime(payTime);
        this.updateByPrimaryKeySelective(order);

        if (Const.PROFILE_PROD.equals(profileConfig.getActiveProfile())) {
            // 添加待配送延迟队列
            this.zAddWait2DeliveryOrder(orderId, payTime);
        }
    }

    @Override
    public void wait2Receive(Integer orderId, Integer deliveryId) {
        MallOrder order = new MallOrder();
        order.setOrderId(orderId);
        order.setStatus(MallOrderStatusEnums.WAIT2RECEIVE.getValue());
        order.setDeliveryId(deliveryId);
        order.setDeliveryTime(new Date());
        this.updateByPrimaryKeySelective(order);
    }

    @Override
    public void receiveSuccess(Integer orderId) {
        MallOrder order = new MallOrder();
        order.setOrderId(orderId);
        order.setStatus(MallOrderStatusEnums.WAIT2COMMENT.getValue());
        order.setReceiveTime(new Date());
        this.updateByPrimaryKeySelective(order);
    }

    @Override
    public void commentSuccess(MallOrder order) {
        order.setStatus(MallOrderStatusEnums.COMPLETED.getValue());
        order.setCommentTime(new Date());
        this.updateByPrimaryKeySelective(order);
    }

    @Override
    public List<MallOrderVo> getVoListByFilter(Integer userId, Integer status) {
        return orderMapper.getVoListByFilter(userId, status);
    }

    @RedisCache
    @Override
    public PageInfo<MallOrderVo> getVoPageByFilter(Integer userId, Integer status, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);
        return new PageInfo<>(this.getVoListByFilter(userId, status));
    }

    @RedisCache
    @Override
    public MallOrderVo getDetailVo(Integer userId, Integer orderId) {
        return orderMapper.getDetailVo(userId, orderId);
    }

    @Override
    public List<MallOrderVo> getVoByProductName(Integer userId, String productName) {
        return orderMapper.getVoByProductName(userId, productName);
    }

    @RedisCache
    @Override
    public PageInfo<MallOrderVo> getVoByProductName(Integer userId, String productName, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit);
        return new PageInfo<>(this.getVoByProductName(userId, productName));
    }

    @Override
    public List<MallOrder> getList(Integer status, Integer orderTimeSort) {
        return orderMapper.getList(status, orderTimeSort);
    }

    @Override
    public void payOrder(MallOrder order, HttpServletResponse response) {
        if (MallOrderPayTypeEnums.ALI.getValue().equals(order.getPayType())) {
            AlipayTradeWapPayResponse wapPayResponse = alipayConfig.pay(order.getOrderNumber(), order.getTotalPrice().toString());
            if (ResponseChecker.success(wapPayResponse)) {
                try {
                    // 调用SDK生成表单
                    String form = wapPayResponse.body;
                    response.setContentType("text/html;charset=utf-8");
                    //直接将完整的表单html输出到页面
                    response.getWriter().write(form);
                    response.getWriter().flush();
                    response.getWriter().close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            } else {
                throw new MallException(MallError.MallErrorEnum.TRANSACTION_ERROR);
            }
        } else if (MallOrderPayTypeEnums.WECHAT.getValue().equals(order.getPayType())) {
            // todo 微信支付
        }
    }

    @Override
    public MallOrder getByFilter(String orderNumber, Integer status) {
        return orderMapper.getByFilter(orderNumber, status);
    }

    @Override
    public void zAddOrderTimeout(Integer userId, Integer orderId, Date orderTime) {
        StringBuilder keySb = new StringBuilder(Const.REDIS_KEY_PREFIX)
                .append(Const.REDIS_KEY_DELIMITER)
                .append(Const.REDIS_KEY_ORDER_TIMEOUT_ZSET);
        StringBuilder valueSb = new StringBuilder(Const.REDIS_KEY_ORDER_PREFIX)
                .append(Const.REDIS_PARAM_DELIMITER)
                .append(orderId)
                .append(Const.REDIS_PARAM_DELIMITER)
                .append(userId);
        redisService.zAdd(keySb.toString(), valueSb.toString(), Double.valueOf(String.valueOf(orderTime.getTime() + Const.DEFAULT_ORDER_TIMEOUT)));
    }

    @Override
    public void zAddWait2DeliveryOrder(Integer orderId, Date payTime) {
        StringBuilder keySb = new StringBuilder(Const.REDIS_KEY_PREFIX)
                .append(Const.REDIS_KEY_DELIMITER)
                .append(Const.REDIS_KEY_ALLOCATE_DELIVERY_ZSET);
        StringBuilder valueSb = new StringBuilder(Const.REDIS_KEY_ORDER_PREFIX)
                .append(Const.REDIS_PARAM_DELIMITER)
                .append(orderId);
        redisService.zAdd(keySb.toString(), valueSb.toString(), Double.valueOf(String.valueOf(payTime.getTime())));
    }

    @Override
    public List<Integer> getUserIdListByOrderIdList(List<Integer> orderIdList) {
        return orderMapper.getUserIdListByOrderIdList(orderIdList);
    }

    @Override
    public void deleteCache(Integer userId) {
        redisService.deleteByPrefix(MallOrderServiceImpl.class, "getVoPageByFilter", userId);
        redisService.deleteByPrefix(MallOrderServiceImpl.class, "getVoByProductName", userId);
    }
}
