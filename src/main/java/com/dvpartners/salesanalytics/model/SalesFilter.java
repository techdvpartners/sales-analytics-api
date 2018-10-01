package com.dvpartners.salesanalytics.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class SalesFilter {

	@JsonProperty("categories")
	private List<String> category;
	
	@JsonProperty("groups")
	private List<String> group;
	
	@JsonProperty("subGroups")
	private List<String> subGroup;
	
}
