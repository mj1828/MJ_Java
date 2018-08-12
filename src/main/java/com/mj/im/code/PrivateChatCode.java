package com.mj.im.code;

/**
 * 聊天室私聊
 * 
 * @author zyc
 */
public class PrivateChatCode {
	public static final String ID = "PrivateChat";

	public final static int No = 0;// 关闭
	public final static int Yes = 1;// 开启

	public static String getContentTypeName(int type) {
		if (No == type) {
			return "否";
		} else if (Yes == type) {
			return "是";
		} else {
			return "其他";
		}
	}
}
