package com.jfillo.spring.samples.chatroom.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import com.jfillo.spring.samples.chatroom.model.ChatUser;
import com.jfillo.spring.samples.chatroom.service.ChatService;

public class StompConnectListener implements ApplicationListener<SessionConnectEvent> {

	private static final Logger log = LoggerFactory.getLogger(StompConnectListener.class);
	
	@Autowired
	private ChatService chatService;
	
	@Override
	public void onApplicationEvent(SessionConnectEvent event) {
		log.debug("Connect event fired: session="+event);
		String wsSessionId = event.getMessage().getHeaders().get(SimpMessageHeaderAccessor.SESSION_ID_HEADER, String.class);
		String name = event.getMessage().getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER, UsernamePasswordAuthenticationToken.class).getName();
		ChatUser user = new ChatUser(wsSessionId, name);
		chatService.addParticipant(user);
	}
}
