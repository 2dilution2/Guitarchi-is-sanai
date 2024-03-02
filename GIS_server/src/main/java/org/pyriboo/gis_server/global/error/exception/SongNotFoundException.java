package org.pyriboo.gis_server.global.error.exception;

import org.pyriboo.gis_server.global.error.type.SongErrorType;

import lombok.Getter;

@Getter
public class SongNotFoundException extends RuntimeException {

	private final SongErrorType errorType;

	public SongNotFoundException(SongErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}
