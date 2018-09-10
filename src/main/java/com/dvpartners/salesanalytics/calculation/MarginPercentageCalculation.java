package com.dvpartners.salesanalytics.calculation;

import java.math.BigDecimal;
import java.util.List;

import com.dvpartners.salesanalytics.model.ProductSalesDetails;

public class MarginPercentageCalculation implements Calculation<Integer> {
	@Override
	public Integer calculate(List<ProductSalesDetails> list) {
		MarginCalculation marginCalculation = new MarginCalculation();
		RevenueCalculation revenueCalculation = new RevenueCalculation();
		BigDecimal marginPercentage = marginCalculation.calculate(list).divide(revenueCalculation.calculate(list));
		return marginPercentage.intValue();
	}

	@Override
	public String getCalculationType() {
		return "Margin Percentage";
	}

}
