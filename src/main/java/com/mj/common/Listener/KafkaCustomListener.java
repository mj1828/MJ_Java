package com.mj.common.Listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaCustomListener {
	@KafkaListener(topics = { "test" })
	public void listen(ConsumerRecord<?, ?> record) {
		System.out.println(record.key());
		System.out.println(record.value());
	}
}
