package com.mj.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class UserUtil {

	public static User getCurrentHr() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
