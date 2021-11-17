package com.demo.newproject.service;

import com.demo.newproject.mapper.CommentDAO;
import com.demo.newproject.model.Comment;
import com.demo.newproject.model.HotQueue;
import com.demo.newproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {


    @Autowired
    CommentDAO commentDAO;

    @Autowired
    UserService userService;

    public Integer addComment(Comment comment) throws NullPointerException{
        if(comment == null || comment.getContent() == null || comment.getContent().isBlank()) {
            throw new NullPointerException("content can't be empty");
        }
        User user = userService.selectById(comment.getUserId());
        if(user == null || user.getStatus() == 1) {
            throw new NullPointerException("user can't be empty");
        }
        return commentDAO.addComment(comment);
    }

    public Comment selectById(Integer commentId) {
        if(commentId == null) {
            throw new NullPointerException("commentId is null");
        }
        Comment comment = commentDAO.selectById(commentId);
        if(comment == null || comment.getStatus() != 0) {
            throw new NullPointerException("comment is null");
        }
        return comment;
    }

    public Integer countComment(Integer entityType, Integer entityId) {
        if(entityId == null || entityType == null) {
            throw new NullPointerException("param is null");
        }
        return commentDAO.countComment(entityType, entityId);
    }
}
