package com.jfillo.spring.samples.chatroom.web;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.jfillo.spring.samples.chatroom.model.ChatMessage;
import com.jfillo.spring.samples.chatroom.model.ChatUser;
import com.jfillo.spring.samples.chatroom.service.ChatService;

@Controller
public class ChatController {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ChatController.class);
	
	@Autowired
	private ChatService chatService;

	@SubscribeMapping("/participants")
	public List<ChatUser> getParticipants() {
		return this.chatService.getParticipants();
	}
	
	@SubscribeMapping("/messageHistory")
	public List<ChatMessage> getMessageHistory() {
		return this.chatService.getMessageHistory();
	}
	
	@MessageMapping(value = "/newmessage")
	public void executeMessage(String message, Principal principal) {
		this.chatService.executeMessage(new ChatMessage(principal.getName(), message));
	}
	
	@MessageExceptionHandler
	@SendToUser(value="/queue/errors")
	public String handleException(Throwable exception) {
		return exception.getMessage();
	}
}
