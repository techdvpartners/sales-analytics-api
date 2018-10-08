package com.dvpartners.salesanalytics.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dvpartners.salesanalytics.model.FilterCategoryModel;
import com.dvpartners.salesanalytics.model.SalesFilter;
import com.dvpartners.salesanalytics.service.FilterService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Secured ({"ROLE_ADMIN","ROLE_USER"})
public class FilterController {

	@Autowired
	private FilterService filterService;
	
	@GetMapping("/supercategory/distinct")
	public List<String> getDistinctSuperCategory(){
		return filterService.getDistinctSuperCategory();
	}
	@GetMapping("/category/distinct")
	public List<String> getDistinctCategory(){
		return filterService.getDistinctCategory();
	}
	@GetMapping("/group/distinct")
	public List<String> getDistinctGrp(){
		return filterService.getDistinctGrp();
	}
	@GetMapping("/subgroup/distinct")
	public List<String> getDistinctSubgroup(){
		return filterService.getDistinctSubgroup();
	}
	@PostMapping("/supercategory/filtered")
	public FilterCategoryModel getFilteredSuperCategory(@RequestBody SalesFilter salesFilter){
		return filterService.getFilteredSuperCategory(salesFilter);
	}
	@PostMapping("/category/filtered")
	public FilterCategoryModel getFilteredCategory(@RequestBody SalesFilter salesFilter){
		return filterService.getFilteredCategory(salesFilter);
	}
	@PostMapping("/group/filtered")
	public FilterCategoryModel getFilteredGrp(@RequestBody SalesFilter salesFilter){
		return filterService.getFilteredGrp(salesFilter);
	}
}
