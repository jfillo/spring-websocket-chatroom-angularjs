package com.jfillo.spring.samples.chatroom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jfillo.spring.samples.chatroom.model.ChatMessage;
import com.jfillo.spring.samples.chatroom.model.ChatUser;

@Service
public interface ChatService {

	public List<ChatUser> getParticipants();

	public List<ChatMessage> getMessageHistory();

	public void executeMessage(ChatMessage chatMessage);

	public void addParticipant(ChatUser user);

	public void removeParticipant(String wsSessionId);

}
