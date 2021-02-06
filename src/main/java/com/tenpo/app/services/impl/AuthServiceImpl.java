package com.tenpo.app.services.impl;

import com.tenpo.app.dtos.requests.LoginRequest;
import com.tenpo.app.dtos.requests.SignupRequest;
import com.tenpo.app.dtos.responses.JwtResponse;
import com.tenpo.app.dtos.responses.MessageResponse;
import com.tenpo.app.exceptions.RoleNotFoundException;
import com.tenpo.app.exceptions.UserHasAlreadyExistException;
import com.tenpo.app.model.Role;
import com.tenpo.app.model.User;
import com.tenpo.app.repository.RoleRepository;
import com.tenpo.app.repository.UserRepository;
import com.tenpo.app.security.JwtUtils;
import com.tenpo.app.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl
				implements AuthService {

	private final AuthenticationManager authenticationManager;

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final PasswordEncoder encoder;

	private final JwtUtils jwtUtils;

	public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
					RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
		this.jwtUtils = jwtUtils;
	}


	@Override public JwtResponse login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
						.map(item -> item.getAuthority())
						.collect(Collectors.toList());
		return new JwtResponse(jwt,
						userDetails.getId(),
						userDetails.getUsername(),
						roles);
	}

	@Override public MessageResponse signup(SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new UserHasAlreadyExistException(
							String.format("This username %s has really exist", signUpRequest.getUsername()));
		}
		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
						encoder.encode(signUpRequest.getPassword()));

		Set<Role> roles = signUpRequest.getRoles()
						.stream()
						.map(role -> roleRepository.findByName(role).orElseThrow(RoleNotFoundException::new))
						.collect(Collectors.toSet());


		user.setRoles(roles);
		userRepository.save(user);

		return MessageResponse.whitMessage("User registered successfully!");
	}

	@Override public void logout(String token) {
		jwtUtils.invalidate(extractToken(token));
	}

	private String extractToken(String token) {
		String[] tokenArray = token.split(" ");
		if (tokenArray[0].equals("Bearer")) {
			return tokenArray[1];
		} else {
			return token;
		}
	}
}
