package org.pyriboo.gis_server.domain.users.controller;

import java.util.List;

import org.pyriboo.gis_server.domain.users.dto.UserReq;
import org.pyriboo.gis_server.domain.users.model.Users;
import org.pyriboo.gis_server.domain.users.service.UserService;
import org.pyriboo.gis_server.global.security.jwt.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signup")
	public ResponseEntity<Users> signup(@RequestBody Users user) {
		Users savedUser = userService.signup(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(@RequestBody UserReq loginRequest) {
		TokenResponse tokenResponse = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
		return ResponseEntity.ok(tokenResponse);
	}

	@GetMapping("/{email}")
	public ResponseEntity<Users> getUserByEmail(@PathVariable String email) {
		return userService.findByEmail(email)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public ResponseEntity<List<Users>> getAllUsers() {
		List<Users> users = userService.findAllUsers();
		return ResponseEntity.ok(users);
	}

	@DeleteMapping("/{email}")
	public ResponseEntity<Void> deleteUser(@PathVariable String email) {
		userService.deleteUser(email);
		return ResponseEntity.ok().build();
	}
}
