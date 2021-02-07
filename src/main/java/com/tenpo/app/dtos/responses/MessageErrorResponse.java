package com.tenpo.app.dtos.responses;

import java.time.LocalDateTime;

public class MessageErrorResponse {

	private String timesStamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

	public MessageErrorResponse(Integer status, String error, String message, String path) {
		this.timesStamp = LocalDateTime.now().toString();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public String getTimesStamp() {
		return timesStamp;
	}

	public void setTimesStamp(String timesStamp) {
		this.timesStamp = timesStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}