package com.tenpo.app.dtos.requests;

import com.tenpo.app.model.UserRole;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class SignupRequest {

	@NotEmpty(message = "username is empty")
	private String username;

	@NotEmpty(message = "password is empty")
	private String password;

	@NotEmpty(message = "Role is empty")
	private Set<UserRole> roles;

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

	public Set<UserRole> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
}