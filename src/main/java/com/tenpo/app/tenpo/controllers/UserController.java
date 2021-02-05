package com.tenpo.app.tenpo.controllers;

import com.tenpo.app.tenpo.dtos.User;
import com.tenpo.app.tenpo.dtos.UserResponse;
import com.tenpo.app.tenpo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public @ResponseBody UserResponse findAllUser() {
		return userService.getUsers();
	}

	@GetMapping("/{id}")
	public @ResponseBody User findUserById(@PathVariable(value = "id") Integer id) {
		return userService.getUsers(id);
	}
}
