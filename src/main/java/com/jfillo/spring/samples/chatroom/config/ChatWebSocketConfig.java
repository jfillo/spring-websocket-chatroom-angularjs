package com.jfillo.spring.samples.chatroom.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.jfillo.spring.samples.chatroom.listener.StompConnectListener;
import com.jfillo.spring.samples.chatroom.listener.StompDisconnectListener;
import com.jfillo.spring.samples.chatroom.service.ChatService;
import com.jfillo.spring.samples.chatroom.service.ChatServiceImpl;


@Configuration
@EnableScheduling
@EnableWebSocketMessageBroker
public class ChatWebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chatroom").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/queue/");
		registry.setApplicationDestinationPrefixes("/app");		
	}
	
	@Bean
	public ChatService chatService() {
		return new ChatServiceImpl();
	}
	
	@Bean
	public ApplicationListener<SessionDisconnectEvent> stompDisconnectListener() {
		return new StompDisconnectListener();
	}
	
	@Bean
	public ApplicationListener<SessionConnectEvent> stompConnectListener() {
		return new StompConnectListener();
	}
}
