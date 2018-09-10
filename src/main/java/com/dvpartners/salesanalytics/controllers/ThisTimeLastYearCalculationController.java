package com.dvpartners.salesanalytics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dvpartners.salesanalytics.model.ThisTimeLastYearCalculation;
import com.dvpartners.salesanalytics.model.ThisTimeLastYearCalculationRequest;
import com.dvpartners.salesanalytics.service.ThisTimeLastYearCalculationService;

@RestController
@Secured ({"ROLE_ADMIN","ROLE_USER"})
public class ThisTimeLastYearCalculationController {
	
	@Autowired
	private ThisTimeLastYearCalculationService thisTimeLastYearCalculationService;
	
	@PostMapping("/thistimelastyear/calculate")
	public ThisTimeLastYearCalculation<?> performCalculation(@RequestBody ThisTimeLastYearCalculationRequest thisTimeLastYearCalculationRequest) {
		return thisTimeLastYearCalculationService.calculate(thisTimeLastYearCalculationRequest.getCalculationType());
	}
}
