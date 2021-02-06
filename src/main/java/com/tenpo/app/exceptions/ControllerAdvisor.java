package com.tenpo.app.exceptions;

import com.tenpo.app.dtos.responses.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.ConstraintViolationException;


@ControllerAdvice
@ApiIgnore
public class ControllerAdvisor
				extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserHasAlreadyExistException.class)
	public ResponseEntity<MessageResponse> handleUserAlreadyExistException(UserHasAlreadyExistException ex,
					WebRequest request) {
		return new ResponseEntity(MessageResponse.whitMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<MessageResponse> handleAuthenticationException(AuthenticationException ex,
					WebRequest request) {

		return new ResponseEntity(MessageResponse.whitMessage(ex.getMessage()), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<MessageResponse> handleRoleNotFoundException(RoleNotFoundException ex, WebRequest request) {
		return new ResponseEntity(MessageResponse.whitMessage("Role not found"), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<Object> handleConstraintViolation(
					ConstraintViolationException ex, WebRequest request) {
		return new ResponseEntity(MessageResponse.whitMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

}
