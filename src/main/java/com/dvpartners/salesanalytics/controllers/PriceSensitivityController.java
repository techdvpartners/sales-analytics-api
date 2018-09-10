package com.dvpartners.salesanalytics.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvpartners.salesanalytics.model.PriceSensitivity;
import com.dvpartners.salesanalytics.service.PriceSensitivityService;

@RestController
@Secured ({"ROLE_ADMIN","ROLE_USER"})
public class PriceSensitivityController {
	
	@Autowired
	private PriceSensitivityService priceSensitivityService;
	
	@GetMapping("/price-sensitivity")
	public List<PriceSensitivity> getAll(){
		return priceSensitivityService.getAll();
	}
}
