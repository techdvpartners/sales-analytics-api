package com.dvpartners.salesanalytics.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Secured ({"ROLE_ADMIN","ROLE_USER"})
public class LoginController {

	@GetMapping("/user/login")
	public boolean isdLoggedIn(){
		return true;
	}
	
}
