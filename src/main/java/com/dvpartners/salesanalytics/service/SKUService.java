package com.dvpartners.salesanalytics.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dvpartners.salesanalytics.model.PriceSensitivity;
import com.dvpartners.salesanalytics.model.ProductSip;
import com.dvpartners.salesanalytics.model.RangeFileData;
import com.dvpartners.salesanalytics.model.SKUCalculation;
import com.dvpartners.salesanalytics.model.SalesFilter;

@Service
public class SKUService {
	
	@Autowired
	private RangeFileService rangeFileService;
	
	@Autowired
	private PriceSensitivityService priceSensitivityService;
	
	@Autowired
	private ProductSipService productSipService;

	public SKUCalculation calculateSKU(String calculationType) {
		
		List<PriceSensitivity> listPriceSensitivity = priceSensitivityService.getAll();
		List<ProductSip> listProductSIP = productSipService.getAll();
		List<RangeFileData> listRangeFileData = rangeFileService.getAll();
		
		Map<String,ProductSip> mapProductSip = new HashMap<>();
		for(ProductSip productSip : listProductSIP) {
			mapProductSip.put(productSip.getProductCode(), productSip);
		}
		Map<String, Double> mapPriceSensitivity = new HashMap<>();
		for(PriceSensitivity priceSensitivity : listPriceSensitivity) {
			mapPriceSensitivity.put(priceSensitivity.getProductCode(), priceSensitivity.getSensitivity());
		}
		
		SKUCalculation calculation = new SKUCalculation(calculationType, listRangeFileData, mapPriceSensitivity, mapProductSip);
		return calculation;
		
	}
	
	
	public SKUCalculation calulatefilterSKU(String calculationType, SalesFilter salesFilter) {
		
		List<PriceSensitivity> listPriceSensitivity = priceSensitivityService.getAll();
		List<ProductSip> listProductSIP = productSipService.getAll();
		List<RangeFileData> listRangeFileData = rangeFileService.getFilteredData(salesFilter);
		
		Map<String,ProductSip> mapProductSip = new HashMap<>();
		for(ProductSip productSip : listProductSIP) {
			mapProductSip.put(productSip.getProductCode(), productSip);
		}
		Map<String, Double> mapPriceSensitivity = new HashMap<>();
		for(PriceSensitivity priceSensitivity : listPriceSensitivity) {
			mapPriceSensitivity.put(priceSensitivity.getProductCode(), priceSensitivity.getSensitivity());
		}
		
		SKUCalculation calculation = new SKUCalculation(calculationType, listRangeFileData, mapPriceSensitivity, mapProductSip);
		return calculation;
		
	}

}
