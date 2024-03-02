package org.pyriboo.gis_server.global.error.handler;

import org.pyriboo.gis_server.global.error.exception.CommonException;
import org.pyriboo.gis_server.global.error.exception.PlaylistException;
import org.pyriboo.gis_server.global.error.exception.SongException;
import org.pyriboo.gis_server.global.error.exception.UserException;
import org.pyriboo.gis_server.global.error.model.ErrorResponse;
import org.pyriboo.gis_server.global.error.type.CommonErrorType;
import org.pyriboo.gis_server.global.error.type.PlaylistErrorType;
import org.pyriboo.gis_server.global.error.type.SongErrorType;
import org.pyriboo.gis_server.global.error.type.UserErrorType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CommonException.class)
	public ResponseEntity<ErrorResponse> handleCommonException(CommonException ex) {
		CommonErrorType errorType = ex.getErrorCode();

		ErrorResponse errorResponse = ErrorResponse.builder()
			.status(errorType.getStatus().value())
			.code(errorType.getCode())
			.message(errorType.getMessage())
			.build();

		return new ResponseEntity<>(errorResponse, errorType.getStatus());
	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorResponse> handleUserException(UserException ex) {
		UserErrorType errorType = ex.getErrorCode();

		ErrorResponse errorResponse = ErrorResponse.builder()
			.status(errorType.getStatus().value())
			.code(errorType.getCode())
			.message(errorType.getMessage())
			.build();

		return new ResponseEntity<>(errorResponse, errorType.getStatus());
	}

	@ExceptionHandler(SongException.class)
	public ResponseEntity<ErrorResponse> handleSongException(SongException ex) {
		SongErrorType errorType = ex.getErrorType();

		ErrorResponse errorResponse = ErrorResponse.builder()
			.status(errorType.getStatus().value())
			.code(errorType.getCode())
			.message(errorType.getMessage())
			.build();

		return new ResponseEntity<>(errorResponse, errorType.getStatus());
	}

	@ExceptionHandler(PlaylistException.class)
	public ResponseEntity<ErrorResponse> handlePlaylistException(PlaylistException ex) {
		PlaylistErrorType errorType = ex.getErrorType();

		ErrorResponse errorResponse = ErrorResponse.builder()
			.status(errorType.getStatus().value())
			.code(errorType.getCode())
			.message(errorType.getMessage())
			.build();

		return new ResponseEntity<>(errorResponse, errorType.getStatus());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
		CommonErrorType errorType = CommonErrorType.INVALID_ARGUMENT;

		ErrorResponse errorResponse = buildErrorResponse(errorType);

		return new ResponseEntity<>(errorResponse, errorType.getStatus());
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException ex) {
		CommonErrorType errorType = CommonErrorType.NULL_POINTER_EXCEPTION;

		ErrorResponse errorResponse = buildErrorResponse(errorType);

		return new ResponseEntity<>(errorResponse, errorType.getStatus());
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
		CommonErrorType errorType = CommonErrorType.ENTITY_NOT_FOUND;

		ErrorResponse errorResponse = buildErrorResponse(errorType);

		return new ResponseEntity<>(errorResponse, errorType.getStatus());
	}

	private ErrorResponse buildErrorResponse(CommonErrorType errorType) {
		return ErrorResponse.builder()
			.status(errorType.getStatus().value())
			.code(errorType.getCode())
			.message(errorType.getMessage())
			.build();
	}

}
