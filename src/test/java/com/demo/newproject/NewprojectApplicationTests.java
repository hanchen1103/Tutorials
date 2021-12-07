package com.demo.newproject;


import com.demo.newproject.model.HotQueue;
import com.demo.newproject.repository.HotQueueRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.*;

import java.awt.print.Pageable;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NewprojectApplicationTests {

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    @Autowired
    HotQueueRepository hotQueueRepository;

    @Test
    public void testLock() {
//        List<HotQueue> list = hotQueueRepository.findByContent("hello", PageRequest.of(0, 10));
//        for(HotQueue i : list) {
//            System.out.println(i.toString());
//        }
    }
}
