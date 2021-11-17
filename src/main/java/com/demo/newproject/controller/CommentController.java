package com.demo.newproject.controller;

import com.demo.newproject.model.Comment;
import com.demo.newproject.service.CommentService;
import com.demo.newproject.service.UserService;
import com.demo.newproject.util.jsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;


    @PostMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public String addHotQueue(@RequestBody Map<String, String> jsonMap) {
        try {
            Comment comment = new Comment();
            comment.setStatus(0);
            comment.setCreateDate(new Date());
            comment.setContent(jsonMap.getOrDefault("content", " "));
            comment.setEntityId(Integer.parseInt(jsonMap.get("entityId")));
            comment.setEntityType(Integer.parseInt(jsonMap.get("entityType")));
            comment.setUserId(Integer.parseInt(jsonMap.get("userId")));
            commentService.addComment(comment);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, "add comment error");
        }
        return jsonUtil.getJSONString(200);
    }

    @GetMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public String getComment(Integer commentId) {
        try {
            return jsonUtil.getJSONString(200, commentService.selectById(commentId));
        } catch (NullPointerException ex) {
            logger.error(ex.getMessage());
            return jsonUtil.getJSONString(500, ex.getMessage());
        }
    }


}
