package com.shpun.mall.front.security.filter;

import com.shpun.mall.front.security.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: JWT前置认证过滤器，抛出异常就会调用JwtAuthenticationEntryPoint的方法返回结果
 * @Author: sun
 * @Date: 2020/8/23 20:06
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    /**
     * 从头部中获取token，并解析获取用户名再进行校验，最后保存到SecurityContext上下文中
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String token = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
            if (token != null && token.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
                String jwtToken = token.replace(JwtTokenUtils.TOKEN_PREFIX, "");
                String username = JwtTokenUtils.getUsernameByToken(jwtToken);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                    // 校验token
                    if (JwtTokenUtils.validateToken(jwtToken, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }
        chain.doFilter(request, response);
    }

}
