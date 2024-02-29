package org.pyriboo.gis_server.domain.playlist.service;

import java.util.List;

import org.pyriboo.gis_server.domain.playlist.dto.PlaylistReq;
import org.pyriboo.gis_server.domain.playlist.dto.PlaylistRes;

public interface PlaylistService {

	PlaylistRes createPlaylist(PlaylistReq playlistReq);

	List<PlaylistRes> getAllPlaylists();

	PlaylistRes getPlaylistById(String id);

	PlaylistRes updatePlaylist(String id, PlaylistReq playlistReq);

	void addSongToPlaylist(String playlistId, String songId);

	void deletePlaylist(String id);
}
