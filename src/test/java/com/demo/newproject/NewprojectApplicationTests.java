package com.demo.newproject;

import com.demo.newproject.model.HotQueue;
import com.demo.newproject.service.HotQueueService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import service.DistributedLimit;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NewprojectApplicationTests {

	@Autowired
	JedisConnectionFactory jedisConnectionFactory;


	public class TestLimit implements Runnable {

		DistributedLimit distributedLimit;

		public TestLimit(DistributedLimit distributedLimit) {
			this.distributedLimit = distributedLimit;
		}

		@Override
		public void run() {
			Boolean res = distributedLimit.isLimit();
			System.out.println(res);
		}
	}

	@Autowired
	HotQueueService hotQueueService;
	@Test
	void contextLoads() throws IllegalAccessException {
		DistributedLimit distributedLimit = new DistributedLimit.JedisConnectionFactoryBuilder(jedisConnectionFactory).
				maxSize(100).secondToken(5).wasteTicket(1).request("test-request").create();

		for(int i = 0; i < 100; i ++) {
            Thread a = new Thread(new TestLimit(distributedLimit));
            a.start();
        }
	}

}
