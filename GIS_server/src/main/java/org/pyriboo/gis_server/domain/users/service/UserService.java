package org.pyriboo.gis_server.domain.users.service;

import java.util.List;
import java.util.Optional;

import org.pyriboo.gis_server.domain.users.model.Users;
import org.pyriboo.gis_server.global.security.jwt.TokenResponse;

public interface UserService {

	Users signup(Users user);

	TokenResponse loginUser(String email, String password);

	Optional<Users> findByEmail(String email);

	List<Users> findAllUsers();

	void deleteUser(String email);
}