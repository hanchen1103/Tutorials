package com.demo.newproject.service;

import com.demo.newproject.mapper.MessageDAO;
import com.demo.newproject.model.Message;
import com.demo.newproject.util.JedisAdapter;
import com.demo.newproject.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageDAO messageDAO;

    @Autowired
    JedisAdapter jedisAdapter;

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
        jedisAdapter.del(RedisKeyUtil.getMessageKey(fromId, toId));
        return messageDAO.selectByFromIdAndtoId(fromId, toId, start, end);
    }

    /**
     * 红点消息提示
     * @param fromId fromid
     * @param toId toid
     * @return 未读消息数量
     */
    public String getMessageHot(Integer fromId, Integer toId) {
        if(fromId == null || toId == null) {
            throw new NullPointerException();
        }
        return jedisAdapter.get(RedisKeyUtil.getMessageKey(fromId, toId));
    }

    public List<Message> selectUnRead(Integer toId) {
        if(toId == null) {
            throw new NullPointerException();
        }
        return messageDAO.selectUnReadMessage(toId);
    }


}
