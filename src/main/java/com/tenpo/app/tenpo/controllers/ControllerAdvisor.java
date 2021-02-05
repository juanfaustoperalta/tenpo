package com.tenpo.app.tenpo.controllers;

import com.tenpo.app.tenpo.dtos.responses.MessageResponse;
import com.tenpo.app.tenpo.exceptions.UserHasAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControllerAdvisor
				extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserHasAlreadyExistException.class)
	public ResponseEntity<MessageResponse> handleUserHasReallyExistException(UserHasAlreadyExistException ex,
					WebRequest request) {
		return new ResponseEntity(MessageResponse.whitMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<MessageResponse> handleAuthenticationException(AuthenticationException ex,
					WebRequest request) {

		return new ResponseEntity(MessageResponse.whitMessage(ex.getMessage()), HttpStatus.UNAUTHORIZED);
	}

}
