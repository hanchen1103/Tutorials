package com.demo.newproject.service;

import com.demo.newproject.util.JedisAdapter;
import com.demo.newproject.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    JedisAdapter jedisAdapter;

    @Autowired
    UserService userService;

    public Long getLikeCount(int entityType, int entityId) {
        String likeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        return jedisAdapter.scard(likeKey);
    }

    public Long like(int userId, int entityType, int entityId) {
        String LikeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        jedisAdapter.sadd(LikeKey, String.valueOf(userId));
        return jedisAdapter.scard(LikeKey);
    }

    public Long disLike(int userId, int entityType, int entityId) {
        String LikeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        jedisAdapter.srem(LikeKey, String.valueOf(userId));
        return jedisAdapter.scard(LikeKey);
    }

    public Boolean getLikeStatus(int userId, int entityType, int entityId) {
        String LikeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        return jedisAdapter.sismember(LikeKey, String.valueOf(userId));
    }
}
