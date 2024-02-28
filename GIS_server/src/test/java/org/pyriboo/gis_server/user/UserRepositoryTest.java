package org.pyriboo.gis_server.user;

import org.junit.jupiter.api.Test;
import org.pyriboo.gis_server.domain.users.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.pyriboo.gis_server.domain.users.model.Users;
import org.pyriboo.gis_server.domain.users.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testCreateAndFindUserByEmail() {
		// Users 엔티티 생성 및 저장
		Users newUser = new Users("user@example.com", "securePassword", "User Name", "010-0000-0000", "userNickname", UserType.DEFAULT);
		entityManager.persistAndFlush(newUser);

		// 저장된 Users 엔티티 조회
		Users foundUser = userRepository.findById(newUser.getEmail()).orElse(null);

		// 검증
		assertThat(foundUser).isNotNull();
		assertThat(foundUser.getEmail()).isEqualTo("user@example.com");
		assertThat(foundUser.getPassword()).isEqualTo("securePassword");
		assertThat(foundUser.getName()).isEqualTo("User Name");
		assertThat(foundUser.getPhone()).isEqualTo("010-0000-0000");
		assertThat(foundUser.getNickname()).isEqualTo("userNickname");
		assertThat(foundUser.getUserType()).isEqualTo(UserType.DEFAULT);
	}
}
