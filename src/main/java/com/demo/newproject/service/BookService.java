package com.demo.newproject.service;

import com.demo.newproject.mapper.BookDAO;
import com.demo.newproject.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 书本服务
 */
@Service
public class BookService {

    @Autowired
    BookDAO bookDAO;

    public void addBook(Book book) throws IllegalAccessException {
        if(book == null || book.getAuthor() == null ||
                book.getContent() == null || book.getUserId() == null ||
                book.getName() == null || book.getStock() == null) {
            throw new NullPointerException("param exception");
        }
        if(book.getStock() > 200) {
            throw new IllegalAccessException("more stock");
        }
        bookDAO.addBook(book);
    }

    public List<Book> selectByOffset(Integer start, Integer offset) throws IllegalAccessException {
        if(start == null || offset == null) {
            throw new NullPointerException();
        }

        if(start < 0) {
            throw new IllegalAccessException("param exception");
        }

        return bookDAO.selectByStartAndOffset(start, offset);
    }

    public void deleteById(Integer bookId) {
        if(bookId == null) {
            throw new NullPointerException();
        }
        bookDAO.deleteById(bookId);
    }
}
