package com.tenpo.app.dtos.responses;

public class MessageResponse {
	private String message;


	public static MessageResponse whitMessage(String message) {
		return new MessageResponse(message);
	}

	public MessageResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}