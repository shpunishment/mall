package com.shpun.mall.front.security;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.enums.MallUserEnableEnums;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description: security 中 UserDetails 实现类
 * @Author: sun
 * @Date: 2020/8/23 19:51
 */
public class SecurityUserDetails implements UserDetails {

    private Integer userId;

    private String username;

    private String password;

    private Integer enable;

    private List<GrantedAuthority> authorities;

    public SecurityUserDetails (Integer userId, String username, String password, Integer enable, List<String> authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.enable = enable;

        // 设置默认权限值
        if (authorities == null) {
            List<GrantedAuthority> authorityList = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority(Const.DEFAULT_AUTHORITY));
            this.authorities = authorityList;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enable.equals(MallUserEnableEnums.ENABLE.getValue());
    }

    @Override
    public boolean equals(Object o) {
        return this.toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return this.username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
