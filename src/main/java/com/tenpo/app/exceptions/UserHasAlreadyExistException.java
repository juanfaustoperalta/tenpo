package com.tenpo.app.exceptions;

public class UserHasAlreadyExistException
				extends RuntimeException {

	public UserHasAlreadyExistException(String message) {
		super(message);
	}
}
