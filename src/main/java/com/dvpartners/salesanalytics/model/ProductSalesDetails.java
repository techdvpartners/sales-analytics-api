package com.dvpartners.salesanalytics.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class ProductSalesDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	String productCode;
	String finPeriod;
	Integer salesLineQuantity;
	BigDecimal salesCost;
	BigDecimal salesNetExclusiveVat;
	BigDecimal margin;
}
