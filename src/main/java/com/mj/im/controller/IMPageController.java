package com.mj.im.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mj.im.service.IMUserInfoService;
import com.mj.util.ResultUtil;
import com.mj.util.StringUtil;

/**
 * 消息控制器
 * @author: MJ
 * @date: 2018年8月11日
 */
@RestController
public class IMPageController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private IMUserInfoService imService;

	@Value("${mj.kafka.msgTopic}")
	private String msgTopic; // 消息主题

	@Value("${mj.kafka.wbMsgtopic}")
	private String wbMsgtopic;// 白板消息主题

	/**
	 * @Description: 上线消息
	 * @return String  
	 * @throws
	 */
	@MessageMapping("onLineMsg")
	public String onLineMsg(@RequestBody String msg) {
		if (StringUtil.isNotNull(msg)) {
			JSONObject msgContent = JSONObject.parseObject(msg);
			if (msgContent != null) {
				String chatRoomId = msgContent.getString("ChatRoomId");
				if (StringUtil.isNotNull(chatRoomId)) {
					imService.add(chatRoomId, msgContent.getString("UserName"));
					kafkaTemplate.send(msgTopic, chatRoomId, msgContent.toString());
					return ResultUtil.success("上线消息发送成功！");
				}
			}
		}
		return ResultUtil.fail("上线消息发送失败！");
	}

	/**
	 * @Description: 接受消息，发布到kafka
	 * @return String  
	 * @throws
	 */
	@MessageMapping("msg")
	public String msg(@RequestBody String msg) {
		// TODO 根据配置项判断是否敏感词过滤
		// TODO 根据配置项判断是否备份聊天室内容
		if (StringUtil.isNotNull(msg)) {
			JSONObject msgContent = JSONObject.parseObject(msg);
			if (msgContent != null) {
				String chatRoomId = msgContent.getString("ChatRoomId");
				if (StringUtil.isNotNull(chatRoomId)) {
					kafkaTemplate.send(msgTopic, chatRoomId, msgContent.toString());
					return ResultUtil.success("消息发送成功");
				}
			}
		}
		return ResultUtil.fail("消息发送失败");
	}

	/**
	 * @Description: 接收白板消息
	 * @return String  
	 * @throws
	 */
	@MessageMapping("wbMsg")
	public String wbMsg(@RequestBody String msg) {
		// TODO 根据配置项判断是否备份聊天室内容
		if (StringUtil.isNotNull(msg)) {
			JSONObject msgContent = JSONObject.parseObject(msg);
			if (msgContent != null) {
				String chatRoomId = msgContent.getString("ChatRoomId");
				if (StringUtil.isNotNull(chatRoomId)) {
					kafkaTemplate.send(wbMsgtopic, chatRoomId, msgContent.toString());
					return ResultUtil.success("白板消息发送成功");
				}
			}
		}
		return ResultUtil.fail("白板消息发送失败");
	}

}
