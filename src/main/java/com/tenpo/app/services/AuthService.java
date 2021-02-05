package com.tenpo.app.services;

import com.tenpo.app.dtos.requests.LoginRequest;
import com.tenpo.app.dtos.requests.SignupRequest;
import com.tenpo.app.dtos.responses.JwtResponse;
import com.tenpo.app.dtos.responses.MessageResponse;

public interface AuthService {

	JwtResponse login(LoginRequest loginRequest);

	MessageResponse signup(SignupRequest signupRequest);
}
