package com.demo.newproject.service;

import com.demo.newproject.mapper.MessageDAO;
import com.demo.newproject.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageDAO messageDAO;

    public Integer addMessage(Message message){
        if(message == null) {
            throw new NullPointerException("message can't be null");
        }
        return messageDAO.addMessage(message);
    }

    public List<Message> selectMessageList(Integer fromId, Integer toId, Integer start, Integer end) throws IllegalAccessException {
        if(fromId == null || toId == null || start == null || end == null) {
            throw new NullPointerException("param can't be null");
        }
        if(start < 0 || end < 0) {
            throw new IllegalAccessException("param exception");
        }
        return messageDAO.selectByFromIdAndtoId(fromId, toId, start, end);
    }
}
