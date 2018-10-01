package com.dvpartners.salesanalytics.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.dvpartners.salesanalytics.model.ProductSalesDetails;

public class MarginPercentageCalculation implements Calculation<BigDecimal> {
	@Override
	public BigDecimal calculate(List<ProductSalesDetails> list) {
		if(list.size()==0 || list.isEmpty())return new BigDecimal(0);
		MarginCalculation marginCalculation = new MarginCalculation();
		RevenueCalculation revenueCalculation = new RevenueCalculation();
		BigDecimal marginPercentage = marginCalculation.calculate(list).divide(revenueCalculation.calculate(list),3,RoundingMode.FLOOR);
		return marginPercentage;
	}

	@Override
	public String getCalculationType() {
		return "Rate";
	}

	@Override
	public BigDecimal calcSum(BigDecimal tier1, BigDecimal tier2, BigDecimal tier3, BigDecimal tier4) { 
		// TODO Auto-generated method stub
		return (tier1.add(tier2).add(tier3).add(tier4)).divide(new BigDecimal(4));
	}

	@Override
	public String getUnit() {
		// TODO Auto-generated method stub
		return "percent";
	}


}
