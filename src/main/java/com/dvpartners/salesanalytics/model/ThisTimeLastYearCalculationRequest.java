package com.dvpartners.salesanalytics.model;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
public class ThisTimeLastYearCalculationRequest {
	@JsonProperty("calculationType")
	private String calculationType;
	
	@JsonProperty("salesFilter")
	private SalesFilter salesFilter;
}
