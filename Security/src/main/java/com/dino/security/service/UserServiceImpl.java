package com.dino.security.service;

import org.springframework.stereotype.Service;

import com.dino.security.domain.User;
import com.dino.security.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	@Override
	public User save(User user) {
		user.addAuthority("ROLE_CLIENT");
		return userRepository.save(user);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow();
	}

}
