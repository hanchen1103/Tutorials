package com.demo.newproject.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 114512405714830989L;

    private int id;

    private String account;

    private String password;

    private String head_url;

    private String salt;

    private int status;

}
