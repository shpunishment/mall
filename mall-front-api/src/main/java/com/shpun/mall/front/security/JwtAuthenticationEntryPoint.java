package com.shpun.mall.front.security;

import com.alibaba.fastjson.JSON;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.model.vo.MallResultVo;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 当没有token或者token失效访问接口时，自定义返回结果
 * @Author: sun
 * @Date: 2020/8/23 20:08
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.getWriter()
                .write(JSON.toJSONString(MallResultVo.failure(Const.API_RETRUN_CODE_AUTHENTICATION_FAIL, "未认证通过，请登录！")));
    }
}
