package com.dove.authority.utils;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author run
 * @since 2021/3/20 19:59
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public void redisIncrement(String redisKey, final Long expireTime){
        if (!redisTemplate.hasKey(redisKey)){
            redisTemplate.opsForValue().increment(redisKey);
            redisTemplate.expire(redisKey, expireTime, TimeUnit.SECONDS);
            return;
        }

        redisTemplate.opsForValue().increment(redisKey);
    }

    public int getCountOfKey(String redisKey){
        return redisTemplate.hasKey(redisKey)
                ? ((Integer) redisTemplate.opsForValue().get(redisKey))
                : 0;
    }

    public void add(String redisKey, Object value, final Long expireTime){
        redisTemplate.opsForValue().set(redisKey, value);
        redisTemplate.expire(redisKey, expireTime, TimeUnit.SECONDS);
    }

}
