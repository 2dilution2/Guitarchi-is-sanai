package org.pyriboo.gis_server.global.error.type;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 회원 에러 코드 = U001 ~ U099
 */

@Getter
@AllArgsConstructor
public enum UserErrorType {

	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "사용자를 찾을 수 없습니다."),
	USER_AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "U002", "회원가입에 실패하였습니다."),
	INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "U003", "비밀번호가 일치하지 않습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;

}
