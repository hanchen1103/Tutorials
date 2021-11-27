package com.demo.newproject.config;


import com.demo.newproject.util.JedisAdapter;
import com.hanchen.distributed.component.common.RSDistributedLimit;
import com.hanchen.distributed.component.common.ZKDistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope(value = "prototype")
public class DistributedComponentConfig {

    @Autowired
    JedisAdapter jedisAdapter;

    @Bean
    public ZKDistributedLock buildLock() {
        ZKDistributedLock zkDistributedLock = new ZKDistributedLock("/zookeeper", "127.0.0.1", 10000);
        zkDistributedLock.setLockValue("index");
        return zkDistributedLock;
    }

    @Bean
    public RSDistributedLimit buildLimit() {
        return new RSDistributedLimit.JedisBuilder<>(jedisAdapter.getJedisConnection()).
                maxSize(100).secondToken(5).wasteTicket(10).request("queuelist").create();
    }

}
