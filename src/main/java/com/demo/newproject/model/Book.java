package com.demo.newproject.model;

import lombok.Data;

import java.util.Date;

/**
 * 书本
 */
@Data
public class Book {

    /**
     * 书本id
     */
    private Integer id;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 录入图书管理员id
     */
    private Integer userId;

    /**
     * 库存量
     */
    private Integer stock;

    /**
     * 录入日期
     */
    private Date createDate;

    /**
     * 书本简介
     */
    private String content;

    /**
     * 书本封面
     */
    private String image;

}
