package org.pyriboo.gis_server.domain.song.controller;

import java.util.List;

import org.pyriboo.gis_server.domain.song.dto.SongReq;
import org.pyriboo.gis_server.domain.song.dto.SongRes;
import org.pyriboo.gis_server.domain.song.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/songs")
public class SongController {

	@Autowired
	private SongService songService;

	@PostMapping
	public ResponseEntity<SongRes> createSong(@RequestBody SongReq songReq) {
		return ResponseEntity.ok(songService.createSong(songReq));
	}

	@GetMapping
	public ResponseEntity<List<SongRes>> getAllSongs() {
		return ResponseEntity.ok(songService.getAllSongs());
	}

	@GetMapping("/{id}")
	public ResponseEntity<SongRes> getSongById(@PathVariable String id) {
		return ResponseEntity.ok(songService.getSongById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<SongRes> updateSong(@PathVariable String id, @RequestBody SongReq songReq) {
		return ResponseEntity.ok(songService.updateSong(id, songReq));
	}

	@DeleteMapping("/{id}")
	public void deleteSong(@PathVariable String id) {
		songService.deleteSong(id);
		ResponseEntity.ok();
	}
}
