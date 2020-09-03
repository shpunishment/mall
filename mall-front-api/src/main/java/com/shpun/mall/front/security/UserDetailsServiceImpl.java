package com.shpun.mall.front.security;

import com.shpun.mall.common.model.MallUser;
import com.shpun.mall.common.service.MallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Description: UserDetailsService 接口实现类
 * @Author: sun
 * @Date: 2020/8/23 19:51
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MallUserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MallUser user = userService.getByUsername(s);
        if (user != null) {
            return new SecurityUserDetails(user.getUserId(), user.getUsername(), user.getPassword(), user.getEnable(), null);
        }
        return null;
    }

}
