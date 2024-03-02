package org.pyriboo.gis_server.global.error.type;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 노래 에러 코드 = S001 ~ S099
 */

@Getter
@AllArgsConstructor
public enum SongErrorType {
	SONG_NOT_FOUND(HttpStatus.NOT_FOUND, "S001", "노래를 찾을 수 없습니다."),
	SONG_CREATION_FAILED(HttpStatus.BAD_REQUEST, "S002", "노래 생성에 실패하였습니다."),
	SONG_UPDATE_FAILED(HttpStatus.BAD_REQUEST, "S003", "노래 업데이트에 실패하였습니다."),
	SONG_DELETION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "S004", "노래 삭제에 실패하였습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
