package com.krw.spring.rest.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.krw.spring.constants.AuthConstants;
import com.krw.spring.service.UserService;
import com.krw.spring.service.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;

	@PostMapping("/auth")
	public ResponseEntity<String> login(@RequestBody User user) {
		User targetUser = userService.login(user);
		if (targetUser == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		// TODO: beautify response body
		String jwt = Jwts.builder().setAudience(targetUser.getUuid().toString())
				.claim("autorities", targetUser.getAuthorities()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
				.signWith(SignatureAlgorithm.RS256, AuthConstants.getJwtPrivateKey()).compact();
		return new ResponseEntity<>(jwt, HttpStatus.OK);
	}

}
