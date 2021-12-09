package com.demo.newproject.controller;

import com.demo.newproject.service.MessageService;
import com.demo.newproject.service.UserService;
import com.demo.newproject.util.jsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/profile", produces = {"application/json;charset=UTF-8"})
    public String ChatProfile(Integer fromId, Integer toId) {
        try {
            Map<String, Object> map = messageService.selectByFromIdAnd2Id(fromId, toId);
            return jsonUtil.getJSONString(200, map);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
        }
        return jsonUtil.getJSONString(500);
    }
}
