package com.tenpo.app.controllers;

import com.tenpo.app.aspect.MetricRecorder;
import com.tenpo.app.dtos.requests.LoginRequest;
import com.tenpo.app.dtos.requests.SignupRequest;
import com.tenpo.app.dtos.responses.JwtResponse;
import com.tenpo.app.dtos.responses.MessageResponse;
import com.tenpo.app.model.TransactionName;
import com.tenpo.app.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService service;

	public AuthController(AuthService service) {
		this.service = service;
	}

	@MetricRecorder(name = TransactionName.LOGIN)
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {

		JwtResponse jwtResponse = service.login(loginRequest);

		return ResponseEntity.ok(jwtResponse);
	}

	@MetricRecorder(name = TransactionName.SIGNUP)
	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> signup(@RequestBody @Valid SignupRequest signUpRequest) {
		MessageResponse messageResponse = service.signup(signUpRequest);

		return ResponseEntity.ok(messageResponse);
	}

	@MetricRecorder(name = TransactionName.LOGOUT)
	@PatchMapping("/logout")
	public ResponseEntity<Object> logout(@RequestHeader(value = "Authorization") String authorization) {
		service.logout(authorization);
		return ResponseEntity.accepted().build();
	}
}