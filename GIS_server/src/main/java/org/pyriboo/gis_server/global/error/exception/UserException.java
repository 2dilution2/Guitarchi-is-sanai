package org.pyriboo.gis_server.global.error.exception;

import org.pyriboo.gis_server.global.error.type.UserErrorType;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

	private final UserErrorType errorCode;

	public UserException(UserErrorType errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
