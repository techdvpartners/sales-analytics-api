package com.dvpartners.salesanalytics.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.dvpartners.salesanalytics.model.ProductSalesDetails;

public class WeightedAveragePaidPriceCalculation implements Calculation<BigDecimal> {

	@Override
	public BigDecimal calculate(List<ProductSalesDetails> list) {
		if(list.size()==0 || list.isEmpty()) return new BigDecimal(0);
		BigDecimal ukPriceConstant = new BigDecimal(1.2);
		RevenueCalculation revenueCalculation = new RevenueCalculation();
		QuantityCalculation quantityCalculation = new QuantityCalculation();
		//Integer result = new BigDecimal(revenueCalculation.calculate(list)).multiply(ukPriceConstant).divide(new BigDecimal(quantityCalculation.calculate(list)),3,RoundingMode.FLOOR).intValue();
		return (new BigDecimal(revenueCalculation.calculate(list)).multiply(ukPriceConstant)).divide(new BigDecimal(quantityCalculation.calculate(list)),3,RoundingMode.FLOOR);
	}

	@Override
	public String getCalculationType() {
		return "Weighted Average Paid Price";
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
