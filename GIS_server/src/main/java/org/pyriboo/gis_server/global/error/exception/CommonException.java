package org.pyriboo.gis_server.global.error.exception;

import org.pyriboo.gis_server.global.error.type.CommonErrorType;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {

	private final CommonErrorType errorCode;

	public CommonException(CommonErrorType errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}