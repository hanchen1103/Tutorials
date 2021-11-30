package com.demo.newproject.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.newproject.model.Message;
import com.demo.newproject.model.User;
import com.demo.newproject.service.MessageService;
import com.demo.newproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint(value = "/websocket/{userId}")
@Component
public class WebSocket {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    private static AtomicInteger onlineCount = new AtomicInteger(0);

    private static final Logger logger = LoggerFactory.getLogger(WebSocket.class);
    
    private Integer userId;

    private static HashMap<Integer, WebSocket> webSocketMap = new HashMap<>();

    private Session session;

    private static final Object obj = new Object();


    @OnOpen
    public void onOpen(Session session, @PathParam("userId") int userId) {
        synchronized (obj) {
            this.session = session;
            this.userId = userId;
            if(webSocketMap.containsKey(userId)) {
                webSocketMap.remove(userId);
                webSocketMap.put(userId, this);
            } else {
                webSocketMap.put(userId, this);
                onlineCount.addAndGet(1);
            }
            logger.info("Welcome to connect websocket, current online num is: "+ getOnlineCount());
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("the message from client-" + userId + ": " + message);
        if(message == null || message.isBlank()) {
            logger.warn("message can't be empty");
            return ;
        }
        JSONObject jsonObject = JSON.parseObject(message);
        Message messageDto = JSONObject.toJavaObject(jsonObject, Message.class);
        Integer toId = messageDto.getToId();
        messageDto.setFromId(this.userId);
        messageDto.setIsRead(1);
        messageDto.setStatus(0);
        User toUser = userService.selectById(toId);
        User fromUser = userService.selectById(userId);
        if(toUser == null || toUser.getStatus() > 0 || fromUser == null || fromUser.getStatus() > 0) {
            logger.error("User exception");
            return ;
        }
        try {
            if(webSocketMap.containsKey(toId)) {
                webSocketMap.get(toId).sendMessage(jsonObject.getString(message));
            }
            messageService.addMessage(messageDto);
        } catch (IOException e) {
            logger.error("Sendmessage error:" + e.getMessage());
        }

    }

    @OnClose
    public void onClose() {
        synchronized (obj) {
            if(webSocketMap.containsKey(userId)) {
                webSocketMap.remove(userId);
                onlineCount.decrementAndGet();
            }
            logger.info("one connection close, currnet online num is: " + getOnlineCount());
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("Occur error!");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static int getOnlineCount() {
        return onlineCount.get();
    }

}
