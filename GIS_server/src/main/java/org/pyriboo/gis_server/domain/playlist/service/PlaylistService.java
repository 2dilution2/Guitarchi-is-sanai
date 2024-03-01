package org.pyriboo.gis_server.domain.playlist.service;

import java.util.List;

import org.pyriboo.gis_server.domain.playlist.dto.AddSongReq;
import org.pyriboo.gis_server.domain.playlist.dto.PlaylistReq;
import org.pyriboo.gis_server.domain.playlist.dto.PlaylistRes;

public interface PlaylistService {

	PlaylistRes createPlaylist(PlaylistReq playlistReq);

	List<PlaylistRes> getAllPlaylists();

	PlaylistRes getPlaylistById(String id);

	PlaylistRes updatePlaylist(String id, PlaylistReq playlistReq);

	void addSongs(AddSongReq addSongReq);

	void deletePlaylist(String id);
}
