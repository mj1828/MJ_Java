package com.mj.im.code;

/**
 * 聊天室用户类型
 * 
 * @author zyc
 */
public class MemberTypeCode {
	public static final String ID = "MemberType";

	public final static String Admin = "Admin";// 管理员
	public final static String Common = "Common";// 普通用户
	public final static String Assistant = "Assistant";// 助手

	public static String getContentTypeName(String type) {
		if (Admin.equals(type)) {
			return "管理员";
		} else if (Admin.equals(type)) {
			return "普通用户";
		} else if (Assistant.equals(type)) {
			return "助手";
		} else {
			return "其他";
		}
	}

}
