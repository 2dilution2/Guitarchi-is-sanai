package org.pyriboo.gis_server.song;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.pyriboo.gis_server.domain.song.model.Song;
import org.pyriboo.gis_server.domain.song.repository.SongRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataMongoTest
public class SongRepositoryTest {

	@Autowired
	private SongRepository songRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	public void testCreateAndFindSong() {
		Song song = new Song(null, "Sample Song", "Sample Artist", List.of("#rock"), null);
		song = songRepository.save(song);

		Song foundSong = mongoTemplate.findById(song.getId(), Song.class);

		assertThat(foundSong).isNotNull();
		assertThat(foundSong.getTitle()).isEqualTo("Sample Song");
		assertThat(foundSong.getArtist()).isEqualTo("Sample Artist");
	}
}
