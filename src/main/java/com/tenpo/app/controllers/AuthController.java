package com.tenpo.app.tenpo.controllers;

import com.tenpo.app.tenpo.dtos.requests.LoginRequest;
import com.tenpo.app.tenpo.dtos.requests.SignupRequest;
import com.tenpo.app.tenpo.dtos.responses.JwtResponse;
import com.tenpo.app.tenpo.dtos.responses.MessageResponse;
import com.tenpo.app.tenpo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired AuthService service;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		JwtResponse jwtResponse = service.login(loginRequest);

		return ResponseEntity.ok(jwtResponse);
	}

	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> registerUser(@RequestBody SignupRequest signUpRequest) {
		MessageResponse messageResponse = service.signup(signUpRequest);

		return ResponseEntity.ok(messageResponse);
	}
}