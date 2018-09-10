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

}
