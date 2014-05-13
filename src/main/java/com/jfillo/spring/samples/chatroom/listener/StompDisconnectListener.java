package com.jfillo.spring.samples.chatroom.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.jfillo.spring.samples.chatroom.service.ChatService;

public class StompDisconnectListener implements ApplicationListener<SessionDisconnectEvent> {

	private static final Logger log = LoggerFactory.getLogger(StompDisconnectListener.class);
	
	@Autowired
	private ChatService chatService;
	
	@Override
	public void onApplicationEvent(SessionDisconnectEvent event) {
		log.debug("Disconnect event fired: session="+event);
		String wsSessionId = event.getSessionId();
		chatService.removeParticipant(wsSessionId);
	}

}
