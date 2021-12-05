//package com.demo.newproject.config;
//
//
//import com.demo.newproject.util.JedisAdapter;
//import com.hanchen.distributed.component.common.RSDistributedLimit;
//import com.hanchen.distributed.component.common.ZKDistributedLock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class DistributedComponentConfig {
//
//    @Autowired
//    JedisAdapter jedisAdapter;
//
//    @Bean
//    @Scope(value = "prototype")
//    public ZKDistributedLock buildLock() {
//        ZKDistributedLock zkDistributedLock = new ZKDistributedLock("/zookeeper", "127.0.0.1", 10000);
//        zkDistributedLock.setLockValue("index");
//        return zkDistributedLock;
//    }
//
//    @Bean
//    @Scope(value = "prototype")
//    public RSDistributedLimit buildLimit() {
//        return new RSDistributedLimit.JedisBuilder<>(jedisAdapter.getJedisConnection()).
//                maxSize(200).secondToken(30).wasteTicket(1).request("queuelist").create();
//    }
//
//}
