package org.pyriboo.gis_server.domain.users.model;

import org.pyriboo.gis_server.global.entity.BaseTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users extends BaseTime {

	@Id
	private String email;

	private String password;

	private String name;

	private String phone;

	private String nickname;

	private UserType userType;
}
