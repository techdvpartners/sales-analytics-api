package com.dvpartners.salesanalytics.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@lombok.Getter
@lombok.Setter
public class PriceSensitivity {
	@Id
	String productCode;
	String productName;
	String productMasterCategory1;
	String productMasterCategory2;
	String productMasterCategory3;
	String brand;
	Double sensitivity;
	Double sensitivityVariance;
	Integer numberOfMeasurements;
	Double salesL12M;
	Double opportunityScore;
}
