package com.demo.newproject.mapper;

import com.demo.newproject.model.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface MessageDAO {

    String TABLE_NAME = " message ";
    String INSERT_NAME = " createDate, content, fromId, toId, isRead";
    String SELECT_NAME = "id, " + INSERT_NAME;

    @Select({"select ", SELECT_NAME, " from ", TABLE_NAME, " where id = #{id}"})
    Message selectById(@Param("id") Integer id);


    @Select({"select ", SELECT_NAME, " from ",
            TABLE_NAME, " where fromId = #{fromId} and toId = #{toId} order by createDate desc limit #{start}, #{end}"})
    List<Message> selectByFromIdAndtoId(@Param("id") Integer fromId, @Param("toId") Integer toId,
                                        @Param("start") Integer start, @Param("end") Integer end);

    @Insert({"insert into ", TABLE_NAME,
            " ( ", INSERT_NAME, " ) values (#{createDate}, #{content}, #{fromId}, #{toId})"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addMessage(Message message);


}
