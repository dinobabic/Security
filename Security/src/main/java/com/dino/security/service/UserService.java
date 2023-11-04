package com.dino.security.service;

import com.dino.security.domain.User;

public interface UserService {

	User save(User user);

	User findByEmail(String email);

}
