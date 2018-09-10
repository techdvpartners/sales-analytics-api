package com.dvpartners.salesanalytics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dvpartners.salesanalytics.repository.PriceRepository;

@Service
public class PriceService {
	
	@Autowired
	private PriceRepository priceRepository;
	
	public List<String> getDistinctCategory(){
		return priceRepository.findDistinctCategory();
	}
	public List<String> getDistinctGrp(){
		return priceRepository.findDistinctGrp();
	}
	public List<String> getDistinctSubgroup(){
		return priceRepository.findDistinctSubgroup();
	}
}
