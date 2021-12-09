package com.demo.newproject.service;

import com.demo.newproject.mapper.MessageDAO;
import com.demo.newproject.model.Message;
import com.demo.newproject.model.User;
import com.demo.newproject.util.JedisAdapter;
import com.demo.newproject.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    @Autowired
    MessageDAO messageDAO;

    @Autowired
    JedisAdapter jedisAdapter;

    @Autowired
    UserService userService;

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

    public Integer clearUnRead(Integer toId) {
        if(toId == null) {
            throw new NullPointerException();
        }
        return messageDAO.clearUnReadMessage(toId);
    }

    public Map<String, Object> selectByFromIdAnd2Id(Integer fromId, Integer toId) throws IllegalAccessException {
        if(fromId == null || toId == null) {
            throw new NullPointerException();
        }
        User fromUser = userService.selectById(fromId);
        User toUser = userService.selectById(toId);
        if(fromUser == null || toUser == null || fromUser.getStatus() > 0 || toUser.getStatus() > 0) {
            throw new IllegalAccessException("user exception");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("fromHeadUrl", fromUser.getHead_url());
        map.put("fromName", fromUser.getAccount());
        map.put("toHeadUrl", toUser.getHead_url());
        map.put("toName", toUser.getAccount());
        map.put("messageList", messageDAO.selectByFromIdAndToId(fromId, toId));
        return map;
    }


}
