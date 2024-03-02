package org.pyriboo.gis_server.global.error.type;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 공통 에러 코드 = E001 ~ E099
 */

@Getter
@AllArgsConstructor
public enum CommonErrorType {
	INVALID_ARGUMENT(HttpStatus.BAD_REQUEST, "E001", "유효성 검증 실패"),
	FAIL_TO_AUTHENTICATION(HttpStatus.UNAUTHORIZED, "E002", "사용자 인증에 실패하였습니다."),
	FAIL_TO_AUTHORIZATION(HttpStatus.FORBIDDEN, "E003", "사용자 접근 권한이 없습니다."),
	NULL_POINTER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "E004", "Null pointer exception occurred"),
	ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "E005", "Requested resource was not found");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
