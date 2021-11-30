package com.demo.newproject.model;

import lombok.Data;

import java.util.Date;

@Data
public class Message {

    private int id;

    private Date createDate;

    private String content;

    private Integer fromId;

    private Integer toId;

    private Integer status;

    private Integer isRead;

}
