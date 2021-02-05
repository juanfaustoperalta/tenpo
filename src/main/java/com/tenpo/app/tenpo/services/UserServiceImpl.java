package com.tenpo.app.tenpo.services;

import com.tenpo.app.tenpo.clients.UserClient;
import com.tenpo.app.tenpo.dtos.User;
import com.tenpo.app.tenpo.dtos.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
				implements UserService {

	private UserClient userClient;

	@Autowired
	public UserServiceImpl(UserClient userClient) {
		this.userClient = userClient;
	}

	@Override public UserResponse getUsers() {
		return userClient.getUsers();
	}

	@Override public User getUsers(Integer id) {
		return userClient.getUsers(id);
	}

}
