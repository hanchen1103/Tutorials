package com.demo.newproject.mapper;

import com.demo.newproject.model.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookDAO {

    String TABLE_NAME = " book ";
    String INSERT_NAME = " name, author, createDate, stock, userId, content, image";
    String SELECT_NAME = " id, " + INSERT_NAME;
    
    @Insert({"insert into ", TABLE_NAME, " ( ", INSERT_NAME,
            " ) values (#{name}, #{author}, #{createDate},#{stock},#{userId}, #{content},#{image})"})
    Integer addBook(Book book);

    @Select({"select ", SELECT_NAME, " from ", TABLE_NAME, " where id=#{id}"})
    Book selectById(Integer id);

    @Select({"select ", SELECT_NAME, " from ", TABLE_NAME, " limit #{start}, #{offset}"})
    List<Book> selectByStartAndOffset(Integer start, Integer offset);

    @Delete({"delete from ", TABLE_NAME, " where id = #{id}"})
    void deleteById(Integer id);

}
