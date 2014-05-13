package com.jfillo.spring.samples.chatroom.model;

import java.io.Serializable;

public class ChatMessage implements Serializable {
	private static final long serialVersionUID = 5535659593459219696L;
	
	private final String name;
	private final String message;
	private final long timestamp;
	
	public ChatMessage(String name, String message) {
		this.name = name;
		this.message = message;
		this.timestamp = System.currentTimeMillis();
	}
	
	public String getName() {
		return name;
	}
	public String getMessage() {
		return message;
	}
	public long getTimestamp() {
		return timestamp;
	}
}
