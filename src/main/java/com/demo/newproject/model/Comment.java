package com.demo.newproject.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.security.SecureRandomParameters;
import java.util.Date;

@Data
@Document(indexName = "comment_index", createIndex = true)
public class Comment implements Serializable {

    private static final long serialVersionUID = 3064600675476456529L;

    @Id
    @Field(type = FieldType.Integer)
    private Integer id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content;

    @Field(type = FieldType.Integer)
    private Integer entityId;

    @Field(type = FieldType.Integer)
    private Integer entityType;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @Field(type = FieldType.Integer)
    private Integer status;

    @Field(type = FieldType.Integer)
    private Integer userId;


}
