package com.tenpo.app.tenpo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class UserResponse {

	@JsonProperty("data")
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
