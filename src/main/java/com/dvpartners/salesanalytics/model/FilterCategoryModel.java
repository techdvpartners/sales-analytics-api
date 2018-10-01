package com.dvpartners.salesanalytics.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilterCategoryModel {

	private List<String> grps;
	private List<String> subgroups;
	
}
