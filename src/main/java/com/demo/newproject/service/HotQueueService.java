package com.demo.newproject.service;

import com.demo.newproject.mapper.CommentDAO;
import com.demo.newproject.mapper.HotQueueDAO;
import com.demo.newproject.model.EntityType;
import com.demo.newproject.model.HotQueue;
import com.demo.newproject.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class HotQueueService {

    @Autowired
    UserService userService;

    @Autowired
    HotQueueDAO hotQueueDAO;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    public Integer publishHotQueue(HotQueue hotQueue) throws IllegalAccessException{
        if(hotQueue.getTitle() == null || hotQueue.getContent() == null ||
                hotQueue.getContent().isBlank() || hotQueue.getTitle().isBlank()) {
            throw new NullPointerException("title or content can't be empty");
        }
        User user = userService.selectById(hotQueue.getUserId());
        if(user == null || user.getStatus() == 1) {
            throw new IllegalAccessException();
        }
        return hotQueueDAO.addHotQueue(hotQueue);
    }

    public List<HotQueue> getHotQueueByOffset(Integer page, Integer offset) throws IllegalAccessException {
        if(page == null || offset == null) {
            throw new NullPointerException("page or offset can't be null");
        }
        page -= 1;
        if(page < 0) {
            throw new IllegalAccessException("page is illegal");
        }
        return hotQueueDAO.selectByPage(page, offset);
    }

    public List<HotQueue> getOwnHotQueue(int userId) throws IllegalAccessException {
        User user = userService.selectById(userId);
        if(user == null || user.getStatus() == 1) {
            throw new IllegalArgumentException("error Argument");
        }
        return hotQueueDAO.selectOwnQueue(userId);
    }

    public Integer deleteHotQueue(Integer hotqueueId) {
        if(hotqueueId == null) {
            throw new NullPointerException("hotqueueId is null");
        }
        HotQueue hotQueue = hotQueueDAO.selectById(hotqueueId);
        if(hotQueue == null || hotQueue.getStatus() != 0) {
            throw new NullPointerException("hotqueue is null");
        }
        return hotQueueDAO.deleteHotQueue(hotqueueId);
    }

    public HotQueue selectById(Integer hotqueueId) {
        if(hotqueueId == null) {
            throw new NullPointerException("hotqueueId is null");
        }
        HotQueue hotQueue = hotQueueDAO.selectById(hotqueueId);
        if(hotQueue == null || hotQueue.getStatus() != 0) {
            throw new NullPointerException("hotqueue is null");
        }
        return hotQueue;
    }

    public Map<String, Object> getQueueInfo(int queueId) {
        HotQueue hotqueue = selectById(queueId);
        int userId = hotqueue.getUserId();
        User user = userService.selectById(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("head_url", user.getHead_url());
        map.put("userId", userId);
        map.put("hotqueue", hotqueue);
        map.put("commentCount", commentService.countComment(EntityType.HOTQUEUE, queueId));
        map.put("likeStatus", likeService.getLikeStatus(userId, EntityType.HOTQUEUE, queueId));
        map.put("likeCount", likeService.getLikeCount(EntityType.HOTQUEUE, queueId));
        return map;
    }

    public List<Map<String, Object>> getQueueInfoList(Integer page, Integer offset) throws IllegalAccessException {
        List<HotQueue> queueList = getHotQueueByOffset(page, offset);
        List<Map<String, Object>> res = new LinkedList<>();
        for(HotQueue hq : queueList) {
            Map<String, Object> map = getQueueInfo(hq.getId());
            res.add(map);
        }
        return res;
    }
}
