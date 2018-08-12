package com.mj.im.config;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

import com.alibaba.fastjson.JSONObject;
import com.mj.im.service.IMUserInfoService;
import com.mj.im.service.IMUserSessionService;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Autowired
	private IMUserInfoService imUserInfoService;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${mj.kafka.msgTopic}")
	private String msgTopic; // 消息主题

	/**
	 * 将"/hello"路径注册为STOMP端点，这个路径与发送和接收消息的目的路径有所不同，这是一个端点，客户端在订阅或发布消息到目的地址前，要连接该端点，
	 * 即用户发送请求url="/applicationName/hello"与STOMP server进行连接。之后再转发到订阅url；
	 * PS：端点的作用——客户端在订阅或发布消息到目的地址前，要连接该端点。
	 * 
	 * @param stompEndpointRegistry
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
//		stompEndpointRegistry.addEndpoint("/hello").setAllowedOrigins("*").withSockJS();
		stompEndpointRegistry.addEndpoint("ws").setAllowedOrigins("*");
	}

	/**
	 * 配置了一个简单的消息代理，如果不重载，默认情况下回自动配置一个简单的内存消息代理，用来处理以"/topic"为前缀的消息。这里重载configureMessageBroker()方法，
	 * 消息代理将会处理前缀为"/topic"和"/queue"的消息。
	 * 
	 * @param registry
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 应用程序以/app为前缀，代理目的地以/topic、/user为前缀
		registry.enableSimpleBroker("/topic", "/user");
		registry.setApplicationDestinationPrefixes("/mj");
		registry.setUserDestinationPrefix("/user");
	}

	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
		registry.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
			@Override
			public WebSocketHandler decorate(final WebSocketHandler handler) {
				return new WebSocketHandlerDecorator(handler) {
					@Override
					public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
						// 客户端与服务器端建立连接后，此处记录谁上线了
						Principal userInfo = session.getPrincipal();
						if (userInfo != null) {
							IMUserSessionService.add(userInfo.getName(), session);
							System.err.println("保存用户到列表" + userInfo.getName());
						}
						super.afterConnectionEstablished(session);
					}

					@Override
					public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus)
							throws Exception {
						// 客户端与服务器端断开连接后，此处记录谁下线了
						Principal principal = session.getPrincipal();
						if (principal != null) {
							String userName = principal.getName();
							// 删除缓存session信息
							IMUserSessionService.del(userName);
							// 删除用户缓存信息
							JSONObject userInfo = imUserInfoService.userInfo(userName);
							imUserInfoService.del(userName);
							// 发送下线消息
							JSONObject msg = new JSONObject();
							msg.put("UserName", userName);
							msg.put("MsgType", "OffLine");
							kafkaTemplate.send(msgTopic, userInfo.getString("ChatRoomId"), msg.toString());
							System.err.println("将用户从列表中删除" + userName);
						}
						super.afterConnectionClosed(session, closeStatus);
					}
				};
			}
		});
		WebSocketMessageBrokerConfigurer.super.configureWebSocketTransport(registry);
	}

}
