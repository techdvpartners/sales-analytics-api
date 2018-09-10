package com.dvpartners.salesanalytics.calculation;

import java.util.List;

import com.dvpartners.salesanalytics.model.ProductSalesDetails;

public interface Calculation<T> {

	T calculate(List<ProductSalesDetails> list);
	String getCalculationType();

}
