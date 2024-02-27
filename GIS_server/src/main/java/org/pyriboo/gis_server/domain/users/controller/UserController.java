package org.pyriboo.gis_server.domain.users.controller;

import org.pyriboo.gis_server.domain.users.model.Users;
import org.pyriboo.gis_server.domain.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<Users> createUser(@RequestBody Users user) {
		Users savedUser = userService.saveUser(user);
		return ResponseEntity.ok(savedUser);
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
