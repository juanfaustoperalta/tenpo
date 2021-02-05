package com.tenpo.app.tenpo.exceptions;

public class UserHasAlreadyExistException
				extends RuntimeException {

	public UserHasAlreadyExistException(String message) {
		super(message);
	}
}
