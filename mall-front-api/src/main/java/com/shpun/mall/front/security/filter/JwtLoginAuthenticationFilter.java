package com.shpun.mall.front.security.filter;

import com.alibaba.fastjson.JSON;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.front.security.JwtTokenUtils;
import com.shpun.mall.front.security.SecurityUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: JWT登录认证过滤器，认证通过即颁发token
 * @Author: sun
 * @Date: 2020/8/23 20:07
 */
public class JwtLoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtLoginAuthenticationFilter(AuthenticationManager authenticationManager) {
        // 设置拦截的url，即登录url
        super.setFilterProcessesUrl(Const.FRONT_LOGIN_URL);
        this.authenticationManager = authenticationManager;
    }

    /**
     * 获取请求中的用户名和密码，再封装成token并校验
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = this.obtainUsername(request);
        String password = this.obtainPassword(request);
        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        return this.authenticationManager.authenticate(authRequest);
    }

    /**
     * 登录成功创建token并写在头部Authorization中
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetails userDetails = (SecurityUserDetails) authResult.getPrincipal();

        List<String> authorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String token = JwtTokenUtils.createJwtToken(userDetails.getUsername(), authorities);
        response.setHeader(JwtTokenUtils.TOKEN_HEADER, token);
    }

    /**
     * 登录失败
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", 401);
        map.put("msg", "未认证通过，请重新登录！");

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(map));
    }
}
