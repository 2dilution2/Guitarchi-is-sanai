package org.pyriboo.gis_server.global.error.exception;

import org.pyriboo.gis_server.global.error.type.PlaylistErrorType;

import lombok.Getter;

@Getter
public class PlaylistException extends RuntimeException {

	private final PlaylistErrorType errorType;

	public PlaylistException(PlaylistErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}

}
