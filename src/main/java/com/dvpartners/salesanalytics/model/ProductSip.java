package com.dvpartners.salesanalytics.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@lombok.Getter
@lombok.Setter
public class ProductSip {
	@Id
	String productCode;
	Integer sip;
}