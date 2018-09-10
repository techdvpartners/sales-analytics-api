package com.dvpartners.salesanalytics.calculation;

import java.math.BigDecimal;
import java.util.List;

import com.dvpartners.salesanalytics.model.ProductSalesDetails;

public class FlatAveragePricePerSkuCalculation implements Calculation<BigDecimal> {

	@Override
	public BigDecimal calculate(List<ProductSalesDetails> list) {
		BigDecimal sum = null;
		BigDecimal count = null;
		BigDecimal ukPriceConstant = new BigDecimal(1.2);
		for(ProductSalesDetails productSalesDetails : list) {
			BigDecimal asp = (productSalesDetails.getSalesNetExclusiveVat().multiply(ukPriceConstant)).divide(new BigDecimal(productSalesDetails.getSalesLineQuantity()));
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
			return sum.divide(count);
		}
		else {
			return null;
		}
	}

	@Override
	public String getCalculationType() {
		return "Flat Average Price per SKU";
	}

}
