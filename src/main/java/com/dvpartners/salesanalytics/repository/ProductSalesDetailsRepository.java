package com.dvpartners.salesanalytics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dvpartners.salesanalytics.model.ProductSalesDetails;

public interface ProductSalesDetailsRepository extends CrudRepository<ProductSalesDetails, Integer> {

	@Query(nativeQuery = true, value="select psd.id,psd.product_code, psd.fin_period, psd.sales_line_quantity, psd.sales_cost, psd.sales_net_exclusive_vat, psd.margin from product_sales_details as psd INNER JOIN price AS price ON psd.product_code = price.product_code ?1")
	List<ProductSalesDetails> findByFilterParameters(String filterParam);
	
	@Query(nativeQuery = true , value = "select psd.* from range_file_data as rfd INNER JOIN product_sales_details as psd ON rfd.product_code = psd.product_code")
	List<ProductSalesDetails> findByRange();
}
