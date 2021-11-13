package com.demo.newproject.controller;

import com.demo.newproject.model.EntityType;
import com.demo.newproject.service.LikeService;
import com.demo.newproject.util.jsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/like")
public class LikeController {

    private static final Logger logger = LoggerFactory.getLogger(LikeController.class);

    @Autowired
    LikeService likeService;

    @PostMapping(value = "/queue", produces = {"application/json;charset=UTF-8"})
    public String Like(@RequestBody Map<String, String> map) {
        try {
            return jsonUtil.getJSONString(200, likeService.like(Integer.parseInt(map.get("userId")),
                    EntityType.HOTQUEUE, Integer.parseInt(map.get("hotqueueId"))));
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, "like error");
        } 
    }
}
