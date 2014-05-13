package com.jfillo.spring.samples.chatroom.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.jfillo.spring.samples.chatroom.model.ChatMessage;
import com.jfillo.spring.samples.chatroom.model.ChatRoom;
import com.jfillo.spring.samples.chatroom.model.ChatUser;

@Service
public class ChatService {	
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ChatService.class);
	private final SimpMessageSendingOperations messagingTemplate;
	
	private static final String PARTICIPANT_UPDATES_DEST = "/queue/participant-updates";
	private static final String MESSAGE_UPDATE_DEST = "/queue/message-updates";
	
	private final ChatRoom chatRoom = new ChatRoom();
	
	@Autowired
	public ChatService(SimpMessageSendingOperations messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
		this.chatRoom.setUsers(new CopyOnWriteArrayList<ChatUser>());
	}
	
	public void executeMessage(ChatMessage message) {
		ChatMessage cm = new ChatMessage(message.getName(), message.getMessage());
		this.chatRoom.getMessageHistory().add(cm);
		this.messagingTemplate.convertAndSend(MESSAGE_UPDATE_DEST, cm);
	}
	
	public void addParticipant(ChatUser user) {
		if(!this.chatRoom.getUsers().contains(user)) {
			this.chatRoom.getUsers().add(user);
			this.messagingTemplate.convertAndSend(PARTICIPANT_UPDATES_DEST, getParticipants());
		}
	}
	
	public List<ChatUser> getParticipants() {
		return this.chatRoom.getUsers();
	}
	
	public void removeParticipant(String wsSessionId) {
		for(ChatUser user : this.chatRoom.getUsers()) {
			if(user.getWsSessionId().equals(wsSessionId)) {
				this.chatRoom.getUsers().remove(user);
				this.messagingTemplate.convertAndSend(PARTICIPANT_UPDATES_DEST, getParticipants());
			}
		}
	}
	
	public List<ChatMessage> getMessageHistory() {
		return this.chatRoom.getMessageHistory();
	}
}
