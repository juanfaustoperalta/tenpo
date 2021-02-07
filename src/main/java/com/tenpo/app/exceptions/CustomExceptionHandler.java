package com.tenpo.app.exceptions;

import com.tenpo.app.dtos.responses.MessageErrorResponse;
import com.tenpo.app.dtos.responses.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import springfox.documentation.annotations.ApiIgnore;


@ControllerAdvice
@ApiIgnore
public class CustomExceptionHandler
				extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserHasAlreadyExistException.class)
	public ResponseEntity<MessageResponse> handleUserAlreadyExistException(UserHasAlreadyExistException ex,
					WebRequest request) {
		return new ResponseEntity(getMessageErrorResponse(ex, request, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<MessageResponse> handleRoleNotFoundException(RoleNotFoundException ex, WebRequest request) {
		return new ResponseEntity(getMessageErrorResponse(ex, request, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<MessageResponse> handleInvalidTokenException(InvalidTokenException ex, WebRequest request) {
		return new ResponseEntity(getMessageErrorResponse(ex, request, HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
	}

	private MessageErrorResponse getMessageErrorResponse(Exception ex, WebRequest request, HttpStatus httpStatus) {
		return new MessageErrorResponse(httpStatus.ordinal(), ex.getClass().getName(), ex.getMessage(),
						request.getContextPath());
	}

}
