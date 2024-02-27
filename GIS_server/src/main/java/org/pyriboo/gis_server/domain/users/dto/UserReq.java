package org.pyriboo.gis_server.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {

	private String email;
	private String password;
	private String name;
	private String phone;
	private String nickname;
	private String userType;

}
