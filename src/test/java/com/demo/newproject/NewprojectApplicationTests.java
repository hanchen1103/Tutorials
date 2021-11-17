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


	@Test
	void contextLoads() throws IllegalAccessException {

	}

}
