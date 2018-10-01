package com.dvpartners.salesanalytics.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvpartners.salesanalytics.model.ProductSalesDetails;
import com.dvpartners.salesanalytics.service.ProductSalesDetailsService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Secured ({"ROLE_ADMIN","ROLE_USER"})
public class ProductSalesDetailsController {
	
	@Autowired
	private ProductSalesDetailsService productSalesDetailsService;
	
	@GetMapping("/product-sales-details")
	public List<ProductSalesDetails> getAll() {
		return productSalesDetailsService.getAll();
	}
}
