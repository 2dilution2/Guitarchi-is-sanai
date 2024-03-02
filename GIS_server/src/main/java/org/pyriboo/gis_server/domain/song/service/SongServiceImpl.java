package org.pyriboo.gis_server.domain.song.service;

import java.util.List;
import java.util.stream.Collectors;

import org.pyriboo.gis_server.domain.playlist.dto.AddSongReq;
import org.pyriboo.gis_server.domain.playlist.model.Playlist;
import org.pyriboo.gis_server.domain.playlist.repository.PlaylistRepository;
import org.pyriboo.gis_server.domain.song.dto.SongReq;
import org.pyriboo.gis_server.domain.song.dto.SongRes;
import org.pyriboo.gis_server.domain.song.model.Song;
import org.pyriboo.gis_server.domain.song.repository.SongRepository;
import org.pyriboo.gis_server.global.error.exception.SongException;
import org.pyriboo.gis_server.global.error.type.SongErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

	@Autowired
	private SongRepository songRepository;

	@Autowired
	private PlaylistRepository playlistRepository;

	@Override
	public SongRes createSong(SongReq songReq) {
		try {
			Song song = new Song();
			song.setTitle(songReq.getTitle());
			song.setArtist(songReq.getArtist());
			song.setHashtags(songReq.getHashtags());
			Song savedSong = songRepository.save(song);
			return convertToDto(savedSong);
		} catch (Exception e) {
			throw new SongException(SongErrorType.SONG_CREATION_FAILED);
		}
	}

	@Override
	public List<SongRes> getAllSongs() {
		return songRepository.findAll().stream()
			.map(this::convertToDto)
			.collect(Collectors.toList());
	}

	@Override
	public SongRes getSongById(String id) {
		Song song = songRepository.findById(id)
			.orElseThrow(() -> new SongException(SongErrorType.SONG_NOT_FOUND));
		return convertToDto(song);
	}

	@Override
	public SongRes updateSong(String id, SongReq songReq) {
		Song existingSong = songRepository.findById(id)
			.orElseThrow(() -> new SongException(SongErrorType.SONG_NOT_FOUND));
		try {
			existingSong.setTitle(songReq.getTitle());
			existingSong.setArtist(songReq.getArtist());
			existingSong.setHashtags(songReq.getHashtags());
			Song updatedSong = songRepository.save(existingSong);
			return convertToDto(updatedSong);
		} catch (Exception e) {
			throw new SongException(SongErrorType.SONG_UPDATE_FAILED);
		}
	}

	@Override
	public void addSongToPlaylist(AddSongReq addSongReq) {
		Playlist playlist = playlistRepository.findById(addSongReq.getPlaylistId())
			.orElseThrow(() -> new SongException(SongErrorType.SONG_NOT_FOUND));
		List<Song> songsToAdd = songRepository.findAllById(addSongReq.getSongIds());
		if (songsToAdd.isEmpty()) {
			throw new SongException(SongErrorType.SONG_NOT_FOUND);
		}
		playlist.getSongs().addAll(songsToAdd);
		playlistRepository.save(playlist);
	}

	@Override
	public void deleteSong(String id) {
		if (!songRepository.existsById(id)) {
			throw new SongException(SongErrorType.SONG_DELETION_FAILED);
		}
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
