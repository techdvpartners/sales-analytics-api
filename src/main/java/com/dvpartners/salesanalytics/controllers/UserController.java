package com.dvpartners.salesanalytics.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dvpartners.salesanalytics.model.User;
import com.dvpartners.salesanalytics.service.UserService;

@RestController
@Secured ({"ROLE_ADMIN"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping("/user")
	public User create(@RequestParam("username") String username, @RequestParam("password") String password) {
		return userService.createUser(username,password);
	}
}
