package com.krw.spring.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.krw.spring.service.UserService;
import com.krw.spring.service.domain.User;

/**
 * administration API
 * 
 * @author rwkim
 */
@RestController
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@JsonView(User.View.Admin.class)
	@GetMapping("/users")
	public List<User> allUsers() {
		return userService.showAll();
	}
	
}
