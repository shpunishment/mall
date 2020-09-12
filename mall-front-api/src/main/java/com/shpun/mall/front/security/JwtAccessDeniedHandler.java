package com.shpun.mall.front.security;

import com.alibaba.fastjson.JSON;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.model.vo.MallResultVo;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 当用户无权限访问该资源时，自定义返回结果
 * @Author: sun
 * @Date: 2020/8/23 20:07
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.getWriter()
                .write(JSON.toJSONString(MallResultVo.failure(Const.API_RETRUN_CODE_ACCESS_DENIED, "无权限访问")));
    }
}
