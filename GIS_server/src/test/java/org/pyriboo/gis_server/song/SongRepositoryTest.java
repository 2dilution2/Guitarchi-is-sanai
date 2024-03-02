package org.pyriboo.gis_server.song;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
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
public class SongRepositoryTest {

	@Autowired
	private SongRepository songRepository;

	@Autowired
	private PlaylistRepository playlistRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@AfterEach
	void tearDown() {
		songRepository.deleteAll();
	}

	@Test
	public void testCreateAndFindSong() {
		//given
		Song song = new Song(null, "Sample Song", "Sample Artist", List.of("#rock"), null);
		song = songRepository.save(song);

		//when
		Song foundSong = mongoTemplate.findById(song.getId(), Song.class);

		//then
		assertThat(foundSong).isNotNull();
		assertThat(foundSong.getTitle()).isEqualTo("Sample Song");
		assertThat(foundSong.getArtist()).isEqualTo("Sample Artist");
	}

	@Test
	public void testAddSongToPlaylist() {
		// Given
		Song song1 = new Song(null, "Song 1", "Artist 1", List.of("#rock"), null);
		Song song2 = new Song(null, "Song 2", "Artist 2", List.of("#pop"), null);
		song1 = songRepository.save(song1);
		song2 = songRepository.save(song2);

		Playlist playlist = new Playlist(null, "User1", "My Playlist", new ArrayList<>(Arrays.asList(song1, song2)));
		playlist = playlistRepository.save(playlist);

		// When
		Playlist foundPlaylist = playlistRepository.findById(playlist.getId()).orElse(null);

		// Then
		assertThat(foundPlaylist).isNotNull();
		assertThat(foundPlaylist.getSongs()).hasSize(2);
		assertThat(foundPlaylist.getSongs()).extracting("title").containsExactlyInAnyOrder("Song 1", "Song 2");
	}
}
