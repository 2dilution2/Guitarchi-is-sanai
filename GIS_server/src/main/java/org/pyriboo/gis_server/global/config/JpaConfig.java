package org.pyriboo.gis_server.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "org.pyriboo.gis_server.domain.users.repository")
public class JpaConfig {
}
