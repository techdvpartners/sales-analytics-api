package com.dvpartners.salesanalytics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.dvpartners.salesanalytics.model.SKUCalculation;
import com.dvpartners.salesanalytics.model.ThisTimeLastYearCalculationRequest;
import com.dvpartners.salesanalytics.service.SKUService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Secured ({"ROLE_ADMIN","ROLE_USER"})
public class SKUController {
	
	@Autowired
	private SKUService skuService;
	
	@PostMapping("/sku/calculate")
	public SKUCalculation performCalculation(@RequestBody ThisTimeLastYearCalculationRequest thisTimeLastYearCalculationRequest) {
		return skuService.calculateSKU(thisTimeLastYearCalculationRequest.getCalculationType());
	}
	
	@PostMapping("/sku/salesFilter")
	public SKUCalculation filterCalculation(@RequestBody ThisTimeLastYearCalculationRequest thisTimeLastYearCalculationRequest) {
		return skuService.calulatefilterSKU(thisTimeLastYearCalculationRequest.getCalculationType(),thisTimeLastYearCalculationRequest.getSalesFilter());
	}
}
