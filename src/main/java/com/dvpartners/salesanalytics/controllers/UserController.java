package com.dvpartners.salesanalytics.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dvpartners.salesanalytics.model.User;
import com.dvpartners.salesanalytics.service.UserService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Secured ({"ROLE_ADMIN"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	//@PostMapping("/user")
	@RequestMapping(value="/user", method = RequestMethod.POST)
	public User create(@RequestBody User user) {
		return userService.createUser(user.getUsername(), user.getPassword());
	}
}
