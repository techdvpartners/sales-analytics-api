package com.dvpartners.salesanalytics.calculation;

import java.math.BigDecimal;
import java.util.List;

import com.dvpartners.salesanalytics.model.ProductSalesDetails;

public class RevenueCalculation implements Calculation<Integer> {
	@Override
	public Integer calculate(List<ProductSalesDetails> list) {
		BigDecimal total = new BigDecimal(0.0);
		for(ProductSalesDetails productSalesDetails : list) {
			BigDecimal value = productSalesDetails.getSalesNetExclusiveVat();
			total = total.add(value);
		}
		return total.intValue();
	}

	@Override
	public String getCalculationType() {
		return "Revenue";
	}

	@Override
	public Integer calcSum(Integer tier1, Integer tier2, Integer tier3, Integer tier4) {
		// TODO Auto-generated method stub
		return (tier1 + tier2 + tier3 +tier4);
	}

	@Override
	public String getUnit() {
		// TODO Auto-generated method stub
		return "currency";
	}

	
}
