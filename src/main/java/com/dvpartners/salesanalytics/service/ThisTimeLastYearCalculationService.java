package com.dvpartners.salesanalytics.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dvpartners.salesanalytics.calculation.FlatAveragePricePerSkuCalculation;
import com.dvpartners.salesanalytics.calculation.MarginCalculation;
import com.dvpartners.salesanalytics.calculation.MarginPercentageCalculation;
import com.dvpartners.salesanalytics.calculation.QuantityCalculation;
import com.dvpartners.salesanalytics.calculation.RevenueCalculation;
import com.dvpartners.salesanalytics.calculation.WeightedAveragePaidPriceCalculation;
import com.dvpartners.salesanalytics.model.PriceSensitivity;
import com.dvpartners.salesanalytics.model.ProductSalesDetails;
import com.dvpartners.salesanalytics.model.ProductSip;
import com.dvpartners.salesanalytics.model.RangeFileData;
import com.dvpartners.salesanalytics.model.SalesFilter;
import com.dvpartners.salesanalytics.model.ThisTimeLastYearCalculation;

@Service
public class ThisTimeLastYearCalculationService {

	@Autowired
	private ProductSalesDetailsService productSalesDetailsService;
	
	@Autowired
	private PriceSensitivityService priceSensitivityService;
	
	@Autowired
	private ProductSipService productSipService;
	
	@Autowired
	private RangeFileService rangeFileService;
	

	public ThisTimeLastYearCalculation<?> calculate(String calculationType) {
		List<PriceSensitivity> listPriceSensitivity = priceSensitivityService.getAll();
		List<ProductSip> listProductSIP = productSipService.getAll();
		Map<String,ProductSip> mapProductSip = new HashMap<>();
		for(ProductSip productSip : listProductSIP) {
			mapProductSip.put(productSip.getProductCode(), productSip);
		}
		Map<String, Double> mapPriceSensitivity = new HashMap<>();
		for(PriceSensitivity priceSensitivity : listPriceSensitivity) {
			mapPriceSensitivity.put(priceSensitivity.getProductCode(), priceSensitivity.getSensitivity());
		}
		
		List<ProductSalesDetails> listProductSalesDetails = productSalesDetailsService.getAll();
		
		ThisTimeLastYearCalculation<?> thisTimeLastYearCalculation = null;
		if(calculationType.equalsIgnoreCase("Revenue")) {
			thisTimeLastYearCalculation = new ThisTimeLastYearCalculation<BigDecimal>(new RevenueCalculation(), listProductSalesDetails, mapPriceSensitivity, mapProductSip);
		}
		else if(calculationType.equalsIgnoreCase("Margin")) {
			thisTimeLastYearCalculation = new ThisTimeLastYearCalculation<BigDecimal>(new MarginCalculation(), listProductSalesDetails, mapPriceSensitivity, mapProductSip);
		}
		else if(calculationType.equalsIgnoreCase("Margin %")) {
			thisTimeLastYearCalculation = new ThisTimeLastYearCalculation<BigDecimal>(new MarginPercentageCalculation(), listProductSalesDetails, mapPriceSensitivity, mapProductSip);
		}
		else if(calculationType.equalsIgnoreCase("Quantity")) {
			thisTimeLastYearCalculation = new ThisTimeLastYearCalculation<Integer>(new QuantityCalculation(), listProductSalesDetails, mapPriceSensitivity, mapProductSip);
		}
		else if(calculationType.equalsIgnoreCase("Flat Average Price per SKU")) {
			thisTimeLastYearCalculation = new ThisTimeLastYearCalculation<BigDecimal>(new FlatAveragePricePerSkuCalculation(), listProductSalesDetails, mapPriceSensitivity, mapProductSip);
		}
		else if(calculationType.equalsIgnoreCase("Weighted Average Paid Price")) {
			thisTimeLastYearCalculation = new ThisTimeLastYearCalculation<BigDecimal>(new WeightedAveragePaidPriceCalculation(), listProductSalesDetails, mapPriceSensitivity, mapProductSip);
		}
		else {
			
		}
		
		return thisTimeLastYearCalculation;
	}
	
	
	public List<ThisTimeLastYearCalculation<?>> filterSales(String calculationType, SalesFilter salesFilter) {
		List<PriceSensitivity> listPriceSensitivity = priceSensitivityService.getAll();
		List<ProductSip> listProductSIP = productSipService.getAll();
		Map<String,ProductSip> mapProductSip = new HashMap<>();
		for(ProductSip productSip : listProductSIP) {
			mapProductSip.put(productSip.getProductCode(), productSip);
		}
		Map<String, Double> mapPriceSensitivity = new HashMap<>();
		for(PriceSensitivity priceSensitivity : listPriceSensitivity) {
			mapPriceSensitivity.put(priceSensitivity.getProductCode(), priceSensitivity.getSensitivity());
		}
		
		List<ProductSalesDetails> listProductSalesDetailsParent = productSalesDetailsService.getAll();
		List<ProductSalesDetails> listProductSalesDetails = new ArrayList<>();
		List<RangeFileData> listRangeFileData = rangeFileService.getFilteredData(salesFilter);
		Map<String,RangeFileData> mapRangeFileData = new HashMap<>();
		for(RangeFileData rangeFileData : listRangeFileData) {
			mapRangeFileData.put(rangeFileData.getProductCode(), rangeFileData);
		}
		
		for(ProductSalesDetails productSalesDetails: listProductSalesDetailsParent) {
			if(mapRangeFileData.containsKey(productSalesDetails.getProductCode())) listProductSalesDetails.add(productSalesDetails);
		}
		
		List<ThisTimeLastYearCalculation<?>> thisTimeLastYearCalculation = new ArrayList<>();
		thisTimeLastYearCalculation.add(new ThisTimeLastYearCalculation<BigDecimal>(new RevenueCalculation(), listProductSalesDetails, mapPriceSensitivity, mapProductSip));
		thisTimeLastYearCalculation.add(new ThisTimeLastYearCalculation<BigDecimal>(new MarginCalculation(), listProductSalesDetails, mapPriceSensitivity, mapProductSip));
		thisTimeLastYearCalculation.add(new ThisTimeLastYearCalculation<BigDecimal>(new MarginPercentageCalculation(), listProductSalesDetails, mapPriceSensitivity, mapProductSip));
		thisTimeLastYearCalculation.add(new ThisTimeLastYearCalculation<Integer>(new QuantityCalculation(), listProductSalesDetails, mapPriceSensitivity, mapProductSip));
		thisTimeLastYearCalculation.add(new ThisTimeLastYearCalculation<BigDecimal>(new FlatAveragePricePerSkuCalculation(), listProductSalesDetails, mapPriceSensitivity, mapProductSip));
		thisTimeLastYearCalculation.add(new ThisTimeLastYearCalculation<BigDecimal>(new WeightedAveragePaidPriceCalculation(), listProductSalesDetails, mapPriceSensitivity, mapProductSip));
		
		return thisTimeLastYearCalculation;
	}
	
