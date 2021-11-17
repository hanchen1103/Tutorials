package com.demo.newproject.service;

import com.demo.newproject.util.JedisAdapter;
import com.demo.newproject.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LikeService {

    @Autowired
    JedisAdapter jedisAdapter;

    @Autowired
    UserService userService;

    public Long getLikeCount(Integer entityType, Integer entityId) {
        if(entityType == null || entityId == null) {
            throw new NullPointerException("entityType or entityId can't be null");
        }
        String likeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        return jedisAdapter.scard(likeKey);
    }

    public Long like(Integer userId, Integer entityType, Integer entityId) {
        if(userId == null || entityType == null || entityId == null) {
            throw new NullPointerException("userId or entityType or entityId can't be null");
        }
        String LikeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        jedisAdapter.sadd(LikeKey, String.valueOf(userId));
        return jedisAdapter.scard(LikeKey);
    }

    public Long disLike(Integer userId, Integer entityType, Integer entityId) {
        if(userId == null || entityType == null || entityId == null) {
            throw new NullPointerException("userId or entityType or entityId can't be null");
        }
        String LikeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        jedisAdapter.srem(LikeKey, String.valueOf(userId));
        return jedisAdapter.scard(LikeKey);
    }

    public Boolean getLikeStatus(Integer userId, Integer entityType, Integer entityId) {
        if(userId == null || entityType == null || entityId == null) {
            throw new NullPointerException("userId or entityType or entityId can't be null");
        }
        String LikeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        return jedisAdapter.sismember(LikeKey, String.valueOf(userId));
    }

    public Long scard(Integer userId, Integer entityType, Integer entityId) {
        if(userId == null || entityType == null || entityId == null) {
            throw new NullPointerException("userId or entityType or entityId can't be null");
        }
        String LikeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        return jedisAdapter.scard(LikeKey);
    }

    public Set<String> smembers(Integer userId, Integer entityType, Integer entityId) {
        if(userId == null || entityType == null || entityId == null) {
            throw new NullPointerException("userId or entityType or entityId can't be null");
        }
        String LikeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        return jedisAdapter.smembers(LikeKey);
    }
}
