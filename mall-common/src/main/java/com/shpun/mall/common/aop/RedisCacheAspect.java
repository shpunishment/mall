package com.shpun.mall.common.aop;

import com.alibaba.fastjson.JSON;
import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.exception.MallError;
import com.shpun.mall.common.exception.MallException;
import com.shpun.mall.common.service.RedisService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Description: 缓存切面
 * @Author: sun
 * @Date: 2020/8/30 20:11
 */
@Aspect
@Component
@Profile("prod")
public class RedisCacheAspect {

    @Autowired
    private RedisService redisService;

    @Pointcut("@annotation(com.shpun.mall.common.aop.RedisCache)")
    public void redisCachePointcut() {
    }

    @Around("redisCachePointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder redisKeySb = new StringBuilder(Const.REDIS_KEY_PREFIX)
                .append(Const.REDIS_KEY_DELIMITER);

        // 类
        String className = joinPoint.getTarget().toString().split("@")[0];
        redisKeySb.append(className)
                .append(Const.REDIS_KEY_DELIMITER);

        // 方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();
        redisKeySb.append(methodName);

        // 参数
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                redisKeySb.append(Const.REDIS_PARAM_DELIMITER).append(args[i]);
            }
        }

        String redisKey = redisKeySb.toString();
        Object result = redisService.get(redisKey);
        if (result != null) {
            return result;
        } else {
            try {
                result = joinPoint.proceed();
            } catch (Throwable e) {
                throw new MallException(MallError.MallErrorEnum.INTERNAL_SYSTEM_ERROR);
            }

            RedisCache redisCache = signature.getMethod().getAnnotation(RedisCache.class);
            long expire = redisCache.expire();
            redisService.set(redisKey, result, expire + new Random().nextInt(10));
        }

        return result;
    }

}
