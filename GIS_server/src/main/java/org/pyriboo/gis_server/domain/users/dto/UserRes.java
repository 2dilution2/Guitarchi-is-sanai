package org.pyriboo.gis_server.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRes {

	private String email;
	private String name;
	private String phone;
	private String nickname;
	private String userType;

}
