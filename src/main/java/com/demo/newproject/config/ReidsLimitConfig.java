package com.demo.newproject.config;

import com.hanchen.distrubuted.component.service.DistributedLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;


@Configuration
public class ReidsLimitConfig {

    @Autowired
    JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public DistributedLimit create() {
        return new DistributedLimit.
                JedisConnectionFactoryBuilder(jedisConnectionFactory).
                request("spring-boot").
                secondToken(10).
                wasteTicket(2).create();
    }
}
