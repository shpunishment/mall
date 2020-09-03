package com.shpun.mall.common.exception;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 13:20
 */
public class MallError {

    public enum MallErrorEnum implements Error {
        ACCOUNT_PASSWORD_FAIL(10001, "账号或密码错误!"),
        FORMAT_ERROR(10002, "格式错误【%s】"),
        TEST1_ERROR_1(11000, "test1 错误1!"),
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
