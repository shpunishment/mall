package com.shpun.mall.common.common;

import java.math.BigDecimal;

/**
 * @Description: 常量类
 * @Author: sun
 * @Date: 2020/8/22 12:55
 */
public class Const {

    private Const(){
    }

    /**
     * 管理员id
     */
    public static final Integer ADMIN_ID = 1;

    /**
     * 配送费
     */
    public static final BigDecimal DELIVERY_PRICE = new BigDecimal("8.00");

    /**
     * 免配送费
     */
    public static final BigDecimal FREE_DELIVERY = new BigDecimal("0.00");

    /**
     * 商品价格，大于免配送费
     */
    public static final BigDecimal PRODUCT_PRICE = new BigDecimal("18.00");

    /**
     * 限时抢购默认持续30分钟
     */
    public static final Integer DEFAULT_FLASH_LIMIT_MINS = 30;

    /**
     * api返回成功code
     */
    public static final Integer API_RETURN_CODE_SUCCESS = 200;

    /**
     * api请求错误code
     */
    public static final Integer API_RETURN_CODE_BAD_REQUEST = 400;

    /**
     * api返回错误code
     */
    public static final Integer API_RETURN_CODE_INTERNAL_SERVER_ERROR = 500;

    /**
     * api返回登录失败code
     */
    public static final Integer API_RETRUN_CODE_AUTHENTICATION_FAIL = 401;

    /**
     * api返回无权限code
     */
    public static final Integer API_RETRUN_CODE_ACCESS_DENIED = 403;

    /**
     * api链接前缀
     */
    public static final String API_URL_PREFIX = "/api";

    /**
     * api返回成功message
     */
    public static final String API_RETURN_MESSAGE_SUCCESS = "ok";

    /**
     * api返回错误message
     */
    public static final String API_RETURN_MESSAGE_ERROR = "系统内部错误！";

    /**
     * 商品分类rootId
     */
    public static final Integer PRODUCT_CLASSIFY_ROOT_ID = 0;

    /**
     * 用户默认权限值，添加购物车，查看订单等
     */
    public static final String DEFAULT_AUTHORITY = "userAuthority";

    /**
     * 前台登录认证，即颁发token的url
     */
    public static final String FRONT_LOGIN_URL = "/login";

    /**
     * 热门搜索词默认限制15个
     */
    public static final Integer DEFAULT_LIMIT_HOT_KEYWORD = 15;

    /**
     * 订单列表中展示的商品默认3个
     */
    public static final Integer DEFAULT_LIMIT_ORDER_ITEM = 3;

    /**
     * 订单商品默认无限时抢购的商品id
     */
    public static final Integer DEFAULT_NO_FLASH_ITEM_ID = 0;

    /**
     * redis key 前缀
     */
    public static final String REDIS_KEY_PREFIX = "MALL";

    /**
     * redis key 分隔符
     */
    public static final String REDIS_KEY_DELIMITER = "::";

    /**
     * redis 参数 分隔符
     */
    public static final String REDIS_PARAM_DELIMITER = ":";

    /**
     * 今日可用优惠券数
     */
    public static final Integer TODAY_USE_COUPON_COUNT = 2;

    /**
     * 当前环境，开发
     */
    public static final String PROFILE_DEV = "dev";

    /**
     * 当前环境，生产
     */
    public static final String PROFILE_PROD = "prod";

    /**
     * 超时订单的redis zset key
     */
    public static final String REDIS_KEY_ORDER_TIMEOUT_ZSET = "order_timeout_zset";

    /**
     * 待分配配送员订单的redis zset key
     */
    public static final String REDIS_KEY_ALLOCATE_DELIVERY_ZSET = "allocate_delivery_zset";

    /**
     * 订单值前缀
     */
    public static final String REDIS_KEY_ORDER_PREFIX = "ORDER";

    /**
     * 默认订单未支付，超时时间九分钟
     */
    public static final long DEFAULT_ORDER_TIMEOUT = 9 * 60 * 1000;

    /**
     * 支付默认subject
     */
    public static final String PAY_SUBJECT = "Mall超市订单";

    /**
     * 默认需要去配送的订单数量
     */
    public static final Integer DEFAULT_NEED_2_DELIVERY_AMOUNT = 1;

}
