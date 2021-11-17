package com.demo.newproject.controller;

import com.demo.newproject.service.FileService;
import com.demo.newproject.service.UserService;
import com.demo.newproject.util.jsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    @PostMapping(value = "/login", produces = {"application/json;charset=UTF-8"})
    public String login(@RequestBody Map<String, String> map) {
        String account = map.get("account");
        String password = map.get("password");
        Map<String, Object> resMap = userService.Login(account, password);
        return jsonUtil.getJSONString(200, resMap);
    }

    @PostMapping(value = "/register", produces = {"application/json;charset=UTF-8"})
    public String register(@RequestBody Map<String, String> map) {
        String account = map.get("account");
        String password = map.get("password");
        Map<String, Object> resMap = userService.register(account, password);
        return jsonUtil.getJSONString(200, resMap);
    }


    @PostMapping(value = "/upload", produces = {"application/json;charset=UTF-8"})
    public String uploadRegister(HttpServletRequest httpServletRequest) {
        try {
            MultipartFile file = ((MultipartHttpServletRequest) httpServletRequest).getFile("file");
            fileService.uploadFile(file, Integer.parseInt(httpServletRequest.getParameter("userId")));
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
            return jsonUtil.getJSONString(503, "upload error");
        }
        return jsonUtil.getJSONString(200, "uploadSuccess");
    }
}
