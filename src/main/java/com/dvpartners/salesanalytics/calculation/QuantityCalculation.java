package com.dvpartners.salesanalytics.calculation;

import java.util.List;

import com.dvpartners.salesanalytics.model.ProductSalesDetails;

public class QuantityCalculation implements Calculation<Integer> {

	@Override
	public Integer calculate(List<ProductSalesDetails> list) {
		return list.stream().mapToInt(obj -> obj.getSalesLineQuantity()).sum();
	}

	@Override
	public String getCalculationType() {
		return "Quantity";
	}

	@Override
	public Integer calcSum(Integer tier1, Integer tier2, Integer tier3, Integer tier4) {
		// TODO Auto-generated method stub
		return tier1+tier2+tier3+tier4;
	}

	@Override
	public String getUnit() {
		// TODO Auto-generated method stub
		return "count";
	}

	

}
