package org.pyriboo.gis_server.domain.users.service;

import java.util.List;

import org.pyriboo.gis_server.domain.users.dto.UserReq;
import org.pyriboo.gis_server.domain.users.dto.UserRes;

import org.pyriboo.gis_server.domain.users.model.Users;
import java.util.List;
import java.util.Optional;

public interface UserService {

	Users saveUser(Users user);

	Optional<Users> findByEmail(String email);

	List<Users> findAllUsers();

	void deleteUser(String email);
}