package org.pyriboo.gis_server.domain.users.service;

import org.pyriboo.gis_server.domain.users.model.Users;
import org.pyriboo.gis_server.domain.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Users saveUser(Users user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<Users> findByEmail(String email) {
		return userRepository.findById(email);
	}

	@Override
	public List<Users> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(String email) {
		userRepository.deleteById(email);
	}
}
