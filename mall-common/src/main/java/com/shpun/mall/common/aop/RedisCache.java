package com.shpun.mall.common.aop;

import java.lang.annotation.*;

/**
 * @Description: 缓存注解
 * @Author: sun
 * @Date: 2020/8/30 20:07
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {

    /**
     * 默认失效时间为5分钟
     */
    long expire() default 300;

}
