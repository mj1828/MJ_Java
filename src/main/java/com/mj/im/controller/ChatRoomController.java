package com.mj.im.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mj.im.entity.ZCChatRoom;
import com.mj.im.service.ChatRoomService;

/**
 * 聊天室控制器
 * 
 * @author zyc
 *
 */
@RestController
@RequestMapping("ltsgl")
public class ChatRoomController {

	@Autowired
	private ChatRoomService chatRoomService;

	@GetMapping("/list")
	public String list(int pageNum, int pageSize) {
		return chatRoomService.List(pageNum, pageSize);
	}

	@GetMapping("/chatRoom/{id}")
	public String chatRoomById(@PathVariable String id) {
		return chatRoomService.chatRoomById(id);
	}

	@PostMapping("/save")
	public String save(ZCChatRoom chatRoom) {
		return chatRoomService.save(chatRoom);
	}

	@DeleteMapping("/delete/{id}")
	public String del(@PathVariable String id) {
		return chatRoomService.delete(id);
	}
}
