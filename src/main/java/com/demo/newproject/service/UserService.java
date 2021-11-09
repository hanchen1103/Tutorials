package com.demo.newproject.service;

import com.demo.newproject.mapper.UserDAO;
import com.demo.newproject.model.User;
import com.demo.newproject.util.newProjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public Map<String, Object> register(String account, String password) {
        Map<String, Object> map = new HashMap<>();
        if(account.isEmpty() || password.isEmpty()) {
            map.put("msg", "用户名或密码不能为空");
            return map;
        }
        int count = userDAO.isExist(account);
        if(count != 0) {
            map.put("msg", "用户名已被注册");
            return map;
        }
        User user = new User();
        String salt = UUID.randomUUID().toString().substring(0, 6);
        user.setSalt(salt);
        user.setAccount(account);
        String head_url = "http://127.0.0.1:8080/image/2.jpg";
        user.setHead_url(head_url);
        user.setPassword(newProjectUtil.MD5(password + salt));
        user.setStatus(0);
        userDAO.addUser(user);
        map.put("id", user.getId());
        return map;
    }

    public Map<String, Object> Login(String account, String password) {
        Map<String, Object> map = new HashMap<>();
        if(account.isEmpty() || password.isEmpty()) {
            map.put("msg", "用户名或密码不能为空");
            return map;
        }
        int count = userDAO.isExist(account);
        if(count == 0) {
            map.put("msg", "无当前用户名");
            return map;
        }
        User user = userDAO.selectByUserAccount(account);
        if(!newProjectUtil.MD5(password + user.getSalt()).equals(user.getPassword())) {
            map.put("msg", "密码错误");
            return map;
        }
        map.put("user", user);
        return map;
    }

    public int updateHead_url(String head_url, int id) {
        if(userDAO.selectByuserId(id) == null) {
            return -1;
        }
        return userDAO.updateHead_url(head_url, id);
    }

    public User selectById(int id) {
        return userDAO.selectByuserId(id);
    }
}
