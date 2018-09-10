package com.dvpartners.salesanalytics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dvpartners.salesanalytics.model.Price;

public interface PriceRepository extends CrudRepository<Price, String> {

	@Query("SELECT DISTINCT category FROM Price")
	List<String> findDistinctCategory();
	@Query("SELECT DISTINCT grp FROM Price")
	List<String> findDistinctGrp();
	@Query("SELECT DISTINCT subgroup FROM Price")
	List<String> findDistinctSubgroup();

}
