package com.demo.newproject.service;

import com.demo.newproject.mapper.HotQueueDAO;
import com.demo.newproject.model.HotQueue;
import com.demo.newproject.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotQueueService {

    @Autowired
    UserService userService;

    @Autowired
    HotQueueDAO hotQueueDAO;

    public Integer publishHotQueue(HotQueue hotQueue) throws IllegalAccessException{
        if(hotQueue.getContent().isBlank() || hotQueue.getTitle().isBlank()) {
            throw new IllegalAccessException();
        }
        User user = userService.selectById(hotQueue.getUserId());
        if(user == null || user.getStatus() == 1) {
            throw new IllegalAccessException();
        }
        return hotQueueDAO.addHotQueue(hotQueue);
    }

    public List<HotQueue> getHotQueueByOffset(int page, int offset) {
        page -= 1;
        if(page < 0) return null;
        return hotQueueDAO.selectByPage(page, offset);
    }

    public List<HotQueue> getOwnHotQueue(int userId) throws IllegalAccessException {
        User user = userService.selectById(userId);
        if(user == null || user.getStatus() == 1) {
            throw new IllegalAccessException();
        }
        return hotQueueDAO.selectOwnQueue(userId);
    }

    public Integer deleteHotQueue(int hotqueueId) {
        HotQueue hotQueue = hotQueueDAO.selectById(hotqueueId);
        if(hotQueue == null || hotQueue.getStatus() != 0) return 0;
        return hotQueueDAO.deleteHotQueue(hotqueueId);
    }
}
