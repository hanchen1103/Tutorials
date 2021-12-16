package com.demo.newproject.controller;

import com.demo.newproject.model.Book;
import com.demo.newproject.service.BookService;
import com.demo.newproject.util.jsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    @PostMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public String addBook(@RequestBody Map<String, String> map) {
        try {
            String name = map.get("name");
            String author = map.get("author");
            Integer stock = Integer.parseInt(map.get("stock"));
            Integer userId = Integer.parseInt(map.get("userId"));
            String content = map.get("content");
            String image = map.get("image");
            Book book = new Book();
            book.setContent(content);
            book.setCreateDate(new Date());
            book.setUserId(userId);
            book.setAuthor(author);
            book.setImage(image);
            book.setName(name);
            book.setStock(stock);
            bookService.addBook(book);
            return jsonUtil.getJSONString(200);
        } catch (NullPointerException | NumberFormatException | IllegalAccessException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, e.getMessage());
        }
    }

    @GetMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public String selectByOffset(Integer start, Integer offset) {
        try {
            return jsonUtil.getJSONString(200, bookService.selectByOffset(start, offset));
        } catch (NullPointerException | IllegalAccessException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, e.getMessage());
        }
    }

    @DeleteMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public String deleteById(Integer bookId) {
        try {
            bookService.deleteById(bookId);
            return jsonUtil.getJSONString(200);
        } catch (NullPointerException  e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, e.getMessage());
        }
    }
}
