package com.shpun.mall.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpun.mall.common.aop.RedisCache;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.enums.MallCouponUseTypeEnums;
import com.shpun.mall.common.enums.MallOrderStatusEnums;
import com.shpun.mall.common.enums.MallUserAddressSexEnums;
import com.shpun.mall.common.enums.MallUserCouponStatusEnums;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.mapper.MallOrderMapper;
import com.shpun.mall.common.model.*;
import com.shpun.mall.common.model.vo.MallOrderVo;
import com.shpun.mall.common.model.vo.MallUserCouponVo;
import com.shpun.mall.common.service.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
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
    public MallOrder calculatePrice(List<Integer> cartIdList) {
        List<MallCart> cartList = cartService.getByCartIdList(cartIdList);
        if (CollectionUtils.isNotEmpty(cartIdList)) {
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
            throw new MallException("购物车为空");
        }
    }

    @Override
    public MallOrder calculatePrice(List<Integer> cartIdList, Integer couponId) {
        List<MallCart> cartList = cartService.getByCartIdList(cartIdList);
        if (CollectionUtils.isNotEmpty(cartIdList)) {
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

                // 优惠券不会空，并且商品非限时抢购
                if (coupon != null && useType != null && meetIdSet != null && !product.getFlashing()) {
                    if (MallCouponUseTypeEnums.ALL.getValue().equals(useType)) {
                        meetPrice = meetPrice.add(price);
                    } else if (MallCouponUseTypeEnums.CLASSIFY.getValue().equals(useType)) {
                        // 判断商品的分类，是否存在于优惠券的分类中
                        List<Integer> classifyIdList = productClassifyService.getClassifyIdByProductId(productId);
                        List<Integer> meetClassifyIdList = classifyIdList.stream().filter(meetIdSet::contains).collect(Collectors.toList());
                        if (CollectionUtils.isNotEmpty(meetClassifyIdList)) {
                            meetPrice = meetPrice.add(price);
                        }
                    } else if (MallCouponUseTypeEnums.PRODUCT.getValue().equals(useType)) {
                        // 判断商品，是否存在于优惠券的商品中
                        if (meetIdSet.contains(productId)) {
                            meetPrice = meetPrice.add(price);
                        }
                    }
                }
            }

            MallOrder order = new MallOrder();
            BigDecimal discount = new BigDecimal("0.00");
            if (coupon != null) {
                BigDecimal minPrice = coupon.getMinPrice();
                if (meetPrice.compareTo(minPrice) > -1) {
                    order.setCouponId(couponId);
                    discount = coupon.getDiscount();
                }
            } else {
                order.setCouponId(null);
            }

            this.setPrice(order, productPrice, discount);
            return order;
        } else {
            throw new MallException("购物车为空");
        }
    }

    @Transactional
    @Override
    public void generateOrder(MallOrder order, List<Integer> cartIdList) {
        List<MallCart> cartList = cartService.getByCartIdList(cartIdList);
        if (CollectionUtils.isNotEmpty(cartList)) {
            List<MallProduct> productList = new ArrayList<>(cartList.size());
            List<MallFlashItem> flashItemList = new ArrayList<>(cartList.size());
            List<MallOrderItem> orderItemList = new ArrayList<>(cartList.size());
            BigDecimal productPrice = new BigDecimal("0.00");

            for(MallCart cart : cartList) {
                Integer productId = cart.getProductId();
                Integer quantity = cart.getQuantity();

                // 锁定商品库存
                MallProduct product = productService.lockStock(productId);
                if (product.getPublishStatus() == 1) {
                    Integer stock;
                    BigDecimal price;

                    boolean isFlash = false;
                    // 判断当前商品是否限时抢购
                    MallFlashItem flashItemTemp = flashItemService.getFlashByProductId(product.getProductId());
                    MallFlashItem flashItem = null;
                    if (flashItemTemp != null) {
                        isFlash = true;
                        // 锁定限时抢购商品库存
                        flashItem = flashItemService.lockStock(flashItemTemp.getFlashItemId());
                        stock = flashItem.getStock();
                        price = flashItem.getPrice();
                    } else {
                        stock = product.getStock();
                        price = product.getCurrentPrice();
                    }

                    if (stock < quantity) {
                        throw new MallException("库存不足");
                    } else {
                        // 减库存，加销量
                        if (isFlash) {
                            flashItem.setStock(stock - quantity);
                            flashItem.setSales(flashItem.getSales() + quantity);
                            flashItemList.add(flashItem);
                        } else {
                            product.setStock(stock - quantity);
                            product.setSales(product.getSales() + quantity);
                            productList.add(product);
                        }

                        // 计算价格
                        productPrice = productPrice.add(price.multiply(new BigDecimal(quantity)));

                        // 订单商品
                        MallOrderItem orderItem = new MallOrderItem();
                        orderItem.setProductId(productId);
                        orderItem.setProductName(product.getProductName());
                        orderItem.setPic(product.getPic());
                        orderItem.setPrice(price);
                        orderItem.setQuantity(quantity);
                        orderItem.setFlashItemId(isFlash ? flashItem.getFlashItemId() : Const.DEFAULT_NO_FLASH_ITEM_ID);
                        orderItemList.add(orderItem);
                    }
                } else {
                    throw new MallException("已下架");
                }
            }

            // 删除购物车
            cartService.deleteBatch(cartIdList);
            // 更新商品，减库存，加销量
            productService.updateBatch(productList);
            // 更新限时抢购商品，减库存，加销量
            flashItemService.updateBatch(flashItemList);

            // 订单，设值价格，生成订单号
            // todo 优惠价格
            this.setPrice(order, productPrice, null);
            String orderNumber = new Date().getTime() + "" + new Random().nextInt(1000);
            order.setOrderNumber(orderNumber);
            order.setOrderTime(new Date());
            // 订单地址
            MallUserAddress userAddress = userAddressService.selectByPrimaryKey(order.getAddressId());
            StringBuilder receiveNameSb = new StringBuilder(userAddress.getName());
            if (!userAddress.getSex().equals(MallUserAddressSexEnums.NO.getValue())) {
                receiveNameSb.append("(")
                        .append(MallUserAddressSexEnums.getEnum(userAddress.getSex()).getName())
                        .append(")");
            }
            order.setReceiveName(receiveNameSb.toString());
            order.setReceivePhone(userAddress.getPhone());
            order.setReceiveAddress(userAddress.getAddress());
            orderMapper.insertSelective(order);

            // 订单商品
            for(MallOrderItem orderItem : orderItemList) {
                orderItem.setOrderId(order.getOrderId());
                orderItem.setOrderNumber(orderNumber);
            }
            orderItemService.insertBatch(orderItemList);
        } else {
            throw new MallException("购物车为空");
        }
    }

    @Override
    public List<MallOrder> getByUserId(Integer userId) {
        return orderMapper.getByUserId(userId);
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
        if (productPrice.compareTo(Const.PRODUCT_PRICE) > -1) {
            order.setDeliveryPrice(Const.FREE_DELIVERY);
            order.setTotalPrice(productPrice.subtract(couponPrice));
        } else {
            order.setDeliveryPrice(Const.DELIVERY_PRICE);
            order.setTotalPrice(productPrice.add(Const.DELIVERY_PRICE).subtract(couponPrice));
        }
    }

    @Transactional
    @Override
    public void closeOrder(Integer orderId, Integer userId) {
        // 查找订单是否存在
        MallOrder order = this.selectByPrimaryKey(orderId);
        if (!order.getUserId().equals(userId)) {
            throw new MallException("内部错误");
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
    }

    @Override
    public void paySuccess(Integer orderId) {
        MallOrder order = new MallOrder();
        order.setOrderId(orderId);
        order.setStatus(MallOrderStatusEnums.PAID.getValue());
        order.setPayTime(new Date());
        this.updateByPrimaryKeySelective(order);
    }

    @Override
    public void delivering(MallOrder order) {
        order.setStatus(MallOrderStatusEnums.WAIT2RECEIVE.getValue());
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
    public MallOrderVo getDetailVo(Integer orderId) {
        return orderMapper.getDetailVo(orderId);
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
    public List<MallOrder> getList() {
        return orderMapper.getList();
    }

    @Override
    public void deleteCache(Integer userId) {
        redisService.deleteByPrefix(MallOrderServiceImpl.class, "getVoPageByFilter", userId);
        redisService.deleteByPrefix(MallOrderServiceImpl.class, "getVoByProductName", userId);
    }
}
