package com.krw.spring.rest.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.krw.spring.service.UserService;
import com.krw.spring.service.domain.User;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/users")
	public void create(@RequestBody User user) {
		// TODO: value null check
		userService.register(user);
	}
	
	@JsonView(User.View.EndUserMe.class)
	@GetMapping("/users/me")
	public User get(Authentication authentication) {
		// XXX See JwtAuthenticationProvider.authenticate()
		UUID uuid = (UUID) authentication.getPrincipal();
		return userService.myInfo(uuid);
	}

}
