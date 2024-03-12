package org.pyriboo.gis_server.domain.users.service;

import java.util.List;
import java.util.Optional;

import org.pyriboo.gis_server.domain.users.model.Role;
import org.pyriboo.gis_server.domain.users.model.Users;
import org.pyriboo.gis_server.domain.users.repository.UserRepository;
import org.pyriboo.gis_server.global.error.exception.UserException;
import org.pyriboo.gis_server.global.error.type.UserErrorType;
import org.pyriboo.gis_server.global.security.jwt.JwtProvider;
import org.pyriboo.gis_server.global.security.jwt.TokenResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;

	@Override
	public Users signup(Users user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRole(Role.DEFAULT);
			return userRepository.save(user);
		} catch (Exception e) {
			throw new UserException(UserErrorType.USER_AUTHENTICATION_FAILED);
		}
	}

	@Override
	public TokenResponse loginUser(String email, String password) {
		Users user = userRepository.findById(email)
			.orElseThrow(() -> new UserException(UserErrorType.USER_NOT_FOUND));

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new UserException(UserErrorType.INVALID_PASSWORD);
		}

		String accessToken = jwtProvider.createAccessToken(email, user.getRole());
		String refreshToken = jwtProvider.createRefreshToken(email, user.getRole());

		TokenResponse tokenResponse = new TokenResponse();
		tokenResponse.setAccessToken(accessToken);
		tokenResponse.setRefreshToken(refreshToken);

		return tokenResponse;
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
