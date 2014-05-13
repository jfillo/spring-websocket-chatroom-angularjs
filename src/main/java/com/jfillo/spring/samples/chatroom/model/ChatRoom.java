package com.jfillo.spring.samples.chatroom.model;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatRoom implements Serializable {
	private static final long serialVersionUID = 7077941558334947371L;
	private long id;
	private String welcomeMessage;
	private List<ChatUser> users;
	private boolean modified;
	private List<ChatMessage> messageHistory = new CopyOnWriteArrayList<ChatMessage>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getWelcomeMessage() {
		return welcomeMessage;
	}
	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}
	public List<ChatUser> getUsers() {
		return users;
	}
	public void setUsers(List<ChatUser> users) {
		this.users = users;
	}
	public boolean isModified() {
		return modified;
	}
	public void setModified(boolean modified) {
		this.modified = modified;
	}
	public List<ChatMessage> getMessageHistory() {
		return messageHistory;
	}
	public void setMessageHistory(List<ChatMessage> messageHistory) {
		this.messageHistory = messageHistory;
	}
}
