package com.mj.im.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.WebSocketSession;

/**
 * 保存在线用户
 * @author: MJ 
 * @date: 2018年8月12日
 */
public class IMUserSessionService {

	private static ConcurrentHashMap<String, WebSocketSession> userInfos = new ConcurrentHashMap<String, WebSocketSession>();

	public static boolean add(String userName, WebSocketSession session) {
		userInfos.put(userName, session);
		return true;
	}

	public static boolean del(String userName) {
		if (userInfos.containsKey(userName)) {
			userInfos.remove(userName);
			return true;
		}
		return false;
	}

	public static boolean containsKey(String userName) {
		return userInfos.containsKey(userName);
	}

	public static WebSocketSession get(String userName) {
		if (userInfos.containsKey(userName)) {
			return userInfos.get(userName);
		}
		return null;
	}
}
