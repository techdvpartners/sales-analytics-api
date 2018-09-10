package com.dvpartners.salesanalytics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dvpartners.salesanalytics.model.PriceSensitivity;
import com.dvpartners.salesanalytics.repository.PriceSensitivityRepository;

@Service
public class PriceSensitivityService {

	@Autowired
	PriceSensitivityRepository priceSensitivityRepository;
	
	public List<PriceSensitivity> getAll() {
		return (List<PriceSensitivity>) priceSensitivityRepository.findAll();
	}
	
}
