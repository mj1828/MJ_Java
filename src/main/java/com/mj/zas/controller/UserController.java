package com.mj.zas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mj.zas.entity.ZAUser;
import com.mj.zas.service.UserService;

@RequestMapping("/yhgl")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/list")
	public String list(int pageNum, int pageSize) {
		return userService.List(pageNum, pageSize);
	}

	@GetMapping("/user/{id}")
	public String userById(@PathVariable String id) {
		return userService.userById(id);
	}

	@PostMapping("/save")
	public String save(ZAUser user) {
		return userService.save(user);
	}

	@DeleteMapping("/delete/{id}")
	public String del(@PathVariable String id) {
		return userService.delete(id);
	}
}
