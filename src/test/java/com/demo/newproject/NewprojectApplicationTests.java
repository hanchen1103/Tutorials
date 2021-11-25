package com.demo.newproject;


import com.demo.newproject.mapper.HotQueueDAO;
import com.demo.newproject.model.HotQueue;
import com.demo.newproject.service.HotQueueService;
import com.hanchen.distributed.component.common.ZKDistributedLock;
import lombok.SneakyThrows;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NewprojectApplicationTests {

    @Autowired
    ZKDistributedLock zkDistributedLock;

    @Autowired
    HotQueueService hotQueueService;

    @Autowired
    HotQueueDAO hotQueueDAO;


    public class addQueueThread implements Runnable {

        public int queueId;

        public addQueueThread(int queueId) {
            this.queueId = queueId;
        }

        @SneakyThrows
        @Override
        public void run() {
            Long start = System.currentTimeMillis();
            hotQueueService.getHotQueueByOffset(1, 50);
            Long end = System.currentTimeMillis();
            System.out.println(end - start);
        }
    }

    @Test
    public void testLock() throws InterruptedException {
        for(int i = 0; i < 10; i ++) {
            Thread t = new Thread(new addQueueThread(1));
            t.start();
        }
        Thread.sleep(1000 * 5);
        //zkDistributedLock.unLock();
    }

}
