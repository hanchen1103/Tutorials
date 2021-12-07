package com.demo.newproject.controller;

import com.demo.newproject.mapper.HotQueueDAO;
import com.demo.newproject.model.HotQueue;
import com.demo.newproject.service.HotQueueService;
import com.demo.newproject.service.UserService;
import com.demo.newproject.util.JedisAdapter;
import com.demo.newproject.util.jsonUtil;
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

    @Autowired
    HotQueueDAO hotQueueDAO;

//    @Autowired
//    ZKDistributedLock zkDistributedLock;

    @Autowired
    JedisAdapter jedisAdapter;

//    @Autowired
//    RSDistributedLimit rsDistributedLimit;


    @GetMapping(value = "/test", produces = {"application/json;charset=UTF-8"})
    public String getTest() {
        //zkDistributedLock.setLockValue("HotQueue-1");
        Boolean res = null;
        //if(zkDistributedLock.getLock()) {
            HotQueue hotQueue = hotQueueService.selectById(1);
            int t = Integer.parseInt(jedisAdapter.hget("zktest", "queue"));
            hotQueueDAO.TestLock(hotQueue.getStatus() + 1, hotQueue.getId());
            jedisAdapter.hset("zktest", "queue", String.valueOf(t + 1));
            //zkDistributedLock.unLock();
            return jsonUtil.getJSONString(200, res);
//        }
//        return jsonUtil.getJSONString(500, res);
    }


    @GetMapping(value = "/list", produces = {"application/json;charset=UTF-8"})
    public String getHotQueue(Integer page, Integer offset) {
        try {
//            if(rsDistributedLimit.isLimit()) {
//                logger.error("request has been limited");
//                return jsonUtil.getJSONString(500);
//            }
            String res = jsonUtil.getJSONString(200, hotQueueService.getQueueInfoList(page, offset));
            logger.info(res);
            return res;
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, "page is illegal");
        } catch (NullPointerException ex) {
            logger.error(ex.getMessage());
            return jsonUtil.getJSONString(500, "page or offset can't be null");
        }
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

    @GetMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public String getHotQueueInfo(Integer queueId) {
        try {
            String res = jsonUtil.getJSONString(200, hotQueueService.getQueueInfo(queueId));
            logger.info(res);
            return res;
        } catch (NullPointerException ex) {
            logger.error(ex.getMessage());
            return jsonUtil.getJSONString(500, ex.getMessage());
        }
    }


}
