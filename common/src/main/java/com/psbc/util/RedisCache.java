package com.psbc.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCache {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public static final String EMPTY_TAG = "EMPTY";

    // 存缓存（正常 + 空值都走这个）
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value == null ? EMPTY_TAG : value, timeout, unit);
    }

    // 取：原样返回！不偷偷转 null！
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}