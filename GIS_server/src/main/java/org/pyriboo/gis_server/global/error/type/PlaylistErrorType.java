package org.pyriboo.gis_server.global.error.type;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 플레이리스트 관련 에러 코드 = P001 ~ P099
 */
@Getter
@AllArgsConstructor
public enum PlaylistErrorType {
	PLAYLIST_NOT_FOUND(HttpStatus.NOT_FOUND, "P001", "플레이리스트를 찾을 수 없습니다."),
	PLAYLIST_CREATION_FAILED(HttpStatus.BAD_REQUEST, "P002", "플레이리스트 생성에 실패하였습니다."),
	PLAYLIST_UPDATE_FAILED(HttpStatus.BAD_REQUEST, "P003", "플레이리스트 업데이트에 실패하였습니다."),
	PLAYLIST_DELETION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "P004", "플레이리스트 삭제에 실패하였습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}

