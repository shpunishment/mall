package com.shpun.mall.common.service.impl;

import com.shpun.mall.common.common.Const;
import com.shpun.mall.common.config.ProfileConfig;
import com.shpun.mall.common.service.RedisService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/30 20:28
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {

    @Autowired(required = false)
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ProfileConfig profileConfig;

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Object value, long expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void expire(String key, long expire) {
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public void delete(String key) {
        if (Const.PROFILE_PROD.equals(profileConfig.getActiveProfile())) {
            redisTemplate.delete(key);
        }
    }

    @Override
    public void deleteByPrefix(String prefix) {
        if (Const.PROFILE_PROD.equals(profileConfig.getActiveProfile())) {
            Set<String> keys = redisTemplate.keys(prefix);
            if (CollectionUtils.isNotEmpty(keys)) {
                redisTemplate.delete(keys);
            }
        }
    }

    @Override
    public void deleteByPrefix(Class cls, String methodName, Object... params) {
        if (Const.PROFILE_PROD.equals(profileConfig.getActiveProfile())) {
            StringBuilder prefixSb = new StringBuilder(Const.REDIS_KEY_PREFIX)
                    .append(Const.REDIS_KEY_DELIMITER)
                    .append(cls.toString().split("@")[0]);

            if (methodName != null) {
                prefixSb.append(Const.REDIS_KEY_DELIMITER)
                        .append(methodName);
            }

            if (params != null) {
                for (int i = 0; i< params.length; i++) {
                    prefixSb.append(Const.REDIS_PARAM_DELIMITER)
                            .append(params[i]);
                }
            }

            this.deleteByPrefix(prefixSb.toString());
        }
    }
}
