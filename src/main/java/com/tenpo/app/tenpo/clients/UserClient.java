package com.tenpo.app.tenpo.clients;

import com.tenpo.app.tenpo.dtos.User;
import com.tenpo.app.tenpo.dtos.UserResponse;
import org.springframework.stereotype.Service;


@Service
public interface UserClient {

	UserResponse getUsers();

	User getUsers(Integer id);
}
