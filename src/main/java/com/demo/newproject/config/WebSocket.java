package com.demo.newproject.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint(value = "/websocket", configurator = SpringConfigurator.class)
@Component
public class WebSocket {

    private static AtomicInteger onlineCount = new AtomicInteger(0);

    private static final Logger logger = LoggerFactory.getLogger(WebSocket.class);

    public WebSocket() {

    }

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        onlineCount.addAndGet(1);
        logger.info("Welcome to connect websocket, current online num is: "+ getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {

        logger.info("the message from client: " + message);
        for(WebSocket ws : webSocketSet) {
            try {
                ws.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        onlineCount.decrementAndGet();
        logger.info("one connection close, currnet online num is: " + getOnlineCount());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("Occur error!");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public int getOnlineCount() {
        return onlineCount.get();
    }

}
