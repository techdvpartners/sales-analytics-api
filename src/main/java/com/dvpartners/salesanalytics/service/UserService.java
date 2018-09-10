package com.dvpartners.salesanalytics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dvpartners.salesanalytics.model.User;
import com.dvpartners.salesanalytics.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		return (List<User>) userRepository.findAll();
	}

	public User createUser(String username, String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User user = new User(username, passwordEncoder.encode(password),"ROLE_USER");
		return userRepository.save(user);
	}
}
