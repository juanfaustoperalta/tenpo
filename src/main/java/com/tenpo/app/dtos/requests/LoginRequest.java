package com.tenpo.app.dtos.requests;

import javax.validation.constraints.NotEmpty;

public class LoginRequest {

	@NotEmpty(message = "username is mandatory")
	private String username;

	@NotEmpty(message = "password is mandatory")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}