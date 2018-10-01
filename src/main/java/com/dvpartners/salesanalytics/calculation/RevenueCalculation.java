package com.dvpartners.salesanalytics.calculation;

import java.math.BigDecimal;
import java.util.List;

import com.dvpartners.salesanalytics.model.ProductSalesDetails;

public class RevenueCalculation implements Calculation<BigDecimal> {
	@Override
	public BigDecimal calculate(List<ProductSalesDetails> list) {
		BigDecimal total = new BigDecimal(0.0);
		for(ProductSalesDetails productSalesDetails : list) {
			BigDecimal value = productSalesDetails.getSalesNetExclusiveVat();
			total = total.add(value);
		}
		return total;
	}

	@Override
	public String getCalculationType() {
		return "Revenue";
	}

	@Override
	public BigDecimal calcSum(BigDecimal tier1, BigDecimal tier2, BigDecimal tier3, BigDecimal tier4) {
		// TODO Auto-generated method stub
		return tier1.add(tier2).add(tier3).add(tier4);
	}

	@Override
	public String getUnit() {
		// TODO Auto-generated method stub
		return "currency";
	}

	
}
