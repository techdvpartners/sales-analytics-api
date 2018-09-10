package com.dvpartners.salesanalytics.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Price {
	@Id
	String productCode;
	String publicationName;
	String category;
	String grp;
	String subgroup;
	String description;
	Integer quantity1;
	Integer quantity2;
	Integer quantity3;
	BigDecimal price1;
	BigDecimal price2;
	BigDecimal price3;
	String priceType;
	String unitOfMeasure;
	BigDecimal vatRate;
	BigDecimal unitDivider;
	String upUnitOfMeasure;
	BigDecimal unitPrice1;
	BigDecimal unitPrice2;
	BigDecimal unitPrice3;
}
