package com.shpun.mall.common.service;

import java.util.Set;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/8/30 20:12
 */
public interface RedisService {

    void set(String key, Object value);

    void set(String key, Object value, long expire);

    Object get(String key);

    void expire(String key, long expire);

    void delete(String key);

    void deleteByPrefix(String prefix);

    void deleteByPrefix(Class cls, String methodName, Object...params);

    void zAdd(String key, Object value, Double score);

    void zRemove(String key, Object... value);

    Set<Object> zRangeByScore(String key, double min, double max);

}
