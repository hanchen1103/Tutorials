package com.demo.newproject.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(indexName = "message_index", createIndex = true)
public class Message implements Serializable {

    private static final long serialVersionUID = -8780952080092251452L;

    @Id
    @Field(type = FieldType.Integer)
    private int id;

    @Field(type = FieldType.Date)
    private Date createDate;

    @Field(analyzer = "ik_max_word", format = DateFormat.basic_date_time)
    private String content;

    @Field(type = FieldType.Integer)
    private Integer fromId;

    @Field(type = FieldType.Integer)
    private Integer toId;

    @Field(type = FieldType.Integer)
    private Integer status;

    @Field(type = FieldType.Integer)
    private Integer isRead;

}
