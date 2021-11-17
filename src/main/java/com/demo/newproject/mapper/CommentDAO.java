package com.demo.newproject.mapper;

import com.demo.newproject.model.Comment;
import com.demo.newproject.model.HotQueue;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@MapperScan
public interface CommentDAO {
    String TABLE_NAME = " comment ";
    String INSERT_NAME = " content, entityId, entityType, createDate, status, userId ";
    String SELECT_NAME = " id, " + INSERT_NAME;

    @Select({"select ", SELECT_NAME, " from ", TABLE_NAME, " where status = 0 limit #{page}, #{offset}"})
    List<Comment> selectByPage(@Param("page") int page, @Param("offset") int offset);

    @Select({"select ", SELECT_NAME, " from ", TABLE_NAME, " where id = #{id} and status = 0"})
    Comment selectById(@Param("id") int id);

    @Select({"select ", SELECT_NAME, " from ", TABLE_NAME, " where userId = #{userId} and status = 0 order by id desc"})
    List<Comment> selectOwnQueue(int userId);

    @Insert({"insert into ", TABLE_NAME, "( ", INSERT_NAME,
            " ) values (#{content},#{entityId},#{entityType},#{createDate},#{status},#{userId})"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer addComment(Comment comment);

    @Update({"update ", TABLE_NAME, " set status = 1 where id = #{id}"})
    Integer deleteComment(int id);

    @Update({"update ", TABLE_NAME, " set content = #{content} where id = #{id}"})
    Integer updateComment(Comment comment);

    @Select({"select count(id) from ", TABLE_NAME, " where entityType = #{entityType} and entityId = #{entityId}"})
    Integer countComment(Integer entityType, Integer entityId);
}
