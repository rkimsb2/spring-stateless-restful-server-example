package com.krw.spring.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.krw.spring.repo.UserMapper;
import com.krw.spring.service.domain.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void register(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userMapper.insert(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userMapper.selectByUuid(UUID.fromString(username));
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<User> showAll() {
		return userMapper.selectAll();
	}

	@Override
	public User myInfo(UUID uuid) {
		return userMapper.selectByUuid(uuid);
	}
	
	@Override
	public User login(User user) {
		User targetUser = userMapper.selectByEmail(user);
		if (targetUser == null
				|| !bCryptPasswordEncoder.matches(user.getPassword(), targetUser.getPassword())) {
			return null;
		}
		return targetUser;
	}

}
