package org.pyriboo.gis_server.domain.song.controller;

import java.util.List;
import org.pyriboo.gis_server.domain.playlist.dto.AddSongReq;
import org.pyriboo.gis_server.domain.song.dto.SongReq;
import org.pyriboo.gis_server.domain.song.dto.SongRes;
import org.pyriboo.gis_server.domain.song.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/songs")
public class SongController {

	private final SongService songService;

	@Autowired
	public SongController(SongService songService) {
		this.songService = songService;
	}

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

	@PostMapping("/addToPlaylist")
	public ResponseEntity<Void> addSongToPlaylist(@RequestBody AddSongReq addSongReq) {
		songService.addSongToPlaylist(addSongReq);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSong(@PathVariable String id) {
		songService.deleteSong(id);
		return ResponseEntity.ok().build();
	}
}
