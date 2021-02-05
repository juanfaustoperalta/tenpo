package com.tenpo.app.tenpo.clients;

import com.tenpo.app.tenpo.dtos.User;
import com.tenpo.app.tenpo.dtos.UserResponse;
import com.tenpo.app.tenpo.dtos.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserClientImpl
				implements UserClient {

	private RestTemplate restTemplate;
	public static final String FOO_RESOURCE_URL = "https://reqres.in/api/users";

	@Autowired
	public UserClientImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override public UserResponse getUsers() {

		ResponseEntity<UserResponse> response = restTemplate.getForEntity(FOO_RESOURCE_URL, UserResponse.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}
		return null;
	}

	@Override public User getUsers(Integer id) {

		ResponseEntity<UserWrapper> response = restTemplate.getForEntity(FOO_RESOURCE_URL
						+ String.format("?id=%s", id), UserWrapper.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody().getUser();
		}
		return null;
	}
}
