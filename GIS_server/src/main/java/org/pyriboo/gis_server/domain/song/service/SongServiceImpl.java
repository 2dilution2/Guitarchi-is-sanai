package org.pyriboo.gis_server.domain.song.service;

import java.util.List;
import java.util.stream.Collectors;

import org.pyriboo.gis_server.domain.song.dto.SongReq;
import org.pyriboo.gis_server.domain.song.dto.SongRes;
import org.pyriboo.gis_server.domain.song.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

	@Autowired
	private SongRepository songRepository;

	@Override
	public SongRes createSong(SongReq songReq) {

		return new SongRes();
	}

	@Override
	public List<SongRes> getAllSongs() {

		return songRepository.findAll().stream().map(song -> new SongRes()).collect(Collectors.toList());
	}

	@Override
	public SongRes getSongById(String id) {

		return new SongRes();
	}

	@Override
	public SongRes updateSong(String id, SongReq songReq) {

		return new SongRes();
	}

	@Override
	public void deleteSong(String id) {

	}
}
