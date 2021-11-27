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

//    private JedisCluster jedisCluster;

    @Override
    public void afterPropertiesSet() throws Exception {
        jedisPool = new JedisPool("redis://127.0.0.1:6379/1");
//        Set<HostAndPort> nodes = new HashSet<>();
//        nodes.add(new HostAndPort("127.0.0.1", 6379));
//        jedisCluster = new JedisCluster(nodes);
    }

    public Jedis getJedisConnection() {
        try {
            return jedisPool.getResource();
        } catch (Exception e) {
            logger.error("redis error " + e.getMessage());
        }
        return null;
    }


    public Long srem(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.srem(key, value);
        } catch (Exception e) {
            logger.error("redis error " + e.getMessage());
        }
        return 0L;
    }

    public Long sadd(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.sadd(key, value);
        } catch (Exception e) {
            logger.error("redis error " + e.getMessage());
        }
        return 0L;
    }

    public Set<String> smembers(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.smembers(key);
        } catch (Exception e) {
            logger.error("redis error " + e.getMessage());
        }
        return null;
    }

    public Long scard(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.scard(key);
        } catch (Exception e) {
            logger.error("redis error " + e.getMessage());
        }
        return 0L;
    }

    public Boolean sismember(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.sismember(key, value);
        } catch (Exception e) {
            logger.error("redis error " + e.getMessage());
        }
        return null;
    }

    public String hget(String field, String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hget(field, key);
        } catch (Exception e) {
            logger.error("redis error " + e.getMessage());
        }
        return null;
    }

    public Long hset(String field, String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hset(field, key, value);
        } catch (Exception e) {
            logger.error("redis error " + e.getMessage());
        }
        return null;
    }

}
