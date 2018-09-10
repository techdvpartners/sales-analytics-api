package com.dvpartners.salesanalytics.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dvpartners.salesanalytics.calculation.Calculation;

@lombok.Getter
@lombok.Setter
public class ThisTimeLastYearCalculation<T> {
	private String calculationType;
	private T tier1;
	private T tier2;
	private T tier3;
	private T tier4;
	
	public ThisTimeLastYearCalculation(Calculation<T> calculation, List<ProductSalesDetails> listProductSalesDetails, Map<String, Double> mapPriceSensitivity) {
		this.calculationType = calculation.getCalculationType();
		List<ProductSalesDetails> listTier1 = new ArrayList<>();
		List<ProductSalesDetails> listTier2 = new ArrayList<>();
		List<ProductSalesDetails> listTier3 = new ArrayList<>();
		List<ProductSalesDetails> listTier4 = new ArrayList<>();

		for(ProductSalesDetails productSalesDetails : listProductSalesDetails) {
			String productCode = productSalesDetails.getProductCode();
			Double sensitivity = mapPriceSensitivity.get(productCode);
			if(sensitivity != null && sensitivity <= -1) {
				listTier2.add(productSalesDetails);
			}
			else if(sensitivity != null) {
				listTier3.add(productSalesDetails);
			}
			else {
				listTier4.add(productSalesDetails);
			}
		}
		
		this.tier1 = calculation.calculate(listTier1);
		this.tier2 = calculation.calculate(listTier2);
		this.tier3 = calculation.calculate(listTier3);
		this.tier4 = calculation.calculate(listTier4);
	}
}
