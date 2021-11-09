package com.demo.newproject.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

@Component
public class JedisAdapter implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(JedisAdapter.class);

    private JedisPool jedisPool;

    @Override
    public void afterPropertiesSet() throws Exception {
        jedisPool = new JedisPool("redis://127.0.0.1:7000/10");
    }

    public long srem(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.srem(key, value);
        } catch (Exception e) {
            logger.error("redis error " + e.getMessage());
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    public long sadd(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sadd(key, value);
        } catch (Exception e) {
            logger.error("redis error " + e.getMessage());
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    public Set<String> smembers(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.smembers(key);
        } catch (Exception e) {
            logger.error("redis error " + e.getMessage());
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public long scard(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.scard(key);
        } catch (Exception e) {
            logger.error("redis error " + e.getMessage());
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
        return 0L;
    }

    public Boolean sismember(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sismember(key, value);
        } catch (Exception e) {
            logger.error("redis error " + e.getMessage());
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
        return null;
    }


}
