package com.shpun.mall.front.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/23 21:48
 */
public class SecurityUserUtils {

    private SecurityUserUtils(){
    }

    public static SecurityUserDetails getCurrentUser() {
        SecurityUserDetails securityUserDetails = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof String) {
            // 未登录为anonymousUser
            if ("anonymousUser".equals(principal)) {
                return securityUserDetails;
            }
        } else {
            securityUserDetails = (SecurityUserDetails) principal;
        }
        return securityUserDetails;
    }

    public static Integer getUserId() {
        Integer userId = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof String)) {
            SecurityUserDetails securityUserDetails = (SecurityUserDetails) principal;
            userId = securityUserDetails.getUserId();
        }

        return userId;
    }

}
