package com.mj.zas.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mj.zas.util.ResultUtil;

@RestController
public class LoginContoller {

	@RequestMapping("/login")
	@CrossOrigin
	public String login() {
		return ResultUtil.needLogin(true);
	}
}
