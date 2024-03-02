package org.pyriboo.gis_server.domain.users.service;

import java.util.List;
import java.util.Optional;

import org.pyriboo.gis_server.domain.users.model.Users;
import org.pyriboo.gis_server.domain.users.repository.UserRepository;
import org.pyriboo.gis_server.global.error.exception.UserException;
import org.pyriboo.gis_server.global.error.type.UserErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Users saveUser(Users user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return userRepository.save(user);
		} catch (Exception e) {
			// 회원가입 과정에서 예외가 발생한 경우
			throw new UserException(UserErrorType.USER_AUTHENTICATION_FAILED);
		}
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
		if (!userRepository.existsById(email)) {
			// 삭제하려는 사용자가 존재하지 않는 경우
			throw new UserException(UserErrorType.USER_NOT_FOUND);
		}
		userRepository.deleteById(email);
	}
}
