package org.pyriboo.gis_server.domain.song.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.pyriboo.gis_server.domain.song.dto.SongReq;
import org.pyriboo.gis_server.domain.song.dto.SongRes;
import org.pyriboo.gis_server.domain.song.model.Song;
import org.pyriboo.gis_server.domain.song.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

	@Autowired
	private SongRepository songRepository;

	@Override
	public SongRes createSong(SongReq songReq) {
		Song song = new Song();
		song.setTitle(songReq.getTitle());
		song.setArtist(songReq.getArtist());
		song.setHashtags(songReq.getHashtags());
		Song savedSong = songRepository.save(song);

		return convertToDto(savedSong);
	}

	@Override
	public List<SongRes> getAllSongs() {
		return songRepository.findAll().stream()
			.map(this::convertToDto)
			.collect(Collectors.toList());
	}

	@Override
	public SongRes getSongById(String id) {
		Optional<Song> song = songRepository.findById(id);
		return song.map(this::convertToDto).orElse(null);
	}

	@Override
	public SongRes updateSong(String id, SongReq songReq) {
		Optional<Song> existingSong = songRepository.findById(id);
		if (existingSong.isPresent()) {
			Song song = existingSong.get();
			song.setTitle(songReq.getTitle());
			song.setArtist(songReq.getArtist());
			song.setHashtags(songReq.getHashtags());

			Song updatedSong = songRepository.save(song);
			return convertToDto(updatedSong);
		}
		return null;
	}

	@Override
	public void deleteSong(String id) {
		songRepository.deleteById(id);
	}

	private SongRes convertToDto(Song song) {
		SongRes songRes = new SongRes();
		songRes.setId(song.getId());
		songRes.setTitle(song.getTitle());
		songRes.setArtist(song.getArtist());
		songRes.setHashtags(song.getHashtags());

		return songRes;
	}
}
