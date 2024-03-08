package org.pyriboo.gis_server.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {
	"org.pyriboo.gis_server.domain.song.repository",
	"org.pyriboo.gis_server.domain.playlist.repository"
})
public class MongoConfig {
}
