package org.pyriboo.gis_server.domain.song.service;

import java.util.List;

import org.pyriboo.gis_server.domain.song.dto.SongReq;
import org.pyriboo.gis_server.domain.song.dto.SongRes;

public interface SongService {

	SongRes createSong(SongReq songReq);

	List<SongRes> getAllSongs();

	SongRes getSongById(String id);

	SongRes updateSong(String id, SongReq songReq);

	void deleteSong(String id);

}
