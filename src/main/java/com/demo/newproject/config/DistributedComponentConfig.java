package com.demo.newproject.config;


import com.hanchen.distributed.component.common.ZKDistributedLock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DistributedComponentConfig {

    @Bean
    public ZKDistributedLock build() {
        ZKDistributedLock zkDistributedLock = new ZKDistributedLock("/zookeeper", "127.0.0.1", 10000);
        zkDistributedLock.setLockValue("index");
        return zkDistributedLock;
    }


}
