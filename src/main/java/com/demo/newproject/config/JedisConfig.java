package com.demo.newproject.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JedisConfig {


//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName("127.0.0.1");
//        redisStandaloneConfiguration.setPort(6379);
//        return new JedisConnectionFactory(redisStandaloneConfiguration);
//    }
//
//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        return RedisCacheManager.create(redisConnectionFactory);
//    }
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactoryPool() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(100);
//        jedisPoolConfig.setMaxTotal(20);
//        jedisPoolConfig.setMaxWaitMillis(10000);
//        jedisPoolConfig.setTestOnBorrow(true);
//        jedisPoolConfig.setTestOnReturn(true);
//        jedisPoolConfig.setNumTestsPerEvictionRun(10);
//
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName("127.0.0.1");
//        redisStandaloneConfiguration.setPort(6379);
//
//        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfigurationBuilder
//                = JedisClientConfiguration.builder();
//        JedisClientConfiguration jedisClientConfiguration =
//                jedisClientConfigurationBuilder.usePooling().poolConfig(jedisPoolConfig).build();
//        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
//    }
//
//    @Bean
//    public RedisTemplate<String, Integer> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Integer> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//
//        //??????Jackson2JsonRedisSerializer???????????????????????????redis???value??????????????????JDK?????????????????????
//        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(String.class);
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        serializer.setObjectMapper(mapper);
//
//        template.setValueSerializer(serializer);
//        //??????StringRedisSerializer???????????????????????????redis???key???
//        template.setKeySerializer(new StringRedisSerializer());
//        template.afterPropertiesSet();
//        return template;
//    }
}
