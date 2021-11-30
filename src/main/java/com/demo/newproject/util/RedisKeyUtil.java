package com.demo.newproject.util;

public class RedisKeyUtil {

    public static final String LIKE_KEY = "LIKE";

    public static final String SPLIT = "-";

    public static final String MESSAGE_KEY = "MESSAGE";

    public static String getLikeKey(int entityType, int entityId) {
        //LIKE-1-1: 1,2,3,
        return LIKE_KEY + SPLIT + entityType + SPLIT + entityId;
    }

    public static String getMessageKey(int fromId, int toId) {
        return MESSAGE_KEY + SPLIT + fromId + SPLIT + toId;
    }
}
