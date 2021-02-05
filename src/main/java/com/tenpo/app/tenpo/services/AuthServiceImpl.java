package com.tenpo.app.tenpo.services;

import com.tenpo.app.tenpo.dtos.requests.LoginRequest;
import com.tenpo.app.tenpo.dtos.requests.SignupRequest;
import com.tenpo.app.tenpo.dtos.responses.JwtResponse;
import com.tenpo.app.tenpo.dtos.responses.MessageResponse;
import com.tenpo.app.tenpo.model.Role;
import com.tenpo.app.tenpo.model.User;
import com.tenpo.app.tenpo.model.UserRole;
import com.tenpo.app.tenpo.repository.RoleRepository;
import com.tenpo.app.tenpo.repository.UserRepository;
import com.tenpo.app.tenpo.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl
				implements AuthService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;


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
			return new MessageResponse("Error: Username is already taken!");
		}
		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
						encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(UserRole.USER_ROLE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				if ("admin".equals(role)) {
					Role adminRole = roleRepository.findByName(UserRole.ADMIN_ROLE)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
				} else {
					Role userRole = roleRepository.findByName(UserRole.USER_ROLE)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return new MessageResponse("User registered successfully!");
	}
}
