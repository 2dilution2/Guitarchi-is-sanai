package org.pyriboo.gis_server.domain.playlist.model;

import java.util.List;

import org.pyriboo.gis_server.domain.song.model.Song;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "playlists")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Playlist {

	@Id
	private String id;

	private String userId;

	private String title;

	@DBRef
	private List<Song> songs;

}
