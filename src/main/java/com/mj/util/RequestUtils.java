package com.mj.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

	public static boolean isAjax(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		if ("XMLHttpRequest".equals(requestType)) {
			return true;
		} else {
			return false;
		}
	}

}
