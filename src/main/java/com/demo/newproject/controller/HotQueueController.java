package com.demo.newproject.controller;

import com.demo.newproject.model.HotQueue;
import com.demo.newproject.service.HotQueueService;
import com.demo.newproject.service.UserService;
import com.demo.newproject.util.jsonUtil;
import com.google.common.annotations.Beta;
import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/queue")
public class HotQueueController {

    private static final Logger logger = LoggerFactory.getLogger(HotQueueController.class);

    @Autowired
    HotQueueService hotQueueService;

    @Autowired
    UserService userService;

    public static final RateLimiter rateLimiter = RateLimiter.create(100);


    @GetMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public String getHotQueue(Integer page, Integer offset) {
        try {
            if(rateLimiter.tryAcquire(10)) {
                String res = jsonUtil.getJSONString(200, hotQueueService.getHotQueueByOffset(page, offset));
                logger.info(res);
                return res;
            }
            logger.info("requests has been limited");
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, "page is illegal");
        } catch (NullPointerException ex) {
            logger.error(ex.getMessage());
            return jsonUtil.getJSONString(500, "page or offset can't be null");
        }
        return jsonUtil.getJSONString(500, "error");
    }

    @PostMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public String addHotQueue(@RequestBody Map<String, String> jsonMap) {
        try {
            HotQueue hotqueue = new HotQueue();
            hotqueue.setStatus(0);
            hotqueue.setCreateDate(new Date());
            hotqueue.setContent(jsonMap.get("content"));
            hotqueue.setTag(jsonMap.get("tags"));
            hotqueue.setUserId(Integer.parseInt(jsonMap.get("userId")));
            hotqueue.setTitle(jsonMap.get("title"));
            hotQueueService.publishHotQueue(hotqueue);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, "publish hotqueue error");
        }
        return jsonUtil.getJSONString(200);
    }

}
