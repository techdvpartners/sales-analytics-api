package com.dvpartners.salesanalytics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dvpartners.salesanalytics.model.PriceSensitivity;

public interface PriceSensitivityRepository extends CrudRepository<PriceSensitivity, String> {

	//@Query("select ps.productCode,ps.productName, ps.productMasterCategory1, ps.productMasterCategory2, ps.productMasterCategory3, ps.brand, ps.sensitivity, ps.sensitivityVariance, ps.numberOfMeasurements, ps.salesL12M, ps.opportunityScore from price_sensitivity as ps INNER JOIN price AS price ON psd.product_code = price.product_code where price.category IN('Plumbing') and price.grp IN('Traps')   where price.category IN('Plumbing') and price.grp IN('Traps')")
	@Query(nativeQuery = true, value= "select ps.product_code,ps.product_name, ps.product_master_category1, ps.product_master_category2, ps.product_master_category3, ps.brand, ps.sensitivity, ps.sensitivity_variance, ps.number_of_measurements, ps.salesl12m, ps.opportunity_score from price_sensitivity as ps INNER JOIN price AS price ON ps.product_code = price.product_code ?1")
	List<PriceSensitivity> findByFilterParameters(String filterParam);	
}
