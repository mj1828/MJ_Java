package com.mj.im.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

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
public class IMSevice {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private UserService userService;

	private final String CHATROOMLIST = "ChatRoomList"; // 聊天室用户列表

	private final String USERINFO = "UserInfo"; // 聊天室用户列表

	private final String SYCN = "";

	// 聊天室添加用户
	public boolean add(String chatRoomId, String userName) {
		synchronized (SYCN) {
			if (UserSessionService.containsKey(userName)) {
				JSONObject userInfo = getUserInfo(userName);
				userInfo.put("ChatRoomId", chatRoomId);
				redisTemplate.opsForHash().put(USERINFO, userName, userInfo);
				if (redisTemplate.opsForList().remove(CHATROOMLIST + chatRoomId, 1, userName) != null) {
					long result = redisTemplate.opsForList().rightPush(CHATROOMLIST + chatRoomId, userName);
					if (result > 0) {// 发送消息
						msgToChatRoom(chatRoomId, getOnLineMsg(userInfo));
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
			if (UserSessionService.del(userName)) {
				JSONObject userInfo = (JSONObject) redisTemplate.opsForHash().get(USERINFO, userName);
				redisTemplate.opsForHash().delete(USERINFO, userName);
				String chatRoomId = userInfo.getString("ChatRoomId");
				if (redisTemplate.opsForList().remove(CHATROOMLIST + chatRoomId, 1, userName) != null) {
					JSONObject msgInfo = getUserInfo(userName);
					msgToChatRoom(chatRoomId, getOffLineMsg(msgInfo));
					return true;
				}
			}
			return false;
		}
	}

	// 发送给聊天室
	public boolean msgToChatRoom(String chatRoomId, String userName, String msgContent) {
		JSONObject userInfo = getUserInfo(userName);
		return msgToChatRoom(chatRoomId, getMsg(userInfo, msgContent));

	}

	// 发送给聊天室
	public boolean msgToChatRoom(String chatRoomId, String msg) {
		synchronized (SYCN) {
			List<String> userlist = redisTemplate.opsForList().range(CHATROOMLIST + chatRoomId, 0, -1);
			userlist.forEach(user -> {
				try {
					WebSocketSession session = UserSessionService.get(user);
					if (session != null) {
						session.sendMessage(new TextMessage(msg));
						System.out.println("发送聊天室消息");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			return true;
		}
	}

	// 发送给用户
	public boolean msgToUser(String userName, String msg) {
		synchronized (SYCN) {
			try {
				WebSocketSession session = UserSessionService.get(userName);
				if (session != null) {
					session.sendMessage(new TextMessage(msg));
					System.out.println("发送用户");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
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

	// 上线消息
	private String getOnLineMsg(JSONObject msg) {
		msg.put("MsgType", "上线");
		msg.put("SendTime", System.currentTimeMillis());
		return msg.toString();
	}

	// 下线消息
	private String getOffLineMsg(JSONObject msg) {
		msg.put("MsgType", "下线");
		msg.put("SendTime", System.currentTimeMillis());
		return msg.toString();
	}

	// 基本消息
	private String getMsg(JSONObject msg, String msgContent) {
		msg.put("MsgType", "基本类型");
		msg.put("Msg", msgContent);
		msg.put("SendTime", System.currentTimeMillis());
		return msg.toString();
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
