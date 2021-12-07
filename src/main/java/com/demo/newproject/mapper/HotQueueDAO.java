package com.demo.newproject.mapper;

import com.demo.newproject.model.HotQueue;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@MapperScan
@Repository
public interface HotQueueDAO {

    String TABLE_NAME = " hotqueue ";
    String INSERT_NAME = " content, title, createDate, status, userId, tag ";
    String SELECT_NAME = " id, " + INSERT_NAME;

    @Select({"select ", SELECT_NAME, " from ", TABLE_NAME, " where status = 0 limit #{page}, #{offset}"})
    List<HotQueue> selectByPage(@Param("page") int page, @Param("offset") int offset);

    @Select({"select ", SELECT_NAME, " from ", TABLE_NAME, " where id = #{id}"})
    HotQueue selectById(@Param("id") int id);

    @Select({"select ", SELECT_NAME, " from ", TABLE_NAME, " where userId = #{userId} and status = 0 order by id desc"})
    List<HotQueue> selectOwnQueue(int userId);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "hotqueue.id")
    @Insert({"insert into ", TABLE_NAME, "( ", INSERT_NAME,
            " ) values (#{content},#{title},#{createDate},#{status},#{userId},#{tag})"})
    int addHotQueue(HotQueue hotqueue);

    @Update({"update ", TABLE_NAME, " set status = 1 where id = #{id}"})
    Integer deleteHotQueue(int id);

    @Update({"update ", TABLE_NAME,
            " set content = #{content} and title = #{title} and tag = #{tag} where id = #{id}"})
    Integer updateHotqueue(HotQueue hotQueue);

    @Update({"update ", TABLE_NAME, " set status = #{status} where id = #{id}"})
    Integer TestLock(int status, int id);
}
