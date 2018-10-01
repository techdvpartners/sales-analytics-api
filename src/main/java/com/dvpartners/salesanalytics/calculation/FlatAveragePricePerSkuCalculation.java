package com.dvpartners.salesanalytics.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.dvpartners.salesanalytics.model.ProductSalesDetails;

public class FlatAveragePricePerSkuCalculation implements Calculation<BigDecimal> {

	@Override
	public BigDecimal calculate(List<ProductSalesDetails> list) {
		if(list.size()==0 || list.isEmpty()) return new BigDecimal(0);
		BigDecimal sum = null;
		BigDecimal count = null;
		BigDecimal ukPriceConstant = new BigDecimal(1.2);
		for(ProductSalesDetails productSalesDetails : list) {
			if(productSalesDetails.getSalesLineQuantity()==0)continue;
			BigDecimal asp = (productSalesDetails.getSalesNetExclusiveVat().multiply(ukPriceConstant)).divide(new BigDecimal(productSalesDetails.getSalesLineQuantity()),3,RoundingMode.FLOOR);
			if(sum == null) {
				sum = asp;
				count = new BigDecimal(1);
			}
			else {
				sum = sum.add(asp);
				count = count.add(new BigDecimal(1));
			}
		}
		if(sum != null && count != null) {
			return sum.divide(count,3,RoundingMode.FLOOR);
		}
		else {
			return null;
		}
	}

	@Override
	public String getCalculationType() {
		return "Flat Average Price per SKU";
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
