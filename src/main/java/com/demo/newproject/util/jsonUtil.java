package com.demo.newproject.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class jsonUtil {
    public static String getJSONString(int code) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        return json.toJSONString();
    }

    public static String getJSONString(int code, String msg) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        return json.toJSONString();
    }

    public static String getJSONString(int code, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.putAll(map);
        return json.toJSONString();
    }

    public static String getJSONString(int code, Object obj) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", obj);
        return json.toJSONString();
    }
}
