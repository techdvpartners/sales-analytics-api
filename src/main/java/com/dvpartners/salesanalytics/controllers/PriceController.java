package com.dvpartners.salesanalytics.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvpartners.salesanalytics.service.PriceService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Secured ({"ROLE_ADMIN","ROLE_USER"})
public class PriceController {

	@Autowired
	private PriceService priceService;
	
	@GetMapping("price/category/distinct")
	public List<String> getDistinctCategory(){
		return priceService.getDistinctCategory();
	}
	@GetMapping("price/group/distinct")
	public List<String> getDistinctGrp(){
		return priceService.getDistinctGrp();
	}
	@GetMapping("price/subgroup/distinct")
	public List<String> getDistinctSubgroup(){
		return priceService.getDistinctSubgroup();
	}
}
