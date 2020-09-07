package com.shpun.mall.common.exception;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 13:20
 */
public class MallError {

    public enum MallErrorEnum implements Error {
        INTERNAL_SYSTEM_ERROR(1001, "系统内部错误！"),
        USERNAME_PASSWORD_FAIL(1002, "账号或密码错误！"),
        FILE_NOT_FOUND(1003, "文件id %s 不存在！"),
        ADD_FILE_ERROR(1004, "添加文件失败！"),
        GET_FILE_ERROR(1005, "获取文件失败！"),
        UPLOAD_FILE_ERROR(1006,"上传图片失败！"),
        CART_NULL(1007, "购物车为空！"),
        STOCK_NULL(1008, "商品 %s 库存不足！"),
        OFF_SHELF(1009, "商品 %s 已下架！"),
        LIMIT_ERROR(1010, "商品 %s 限购 %s !"),
        COUPON_GET_ERROR(1011, "优惠券领取失败！"),
        COUPON_REPEAT_ERROR(1012, "优惠券不可重复领取"),
        PRODUCT_NOT_FOUND(1013, "商品id %s 不存在！"),
        COUPON_ERROR(1014, "优惠券异常！"),
        USERNAME_EXIST(1015, "用户名 %s 已存在！"),
        OLD_PASSWORD_ERROR(1016, "原密码错误！"),
        ACTIVITY_NOT_FOUND(1017, "活动不存在！")
        ;

        private Integer code;
        private String message;

        MallErrorEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public MallErrorEnum format(Object... param) {
            this.message = String.format(message, param);
            return this;
        }

        @Override
        public Integer getCode() {
            return this.code;
        }

        @Override
        public String getMessage() {
            return this.message;
        }
    }

}
