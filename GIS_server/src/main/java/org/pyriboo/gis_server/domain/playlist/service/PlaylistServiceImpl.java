package org.pyriboo.gis_server.domain.playlist.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.pyriboo.gis_server.domain.playlist.dto.AddSongReq;
import org.pyriboo.gis_server.domain.playlist.dto.PlaylistReq;
import org.pyriboo.gis_server.domain.playlist.dto.PlaylistRes;
import org.pyriboo.gis_server.domain.playlist.model.Playlist;
import org.pyriboo.gis_server.domain.playlist.repository.PlaylistRepository;
import org.pyriboo.gis_server.domain.song.model.Song;
import org.pyriboo.gis_server.domain.song.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistServiceImpl implements PlaylistService {

	private final PlaylistRepository playlistRepository;
	private final SongRepository songRepository;

	@Autowired
	public PlaylistServiceImpl(PlaylistRepository playlistRepository, SongRepository songRepository) {
		this.playlistRepository = playlistRepository;
		this.songRepository = songRepository;
	}

	@Override
	public PlaylistRes createPlaylist(PlaylistReq playlistReq) {
		List<Song> songs = songRepository.findAllById(playlistReq.getSongIds());
		Playlist playlist = new Playlist();
		playlist.setUserId(playlistReq.getUserId());
		playlist.setTitle(playlistReq.getTitle());
		playlist.setSongs(songs);
		Playlist savedPlaylist = playlistRepository.save(playlist);
		return convertToDto(savedPlaylist);
	}

	@Override
	public List<PlaylistRes> getAllPlaylists() {
		return playlistRepository.findAll().stream()
			.map(this::convertToDto)
			.collect(Collectors.toList());
	}

	@Override
	public PlaylistRes getPlaylistById(String id) {
		Optional<Playlist> playlist = playlistRepository.findById(id);
		return playlist.map(this::convertToDto).orElse(null);
	}

	@Override
	public PlaylistRes updatePlaylist(String id, PlaylistReq playlistReq) {
		Optional<Playlist> existingPlaylist = playlistRepository.findById(id);
		if (existingPlaylist.isPresent()) {
			Playlist playlist = existingPlaylist.get();
			playlist.setTitle(playlistReq.getTitle());
			List<Song> songs = songRepository.findAllById(playlistReq.getSongIds());
			playlist.setSongs(songs);
			Playlist updatedPlaylist = playlistRepository.save(playlist);
			return convertToDto(updatedPlaylist);
		}
		return null;
	}

	@Override
	public void addSongs(AddSongReq addSongReq) {
		Playlist playlist = playlistRepository.findById(addSongReq.getPlaylistId())
			.orElseThrow(() -> new RuntimeException("Playlist not found"));
		List<Song> songsToAdd = songRepository.findAllById(addSongReq.getSongIds());
		playlist.getSongs().addAll(songsToAdd);
		playlistRepository.save(playlist);
	}

	@Override
	public void deletePlaylist(String id) {
		playlistRepository.deleteById(id);
	}

	private PlaylistRes convertToDto(Playlist playlist) {
		PlaylistRes playlistRes = new PlaylistRes();
		playlistRes.setId(playlist.getId());
		playlistRes.setUserId(playlist.getUserId());
		playlistRes.setTitle(playlist.getTitle());
		List<String> songIds = playlist.getSongs().stream()
			.map(Song::getId)
			.collect(Collectors.toList());
		playlistRes.setSongIds(songIds);
		return playlistRes;
	}
}
