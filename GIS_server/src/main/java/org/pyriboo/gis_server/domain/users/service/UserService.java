package org.pyriboo.gis_server.domain.users.service;

import java.util.List;
import java.util.Optional;

import org.pyriboo.gis_server.domain.users.model.Users;

public interface UserService {

	Users saveUser(Users user);

	Optional<Users> findByEmail(String email);

	List<Users> findAllUsers();

	void deleteUser(String email);
}