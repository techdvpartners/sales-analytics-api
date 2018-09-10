package com.dvpartners.salesanalytics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dvpartners.salesanalytics.model.ProductSalesDetails;
import com.dvpartners.salesanalytics.repository.ProductSalesDetailsRepository;

@Service
public class ProductSalesDetailsService {

	@Autowired
	private ProductSalesDetailsRepository productSalesDetailsRepository;
	
	public List<ProductSalesDetails> getAll() {
		return (List<ProductSalesDetails>) productSalesDetailsRepository.findAll();
	}
	
}
