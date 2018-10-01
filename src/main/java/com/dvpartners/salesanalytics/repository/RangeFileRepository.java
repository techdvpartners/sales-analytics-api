package com.dvpartners.salesanalytics.repository;

import org.springframework.data.repository.CrudRepository;
import com.dvpartners.salesanalytics.model.RangeFileData;

public interface RangeFileRepository extends CrudRepository<RangeFileData, String> {

}