package com.shpun.mall.common.model.vo;

import com.shpun.mall.common.common.Const;

import java.io.Serializable;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 18:14
 */
public class MallResultVo<T> implements Serializable {

    private Integer code = Const.API_RETURN_CODE_SUCCESS;

    private String message = Const.API_RETURN_MESSAGE_SUCCESS;

    private T data;

    private static final long serialVersionUID = 1L;

    public static MallResultVo<?> ok() {
        return new MallResultVo<>();
    }

    public static <T> MallResultVo<T> okData(T data) {
        MallResultVo<T> resultVo = new MallResultVo<>();
        resultVo.setData(data);
        return resultVo;
    }

    public static <T> MallResultVo<T> okData(T data, String message) {
        MallResultVo<T> resultVo = new MallResultVo<>();
        resultVo.setMessage(message);
        resultVo.setData(data);
        return resultVo;
    }

    public static <T> MallResultVo<T> build(Integer code, T data, String message) {
        MallResultVo<T> resultVo = new MallResultVo<>();
        resultVo.setCode(code);
        resultVo.setMessage(message);
        resultVo.setData(data);
        return resultVo;
    }

    public static MallResultVo<?> failure(Integer code, String message) {
        MallResultVo<?> resultVo = new MallResultVo<>();
        resultVo.setCode(code);
        resultVo.setMessage(message);
        return resultVo;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MallResultVo{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
