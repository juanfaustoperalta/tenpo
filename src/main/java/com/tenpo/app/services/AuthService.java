package com.tenpo.app.tenpo.services;

import com.tenpo.app.tenpo.dtos.requests.LoginRequest;
import com.tenpo.app.tenpo.dtos.requests.SignupRequest;
import com.tenpo.app.tenpo.dtos.responses.JwtResponse;
import com.tenpo.app.tenpo.dtos.responses.MessageResponse;

public interface AuthService {

	JwtResponse login(LoginRequest loginRequest);

	MessageResponse signup(SignupRequest signupRequest);
}
