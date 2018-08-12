package com.mj.im.code;

/**
 * 用户禁言禁言类型
 * 
 * @author zyc
 */
public class MemberProhibitCode {
	public static final String ID = "MemberProhibit";

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
