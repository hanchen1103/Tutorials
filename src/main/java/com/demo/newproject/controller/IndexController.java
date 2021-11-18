package com.demo.newproject.controller;

import com.demo.newproject.model.User;
import com.demo.newproject.model.Weather;
import com.demo.newproject.service.UserService;
import com.demo.newproject.service.WeatherService;
import com.demo.newproject.util.jsonUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/index")
@MapperScan("com.demo.newproject.mapper")
public class IndexController {

    public static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    WeatherService weatherService;

    @GetMapping(value = "/weather", produces = {"application/json;charset=UTF-8"})
    public String Login(String cityName) {
        try {
            return jsonUtil.getJSONString(0, weatherService.getWeatherInfo(cityName));
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return jsonUtil.getJSONString(500, "error");
    }

}
