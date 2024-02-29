package org.pyriboo.gis_server.domain.playlist.repository;

import org.pyriboo.gis_server.domain.playlist.model.Playlist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaylistRepository extends MongoRepository<Playlist, String> {
}
