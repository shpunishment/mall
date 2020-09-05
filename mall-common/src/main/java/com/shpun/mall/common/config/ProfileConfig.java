package com.shpun.mall.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/5 18:28
 */
@Configuration
public class ProfileConfig {

    @Autowired
    private ApplicationContext context;

    public String getActiveProfile() {
        return context.getEnvironment().getActiveProfiles()[0];
    }

}
