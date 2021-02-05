package com.tenpo.app.dtos.requests;

import com.tenpo.app.model.UserRole;

import java.util.Set;

public class SignupRequest {

	private String username;

	private Set<UserRole> role;

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

	public Set<UserRole> getRole() {
		return this.role;
	}

	public void setRole(Set<UserRole> role) {
		this.role = role;
	}
}