package org.pyriboo.gis_server.playlist;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.pyriboo.gis_server.domain.playlist.model.Playlist;
import org.pyriboo.gis_server.domain.playlist.repository.PlaylistRepository;
import org.pyriboo.gis_server.domain.song.model.Song;
import org.pyriboo.gis_server.domain.song.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@DataMongoTest
public class PlaylistRepositoryTest {

	@Autowired
	private PlaylistRepository playlistRepository;

	@Autowired
	private SongRepository songRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@AfterEach
	void tearDown() {
		playlistRepository.deleteAll();
		songRepository.deleteAll();
	}

	@Test
	public void testCreateAndFindPlaylist() {
		// Given
		Song song1 = new Song(null, "Song 1", "Artist 1", List.of("#rock"), null);
		Song song2 = new Song(null, "Song 2", "Artist 2", List.of("#pop"), null);
		songRepository.saveAll(Arrays.asList(song1, song2));

		Playlist playlist = new Playlist(null, "Test User", "My Playlist", Arrays.asList(song1, song2));
		playlistRepository.save(playlist);

		// When
		Playlist foundPlaylist = mongoTemplate.findById(playlist.getId(), Playlist.class);

		// Then
		assertThat(foundPlaylist).isNotNull();
		assertThat(foundPlaylist.getTitle()).isEqualTo("My Playlist");
		assertThat(foundPlaylist.getUserId()).isEqualTo("Test User");
		assertThat(foundPlaylist.getSongs()).hasSize(2).extracting("title").contains("Song 1", "Song 2");
	}
}