package com.mj.zas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@RequestMapping("index")
	public String page() {
		return "/index";
	}

	@RequestMapping("kafka")
	public void kafka() {
		kafkaTemplate.send("test", "key", "value");
	}
}
