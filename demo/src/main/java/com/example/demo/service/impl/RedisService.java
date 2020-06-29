package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class RedisService {

    /**
     * 注入redisTemplate bean
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public long del(String... key) {
        long res = 0L;
//        if (key == null) {
//            return res;
//        }
        if (key.length == 1) {
            if (redisTemplate.delete(key[0])) {
                res = 1L;
            }
        }
        if (key.length > 1) {
            res = redisTemplate.delete(CollectionUtils.arrayToList(key));
        }
        return res;
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
