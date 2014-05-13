package com.jfillo.spring.samples.chatroom.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.jfillo.spring.samples.chatroom.listener.StompConnectListener;
import com.jfillo.spring.samples.chatroom.listener.StompDisconnectListener;


@Configuration
@EnableScheduling
@ComponentScan(
		basePackages="com.jfillo.spring.samples.chatroom",
		excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, value = Configuration.class)
		)
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
	public ApplicationListener<SessionDisconnectEvent> stompDisconnectListener() {
		return new StompDisconnectListener();
	}
	
	@Bean
	public ApplicationListener<SessionConnectEvent> stompConnectListener() {
		return new StompConnectListener();
	}
}
