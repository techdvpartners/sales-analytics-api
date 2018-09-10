package com.dvpartners.salesanalytics.calculation;

import java.math.BigDecimal;
import java.util.List;

import com.dvpartners.salesanalytics.model.ProductSalesDetails;

public class MarginCalculation implements Calculation<BigDecimal> {
	@Override
	public BigDecimal calculate(List<ProductSalesDetails> list) {
		BigDecimal total = new BigDecimal(0.0);
		for(ProductSalesDetails productSalesDetails : list) {
			BigDecimal value = productSalesDetails.getMargin();
			total = total.add(value);
		}
		return total;
	}

	@Override
	public String getCalculationType() {
		return "Margin";
	}

}