	public List<ThisTimeLastYearCalculation<?>> calculate() {
		List<PriceSensitivity> listPriceSensitivity = priceSensitivityService.getAll();
		List<ProductSip> listProductSIP = productSipService.getAll();
		Map<String,ProductSip> mapProductSip = new HashMap<>();
		for(ProductSip productSip : listProductSIP) {
			mapProductSip.put(productSip.getProductCode(), productSip);
		}
		Map<String, Double> mapPriceSensitivity = new HashMap<>();
		for(PriceSensitivity priceSensitivity : listPriceSensitivity) {
			mapPriceSensitivity.put(priceSensitivity.getProductCode(), priceSensitivity.getSensitivity());
		}
		
		List<ProductSalesDetails> listProductSalesDetailsParent = productSalesDetailsService.getAll();
		List<ProductSalesDetails> listProductSalesDetails = new ArrayList<>();
		List<RangeFileData> listRangeFileData = rangeFileService.getAll();
		Map<String,RangeFileData> mapRangeFileData = new HashMap<>();
		for(RangeFileData rangeFileData : listRangeFileData) {
			mapRangeFileData.put(rangeFileData.getProductCode(), rangeFileData);
		}
		
		for(ProductSalesDetails productSalesDetails: listProductSalesDetailsParent) {
			if(mapRangeFileData.containsKey(productSalesDetails.getProductCode())) listProductSalesDetails.add(productSalesDetails);
		}
		
		List<ThisTimeLastYearCalculation<?>> thisTimeLastYearCalculation = new ArrayList<>();
		thisTimeLastYearCalculation.add(new ThisTimeLastYearCalculation<BigDecimal>(new RevenueCalculation(), listProductSalesDetails, mapPriceSensitivity, mapProductSip));
		thisTimeLastYearCalculation.add(new ThisTimeLastYearCalculation<BigDecimal>(new MarginCalculation(), listProductSalesDetails, mapPriceSensitivity, mapProductSip));
		thisTimeLastYearCalculation.add(new ThisTimeLastYearCalculation<BigDecimal>(new MarginPercentageCalculation(), listProductSalesDetails, mapPriceSensitivity, mapProductSip));
		thisTimeLastYearCalculation.add(new ThisTimeLastYearCalculation<Integer>(new QuantityCalculation(), listProductSalesDetails, mapPriceSensitivity, mapProductSip));
		thisTimeLastYearCalculation.add(new ThisTimeLastYearCalculation<BigDecimal>(new FlatAveragePricePerSkuCalculation(), listProductSalesDetails, mapPriceSensitivity, mapProductSip));
		thisTimeLastYearCalculation.add(new ThisTimeLastYearCalculation<BigDecimal>(new WeightedAveragePaidPriceCalculation(), listProductSalesDetails, mapPriceSensitivity, mapProductSip));
		
		return thisTimeLastYearCalculation;
	}

}
