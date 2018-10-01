package com.dvpartners.salesanalytics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dvpartners.salesanalytics.model.ProductSip;
import com.dvpartners.salesanalytics.repository.ProductSipRepository;

@Service
public class ProductSipService {
	
	@Autowired
	private ProductSipRepository productSipRepository;
	
	public List<ProductSip> getAll(){
		return (List<ProductSip>) productSipRepository.findAll();
	}

}
