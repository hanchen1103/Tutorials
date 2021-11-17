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

    @PostMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public String Like(@RequestBody Map<String, String> map) {
        try {
            Integer type = Integer.parseInt(map.getOrDefault("entityType", null));
            if(type == EntityType.HOTQUEUE) {
                return jsonUtil.getJSONString(200, likeService.like(Integer.parseInt(map.get("userId")),
                        EntityType.HOTQUEUE, Integer.parseInt(map.get("entityId"))));
            }
            if(type == EntityType.USER) {
                return jsonUtil.getJSONString(200, likeService.like(Integer.parseInt(map.get("userId")),
                        EntityType.USER, Integer.parseInt(map.get("entityId"))));
            }
            if(type == EntityType.COMMENT) {
                return jsonUtil.getJSONString(200, likeService.like(Integer.parseInt(map.get("userId")),
                        EntityType.COMMENT, Integer.parseInt(map.get("entityId"))));
            }
            return jsonUtil.getJSONString(500, "null type");
        } catch (NullPointerException | NumberFormatException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, "like error");
        }
    }

    @GetMapping(value = "/status", produces = {"application/json;charset=UTF-8"})
    public String getLikeStatus(Integer userId, Integer entityType, Integer entityId) {
        try {
            Boolean res = likeService.getLikeStatus(userId, entityType, entityId);
            return jsonUtil.getJSONString(200, res);
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, "empty param");
        }
    }

    @DeleteMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public String disLike(@RequestBody Map<String, String> map) {
        try {
            Long res = likeService.disLike(Integer.parseInt(map.get("userId")),
                    Integer.parseInt(map.get("entityType")),
                    Integer.parseInt(map.get("entityId")));
            return jsonUtil.getJSONString(200, res);
        } catch (NullPointerException | NumberFormatException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, "empty param");
        }
    }
}
