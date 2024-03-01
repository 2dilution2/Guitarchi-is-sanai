package org.pyriboo.gis_server.domain.playlist.controller;

import java.util.List;
import org.pyriboo.gis_server.domain.playlist.dto.AddSongReq;
import org.pyriboo.gis_server.domain.playlist.dto.PlaylistReq;
import org.pyriboo.gis_server.domain.playlist.dto.PlaylistRes;
import org.pyriboo.gis_server.domain.playlist.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

	private final PlaylistService playlistService;

	@Autowired
	public PlaylistController(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}

	// 플레이리스트 생성
	@PostMapping
	public ResponseEntity<PlaylistRes> createPlaylist(@RequestBody PlaylistReq playlistReq) {
		PlaylistRes playlistRes = playlistService.createPlaylist(playlistReq);
		return ResponseEntity.ok(playlistRes);
	}

	// 플레이리스트에 노래 추가
	@PostMapping("/add-songs")
	public ResponseEntity<?> addSongsToPlaylist(@RequestBody AddSongReq addSongReq) {
		playlistService.addSongs(addSongReq);
		return ResponseEntity.ok().build();
	}

	// 모든 플레이리스트 조회
	@GetMapping
	public ResponseEntity<List<PlaylistRes>> getAllPlaylists() {
		List<PlaylistRes> playlists = playlistService.getAllPlaylists();
		return ResponseEntity.ok(playlists);
	}

	// 특정 플레이리스트 조회
	@GetMapping("/{id}")
	public ResponseEntity<PlaylistRes> getPlaylistById(@PathVariable String id) {
		PlaylistRes playlistRes = playlistService.getPlaylistById(id);
		return ResponseEntity.ok(playlistRes);
	}

	// 플레이리스트 업데이트
	@PutMapping("/{id}")
	public ResponseEntity<PlaylistRes> updatePlaylist(@PathVariable String id, @RequestBody PlaylistReq playlistReq) {
		PlaylistRes updatedPlaylist = playlistService.updatePlaylist(id, playlistReq);
		return ResponseEntity.ok(updatedPlaylist);
	}

	// 플레이리스트 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePlaylist(@PathVariable String id) {
		playlistService.deletePlaylist(id);
		return ResponseEntity.ok().build();
	}
}
