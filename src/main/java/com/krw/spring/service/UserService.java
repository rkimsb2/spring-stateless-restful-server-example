package com.krw.spring.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.krw.spring.service.domain.User;

public interface UserService extends UserDetailsService {

	void register(User user);
	
	User myInfo(UUID uuid);
	
	User login(User user);

	List<User> showAll();
}
