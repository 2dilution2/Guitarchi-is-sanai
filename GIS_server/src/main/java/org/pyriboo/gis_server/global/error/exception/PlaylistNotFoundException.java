package org.pyriboo.gis_server.global.error.exception;

import org.pyriboo.gis_server.global.error.type.PlaylistErrorType;

import lombok.Getter;

@Getter
public class PlaylistNotFoundException extends RuntimeException {

	private final PlaylistErrorType errorType;

	public PlaylistNotFoundException(PlaylistErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}
