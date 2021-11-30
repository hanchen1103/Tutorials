package com.demo.newproject.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Weather implements Serializable {

    private static final long serialVersionUID = -3531968489672458808L;

    /**
     * 湿度
     */
    private String humidity;

    /**
     * 温度
     */
    private String temperature;

    /**
     * 日出时间
     */
    private String sunRise;

    /**
     * 日落时间
     */
    private String sunDown;

    /**
     * 记录时间
     */
    private String recordTime;

    /**
     * 风力
     */
    private String windPower;

    /**
     * 风向
     */
    private String windFor;

    /**
     * 城市/区县
     */
    private String cityName;

}
