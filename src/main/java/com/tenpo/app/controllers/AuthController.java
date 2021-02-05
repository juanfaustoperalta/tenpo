package com.tenpo.app.controllers;

import com.tenpo.app.dtos.requests.LoginRequest;
import com.tenpo.app.dtos.requests.SignupRequest;
import com.tenpo.app.dtos.responses.JwtResponse;
import com.tenpo.app.dtos.responses.MessageResponse;
import com.tenpo.app.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired AuthService service;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

		JwtResponse jwtResponse = service.login(loginRequest);

		return ResponseEntity.ok(jwtResponse);
	}

	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> signup(@RequestBody SignupRequest signUpRequest) {
		MessageResponse messageResponse = service.signup(signUpRequest);

		return ResponseEntity.ok(messageResponse);
	}

	@GetMapping("/logout")
	public ResponseEntity<Object> logout(@RequestHeader(value = "Authorization") String authorization) {
		service.logout(authorization);
		return ResponseEntity.accepted().build();
	}
}