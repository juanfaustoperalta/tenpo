package com.tenpo.app.repository;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenRepository {

	private static final Map<String, String> jwtTokenWhiteList = new ConcurrentHashMap<>();

	public void whiteListing(String username, String newToken) {

		jwtTokenWhiteList.putIfAbsent(username, "");

		jwtTokenWhiteList.computeIfPresent(username, (key, token) -> newToken);
	}

	public void removeToken(String username) {
		jwtTokenWhiteList.computeIfPresent(username, (key, currentToken) -> "");
	}

	public Optional<String> getToken(String username) {
		return Optional.ofNullable(jwtTokenWhiteList.get(username));
	}
}
