package com.tenpo.app.services;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.tenpo.app.dtos.requests.SignupRequest;
import com.tenpo.app.dtos.responses.JwtResponse;
import com.tenpo.app.model.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@SqlGroup(@Sql(scripts = "classpath:test-data.sql"))
public class AuthServiceTest {

	public static final String API_AUTH_SIGNUP = "/api/auth/signup";
	public static final String API_AUTH_LOGIN = "/api/auth/login";
	public static final String API_MATH = "/api/math/1/2";
	public static final String API_AUTH_LOGOUT = "/api/auth/logout";
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders
						.webAppContextSetup(context)
						.apply(springSecurity())
						.build();
	}

	@Test
	public void test_signup_success() throws Exception {
		SignupRequest signupRequest = new SignupRequest("test_2", "test", Lists.newArrayList(UserRole.USER_ROLE));

		this.mockMvc.perform(
						MockMvcRequestBuilders.post(API_AUTH_SIGNUP)
										.content(objectMapper.writeValueAsString(signupRequest))
										.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk());
	}

	@Test
	public void test_signup_fail() throws Exception {
		SignupRequest signupRequest = new SignupRequest("test_2", "", Lists.newArrayList(UserRole.USER_ROLE));

		this.mockMvc.perform(
						MockMvcRequestBuilders.post(API_AUTH_SIGNUP)
										.content(objectMapper.writeValueAsString(signupRequest))
										.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isBadRequest());
	}

	@Test
	public void test_signup_failed_because_user_already_exist() throws Exception {
		SignupRequest signupRequest = new SignupRequest("test", "test", Lists.newArrayList(UserRole.USER_ROLE));

		this.mockMvc.perform(
						MockMvcRequestBuilders.post(API_AUTH_SIGNUP)
										.content(objectMapper.writeValueAsString(signupRequest))
										.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isBadRequest());
	}

	@Test
	public void test_login_success() throws Exception {
		SignupRequest signupRequest = new SignupRequest("test", "test", Lists.newArrayList(UserRole.USER_ROLE));

		this.mockMvc.perform(
						MockMvcRequestBuilders.post(API_AUTH_LOGIN)
										.content(objectMapper.writeValueAsString(signupRequest))
										.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk());
	}

	@Test
	public void test_get_token_and_perform_math_operation() throws Exception {
		SignupRequest signupRequest = new SignupRequest("test", "test", Lists.newArrayList(UserRole.USER_ROLE));

		String contentAsString = this.mockMvc.perform(
						MockMvcRequestBuilders.post(API_AUTH_LOGIN)
										.content(objectMapper.writeValueAsString(signupRequest))
										.contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse()
						.getContentAsString();

		JwtResponse response = objectMapper.readValue(contentAsString, JwtResponse.class);

		this.mockMvc.perform(MockMvcRequestBuilders.get(API_MATH).contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", String.format("%s %s", response.getTokenType(), response.getAccessToken())))
						.andExpect(status().isOk());
	}

	@Test
	public void test_logout_success() throws Exception {
		SignupRequest signupRequest = new SignupRequest("test", "test", Lists.newArrayList(UserRole.USER_ROLE));


		String contentAsString = this.mockMvc.perform(
						MockMvcRequestBuilders.post(API_AUTH_LOGIN)
										.content(objectMapper.writeValueAsString(signupRequest))
										.contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse()
						.getContentAsString();

		JwtResponse response = objectMapper.readValue(contentAsString, JwtResponse.class);

		this.mockMvc.perform(MockMvcRequestBuilders.get(API_AUTH_LOGOUT).contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", String.format("%s %s", response.getTokenType(), response.getAccessToken())))
						.andExpect(status().isAccepted());

		this.mockMvc.perform(MockMvcRequestBuilders.get(API_MATH).contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", String.format("%s %s", response.getTokenType(), response.getAccessToken())))
						.andExpect(status().isForbidden());
	}

}