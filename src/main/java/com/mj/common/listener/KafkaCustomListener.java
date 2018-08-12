package com.mj.common.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.alibaba.fastjson.JSONObject;

/**
 * 消息消费者
 * @author: MJ 
 * @date: 2018年8月11日
 */
public class KafkaCustomListener {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	/**
	 * @Description: 消息消费 
	 * @return void  
	 * @throws
	 */
	@KafkaListener(topics = { "msgTopic" })
	public void lisMsgTopic(ConsumerRecord<String, String> record) {
		JSONObject msg = JSONObject.parseObject(record.value());
		String chatRoomId = msg.getString("ChatRoomId");
		simpMessagingTemplate.convertAndSend("/topic/msg/" + chatRoomId, msg);
	}

	/**
	 * @Description: 白板消息消费
	 * @return void  
	 * @throws
	 */
	@KafkaListener(topics = { "wbMsgTopic" })
	public void lisWBMsgTopic(ConsumerRecord<String, String> record) {
		JSONObject msg = JSONObject.parseObject(record.value());
		String chatRoomId = msg.getString("ChatRoomId");
		simpMessagingTemplate.convertAndSend("/topic/wb/" + chatRoomId, msg);
	}
}
