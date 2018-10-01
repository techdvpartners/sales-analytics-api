package com.dvpartners.salesanalytics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dvpartners.salesanalytics.model.RangeFileData;

public interface FilterRepository extends CrudRepository<RangeFileData, String> {

	@Query("SELECT DISTINCT category FROM RangeFileData")
	List<String> findDistinctCategory();
	@Query("SELECT DISTINCT grp FROM RangeFileData")
	List<String> findDistinctGrp();
	@Query("SELECT DISTINCT subgroup FROM RangeFileData")
	List<String> findDistinctSubgroup();
	@Query("SELECT DISTINCT grp FROM RangeFileData WHERE category IN(?1)")
	List<String> findDistinctGrpByCategoryIn(List<String> categories);
	@Query("SELECT DISTINCT subgroup FROM RangeFileData WHERE category IN(?1)")
	List<String> findDistinctSubgroupByCategoryIn(List<String> categories);
	@Query("SELECT DISTINCT subgroup FROM RangeFileData WHERE grp IN(?1)")
	List<String> findDistinctSubgroupByGrpIn(List<String> grps);
}