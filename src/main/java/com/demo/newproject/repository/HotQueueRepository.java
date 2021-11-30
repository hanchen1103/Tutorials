package com.demo.newproject.repository;

import com.demo.newproject.model.HotQueue;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * elasticsearch query user
 */
public interface HotQueueRepository extends ElasticsearchRepository<HotQueue, String> {

    List<HotQueue> findByContentOrTitle(String content, String title);


}
