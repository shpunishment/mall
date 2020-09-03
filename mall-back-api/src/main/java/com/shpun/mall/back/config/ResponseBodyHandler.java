package com.shpun.mall.back.config;

import com.alibaba.fastjson.JSON;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.model.vo.MallResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: ResponseBody统一返回包装处理
 * @Author: sun
 * @Date: 2020/8/29 11:16
 */
@RestControllerAdvice
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof MallResultVo) {
            return o;
        } else {
            String apiUrl;
            if (StringUtils.isNotBlank(contextPath)) {
                apiUrl = contextPath + Const.API_URL_PREFIX;
            } else {
                apiUrl = Const.API_URL_PREFIX;
            }

            HttpServletRequest httpServletRequest = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
            String url = httpServletRequest.getRequestURI();
            // api开头的url才进行包装
            if (url.startsWith(apiUrl)) {
                // String类型特别处理，防止发生类型转换异常
                if (o instanceof String) {
                    return JSON.toJSONString(MallResultVo.okData(o));
                } else {
                    return MallResultVo.okData(o);
                }
            } else {
                return o;
            }
        }
    }
}
