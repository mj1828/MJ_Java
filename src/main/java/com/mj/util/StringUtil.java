package com.mj.util;

/**
 * @ClassName: StringUtil
 * @Description: String 类型工具类，不定时添加功能
 * @author: Zyc
 * @date: 2018年1月27日 下午1:09:06
 */
public class StringUtil {

	/**
	 * @Description: 判断是否为空
	 * @param: @param
	 *             value
	 * @param: @return
	 */
	public static boolean isNotNull(String value) {
		if (value != null) {
			if (value.equals("") || value.equals("null")) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
}
