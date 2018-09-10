package com.dvpartners.salesanalytics.service;

import java.math.BigDecimal;
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
import com.dvpartners.salesanalytics.model.ThisTimeLastYearCalculation;

@Service
public class ThisTimeLastYearCalculationService {

	@Autowired
	private ProductSalesDetailsService productSalesDetailsService;
	
	@Autowired
	private PriceSensitivityService priceSensitivityService;

	public ThisTimeLastYearCalculation<?> calculate(String calculationType) {
		List<PriceSensitivity> listPriceSensitivity = priceSensitivityService.getAll();
		Map<String, Double> mapPriceSensitivity = new HashMap<>();
		for(PriceSensitivity priceSensitivity : listPriceSensitivity) {
			mapPriceSensitivity.put(priceSensitivity.getProductCode(), priceSensitivity.getSensitivity());
		}
		List<ProductSalesDetails> listProductSalesDetails = productSalesDetailsService.getAll();
		ThisTimeLastYearCalculation<?> thisTimeLastYearCalculation = null;
		if(calculationType.equalsIgnoreCase("Revenue")) {
			thisTimeLastYearCalculation = new ThisTimeLastYearCalculation<BigDecimal>(new RevenueCalculation(), listProductSalesDetails, mapPriceSensitivity);
		}
		else if(calculationType.equalsIgnoreCase("Margin")) {
			thisTimeLastYearCalculation = new ThisTimeLastYearCalculation<BigDecimal>(new MarginCalculation(), listProductSalesDetails, mapPriceSensitivity);
		}
		else if(calculationType.equalsIgnoreCase("Margin %")) {
			thisTimeLastYearCalculation = new ThisTimeLastYearCalculation<Integer>(new MarginPercentageCalculation(), listProductSalesDetails, mapPriceSensitivity);
		}
		else if(calculationType.equalsIgnoreCase("Quantity")) {
			thisTimeLastYearCalculation = new ThisTimeLastYearCalculation<Integer>(new QuantityCalculation(), listProductSalesDetails, mapPriceSensitivity);
		}
		else if(calculationType.equalsIgnoreCase("Flat Average Price per SKU")) {
			thisTimeLastYearCalculation = new ThisTimeLastYearCalculation<BigDecimal>(new FlatAveragePricePerSkuCalculation(), listProductSalesDetails, mapPriceSensitivity);
		}
		else if(calculationType.equalsIgnoreCase("Weighted Average Paid Price")) {
			thisTimeLastYearCalculation = new ThisTimeLastYearCalculation<BigDecimal>(new WeightedAveragePaidPriceCalculation(), listProductSalesDetails, mapPriceSensitivity);
		}
		else {
			
		}
		
		return thisTimeLastYearCalculation;
	}

}
