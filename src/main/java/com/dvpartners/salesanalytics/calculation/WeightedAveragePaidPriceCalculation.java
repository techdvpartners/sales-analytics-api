package com.dvpartners.salesanalytics.calculation;

import java.math.BigDecimal;
import java.util.List;

import com.dvpartners.salesanalytics.model.ProductSalesDetails;

public class WeightedAveragePaidPriceCalculation implements Calculation<BigDecimal> {

	@Override
	public BigDecimal calculate(List<ProductSalesDetails> list) {
		BigDecimal ukPriceConstant = new BigDecimal(1.2);
		RevenueCalculation revenueCalculation = new RevenueCalculation();
		QuantityCalculation quantityCalculation = new QuantityCalculation();
		return (revenueCalculation.calculate(list).multiply(ukPriceConstant)).divide(new BigDecimal(quantityCalculation.calculate(list)));
	}

	@Override
	public String getCalculationType() {
		return "Weighted Average Paid Price";
	}

}
