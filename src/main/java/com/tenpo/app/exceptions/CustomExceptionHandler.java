package com.tenpo.app.exceptions;

import com.google.common.collect.Lists;
import com.tenpo.app.dtos.responses.MessageErrorResponse;
import com.tenpo.app.dtos.responses.MessageResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class CustomExceptionHandler
				extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserHasAlreadyExistException.class)
	public ResponseEntity<MessageResponse> handleUserAlreadyExistException(UserHasAlreadyExistException ex,
					WebRequest request) {
		return new ResponseEntity(getMessageErrorResponse(request, getErrorsDetails(ex), HttpStatus.BAD_REQUEST),
						HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<MessageResponse> handleRoleNotFoundException(RoleNotFoundException ex, WebRequest request) {
		return new ResponseEntity(getMessageErrorResponse(request, getErrorsDetails(ex), HttpStatus.BAD_REQUEST),
						HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<MessageResponse> handleInvalidTokenException(InvalidTokenException ex, WebRequest request) {
		return new ResponseEntity(getMessageErrorResponse(request, getErrorsDetails(ex), HttpStatus.FORBIDDEN),
						HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<Object> handleConstraintViolation(
					ConstraintViolationException ex, WebRequest request) {
		return new ResponseEntity(getMessageErrorResponse(request, getErrorsDetails(ex), HttpStatus.BAD_REQUEST),
						HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
					HttpStatus status, WebRequest request) {
		Map<String, List<String>> errorDetails = ex.getBindingResult().getAllErrors().stream()
						.collect(Collectors.groupingBy(ObjectError::getObjectName,
										Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage,
														Collectors.toList())));
		return new ResponseEntity(getMessageErrorResponse(request, errorDetails, HttpStatus.BAD_REQUEST),
						HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
					MissingServletRequestParameterException ex, HttpHeaders headers,
					HttpStatus status, WebRequest request) {
		return new ResponseEntity(getMessageErrorResponse(request, getErrorsDetails(ex), HttpStatus.INTERNAL_SERVER_ERROR),
						HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private Map<String, List<String>> getErrorsDetails(Exception ex) {
		return new HashMap() {{
			put(ex.getClass().getName(), Lists.newArrayList(ex.getMessage()));
		}};
	}

	private MessageErrorResponse getMessageErrorResponse(WebRequest request, Map<String, List<String>> errorDetails,
					HttpStatus httpStatus) {
		return new MessageErrorResponse(httpStatus.ordinal(), errorDetails, request.getContextPath());
	}

}
