package com.mj.im.service;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.mj.zas.entity.ZAUser;
import com.mj.zas.service.UserService;

/**
 * redis 存储聊天室、用户关系，本地存储用户会话
 * 
 * @author zyc
 *
 */
@Service
public class IMUserInfoService {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private UserService userService;

	private final String CHATROOMLIST = "ChatRoomList"; // 聊天室用户列表

	private final String USERINFO = "UserInfo"; // 聊天室用户列表

	private static final String SYCN = "sycn";

	// 聊天室添加用户
	public boolean add(String chatRoomId, String userName) {
		synchronized (SYCN) {
			if (IMUserSessionService.containsKey(userName)) {
				JSONObject userInfo = getUserInfo(userName);
				userInfo.put("ChatRoomId", chatRoomId);
				redisTemplate.opsForHash().put(USERINFO, userName, userInfo);
				if (redisTemplate.opsForList().remove(CHATROOMLIST + chatRoomId, 1, userName) != null) {
					long result = redisTemplate.opsForList().rightPush(CHATROOMLIST + chatRoomId, userName);
					if (result > 0) {// 发送消息
						return true;
					}
				}
			}
			return false;
		}
	}

	// 聊天室删除用户
	public boolean del(String userName) {
		synchronized (SYCN) {
			if (IMUserSessionService.del(userName)) {
				JSONObject userInfo = (JSONObject) redisTemplate.opsForHash().get(USERINFO, userName);
				redisTemplate.opsForHash().delete(USERINFO, userName);
				String chatRoomId = userInfo.getString("ChatRoomId");
				if (redisTemplate.opsForList().remove(CHATROOMLIST + chatRoomId, 1, userName) != null) {
					return true;
				}
			}
			return false;
		}
	}

	// 获取聊天室所有用户
	public ArrayList<JSONObject> chatRoomUsers(String chatRoomId) {
		ArrayList<JSONObject> userList = new ArrayList<JSONObject>();
		Set<String> userSet = redisTemplate.opsForZSet().range(CHATROOMLIST + chatRoomId, 0, -1);
		userSet.forEach(user -> {
			Object o = redisTemplate.opsForHash().get(USERINFO, user);
			if (o != null) {
				// 根据需求排序
				userList.add((JSONObject) o);
			}
		});
		return userList;
	}

	public JSONObject userInfo(String userName) {
		return (JSONObject) redisTemplate.opsForHash().get(USERINFO, userName);
	}

	// 获取用户信息
	private JSONObject getUserInfo(String userName) {
		ZAUser user = userService.selectByName(userName);
		JSONObject userInfo = new JSONObject();
		userInfo.put("Head", user.getHead());
		userInfo.put("RealName", "真实姓名");
		userInfo.put("UserName", userName);
		userInfo.put("EnterTime", System.currentTimeMillis());
		return userInfo;
	}
}
