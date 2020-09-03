package com.shpun.mall.common.exception;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/22 13:20
 */
public class MallException extends RuntimeException {

    private Integer code;

    public MallException() {
    }

    public MallException(Error error) {
        super(error.getMessage());
        this.code = error.getCode();
    }

    public MallException(String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
