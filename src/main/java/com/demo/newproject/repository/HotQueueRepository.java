package com.demo.newproject.repository;

import com.demo.newproject.model.HotQueue;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;
import java.util.List;

/**
 * elasticsearch query user
 */
@Component
public interface HotQueueRepository extends ElasticsearchRepository<HotQueue, Integer> {

    @Query("{\"match\": {\"content\": {\"query\": \"?0\"}}}")
    List<HotQueue> findByContent(String content, PageRequest pageRequest);


}
