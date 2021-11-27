package com.demo.newproject;


import com.demo.newproject.mapper.HotQueueDAO;
import com.demo.newproject.model.HotQueue;
import com.demo.newproject.service.HotQueueService;
import com.demo.newproject.util.JedisAdapter;
import com.hanchen.distributed.component.common.ZKDistributedLock;
import lombok.SneakyThrows;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.commands.JedisCommands;

import java.util.concurrent.CountDownLatch;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NewprojectApplicationTests {

    @Autowired
    ZKDistributedLock zkDistributedLock;

    @Autowired
    HotQueueService hotQueueService;

    @Autowired
    HotQueueDAO hotQueueDAO;

    @Autowired
    JedisAdapter jedisAdapter;


    @Test
    public void testLock() throws InterruptedException {

    }

}
