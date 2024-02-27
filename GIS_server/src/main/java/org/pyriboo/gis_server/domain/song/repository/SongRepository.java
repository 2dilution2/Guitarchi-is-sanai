package org.pyriboo.gis_server.domain.song.repository;

import org.pyriboo.gis_server.domain.song.model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<Song, String>{
}
