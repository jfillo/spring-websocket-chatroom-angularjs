package com.jfillo.spring.samples.chatroom.model;

import java.io.Serializable;

public class ChatUser implements Serializable {
	private static final long serialVersionUID = -6123964826275773985L;
	
	private final String wsSessionId;
	private final String name;
	
	public ChatUser(String wsSessionId, String name) {
		this.wsSessionId = wsSessionId;
		this.name = name;
	}
	
	public String getWsSessionId() {
		return wsSessionId;
	}
	
	public String getName() {
		return name;
	}
}
