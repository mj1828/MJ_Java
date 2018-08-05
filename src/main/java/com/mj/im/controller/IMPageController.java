package com.mj.im.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IMPageController {

	@GetMapping("/socket")
	public String socket() {
		return "socket";
	}

	@GetMapping("/imList")
	public String imList() {
		return "imList";
	}

	@GetMapping("/imDetail")
	public String imDetail() {
		return "imDetail";
	}
}
