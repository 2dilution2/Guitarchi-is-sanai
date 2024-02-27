package org.pyriboo.gis_server.domain.users.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	@Id
	private String email;

	private String password;

	private String name;

	private String phone;

	private String nickname;

	private UserType userType;
}
