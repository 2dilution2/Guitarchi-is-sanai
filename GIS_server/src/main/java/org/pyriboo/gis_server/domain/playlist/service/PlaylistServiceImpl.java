package org.pyriboo.gis_server.domain.playlist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.pyriboo.gis_server.domain.playlist.dto.AddSongReq;
import org.pyriboo.gis_server.domain.playlist.dto.PlaylistReq;
import org.pyriboo.gis_server.domain.playlist.dto.PlaylistRes;
import org.pyriboo.gis_server.domain.playlist.model.Playlist;
import org.pyriboo.gis_server.domain.playlist.repository.PlaylistRepository;
import org.pyriboo.gis_server.domain.song.model.Song;
import org.pyriboo.gis_server.domain.song.repository.SongRepository;
import org.pyriboo.gis_server.global.error.exception.PlaylistException;
import org.pyriboo.gis_server.global.error.exception.SongException;
import org.pyriboo.gis_server.global.error.type.PlaylistErrorType;
import org.pyriboo.gis_server.global.error.type.SongErrorType;
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
		// songIds로 노래 조회
		List<Song> songs = songRepository.findAllById(playlistReq.getSongIds());
		if (songs.size() != playlistReq.getSongIds().size()) {
			// 제공된 모든 노래 ID가 유효하지 않은 경우
			throw new PlaylistException(PlaylistErrorType.PLAYLIST_CREATION_FAILED);
		}

		// 모든 노래가 존재하면 플레이리스트 생성
		Playlist playlist = new Playlist();
		playlist.setUserId(playlistReq.getUserId());
		playlist.setTitle(playlistReq.getTitle());
		playlist.setSongs(songs);

		// 플레이리스트 저장 및 DTO 변환하여 반환
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
		Playlist playlist = playlistRepository.findById(id)
			.orElseThrow(() -> new PlaylistException(PlaylistErrorType.PLAYLIST_NOT_FOUND));
		return convertToDto(playlist);
	}

	@Override
	public PlaylistRes updatePlaylist(String id, PlaylistReq playlistReq) {
		Playlist existingPlaylist = playlistRepository.findById(id)
			.orElseThrow(() -> new PlaylistException(PlaylistErrorType.PLAYLIST_NOT_FOUND));

		try {
			List<Song> songs = songRepository.findAllById(playlistReq.getSongIds());
			// 제공된 노래 ID 중 유효하지 않은 ID가 있는 경우
			if (songs.size() != playlistReq.getSongIds().size()) {
				throw new PlaylistException(PlaylistErrorType.PLAYLIST_UPDATE_FAILED);
			}

			existingPlaylist.setTitle(playlistReq.getTitle());
			existingPlaylist.setSongs(songs);
			Playlist updatedPlaylist = playlistRepository.save(existingPlaylist);
			return convertToDto(updatedPlaylist);
		} catch (Exception e) {
			// 업데이트 과정에서 예외가 발생한 경우
			throw new PlaylistException(PlaylistErrorType.PLAYLIST_UPDATE_FAILED);
		}
	}

	@Override
	public void addSongs(AddSongReq addSongReq) {
		Playlist playlist = playlistRepository.findById(addSongReq.getPlaylistId())
			.orElseThrow(() -> new PlaylistException(PlaylistErrorType.PLAYLIST_NOT_FOUND));

		List<Song> songsToAdd = songRepository.findAllById(addSongReq.getSongIds());
		if (songsToAdd.size() != addSongReq.getSongIds().size()) {
			// 제공된 모든 노래 ID가 유효하지 않은 경우
			throw new SongException(SongErrorType.SONG_NOT_FOUND);
		}

		playlist.getSongs().addAll(songsToAdd);
		playlistRepository.save(playlist);
	}

	@Override
	public void deletePlaylist(String id) {
		if (!playlistRepository.existsById(id)) {
			throw new PlaylistException(PlaylistErrorType.PLAYLIST_DELETION_FAILED);
		}
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
