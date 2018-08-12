package com.mj.im.code;

/**
 * 聊天室禁言类型
 * 
 * @author zyc
 */
public class ChatRoomGagCode {
	public static final String ID = "ChatRoomGag";

	public final static int Close = 0;// 关闭
	public final static int Open = 1;// 开启

	public static String getContentTypeName(int type) {
		if (Close == type) {
			return "关闭";
		} else if (Open == type) {
			return "开启";
		} else {
			return "其他";
		}
	}

}
