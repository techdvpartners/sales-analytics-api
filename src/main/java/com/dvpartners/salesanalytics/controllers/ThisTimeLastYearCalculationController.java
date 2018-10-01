package com.dvpartners.salesanalytics.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dvpartners.salesanalytics.model.ThisTimeLastYearCalculation;
import com.dvpartners.salesanalytics.model.ThisTimeLastYearCalculationRequest;
import com.dvpartners.salesanalytics.service.ThisTimeLastYearCalculationService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Secured ({"ROLE_ADMIN","ROLE_USER"})
public class ThisTimeLastYearCalculationController {
	
	@Autowired
	private ThisTimeLastYearCalculationService thisTimeLastYearCalculationService;
	
	/*@PostMapping("/thistimelastyear/calculate")
	public ThisTimeLastYearCalculation<?> performCalculation(@RequestBody ThisTimeLastYearCalculationRequest thisTimeLastYearCalculationRequest) {
		return thisTimeLastYearCalculationService.calculate(thisTimeLastYearCalculationRequest.getCalculationType());
	}*/
	
	@PostMapping("/thistimelastyear/calculate")
	public List<ThisTimeLastYearCalculation<?>> performCalculation(@RequestBody ThisTimeLastYearCalculationRequest thisTimeLastYearCalculationRequest) {
		return thisTimeLastYearCalculationService.calculate();
	}
	
	@PostMapping("/thistimelastyear/salesFilter")
	public List<ThisTimeLastYearCalculation<?>> filterCalculation(@RequestBody ThisTimeLastYearCalculationRequest thisTimeLastYearCalculationRequest) {
		return thisTimeLastYearCalculationService.filterSales(thisTimeLastYearCalculationRequest.getCalculationType(),thisTimeLastYearCalculationRequest.getSalesFilter());
	}
}


