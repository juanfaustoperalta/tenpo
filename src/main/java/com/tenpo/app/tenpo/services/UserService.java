package com.tenpo.app.tenpo.services;

import com.tenpo.app.tenpo.dtos.User;
import com.tenpo.app.tenpo.dtos.UserResponse;

public interface UserService {
	UserResponse getUsers();

	User getUsers(Integer id);
}
