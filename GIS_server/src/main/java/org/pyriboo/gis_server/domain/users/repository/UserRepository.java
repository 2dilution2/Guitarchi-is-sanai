package org.pyriboo.gis_server.domain.users.repository;

import org.pyriboo.gis_server.domain.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
}
