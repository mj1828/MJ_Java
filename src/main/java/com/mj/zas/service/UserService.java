package com.mj.zas.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mj.zas.entity.ZAUser;

public interface UserService extends UserDetailsService {

	boolean insert(ZAUser record);

	ZAUser selectByName(String name);

	String login(ZAUser user);

	String List(int pageNum, int pageSize);

	String userById(String id);

	String save(ZAUser user);

	String delete(String id);
}
