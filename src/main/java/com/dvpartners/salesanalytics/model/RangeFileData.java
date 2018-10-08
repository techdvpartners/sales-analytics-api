package com.dvpartners.salesanalytics.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@lombok.Getter
@lombok.Setter
public class RangeFileData {
	@Id
	Integer Id;
	String productCode;
	String publicationName;
	String superCategory;
	String category;
	String grp;
	String subgroup;
	String description;
	Integer qty1;
	Integer qty2;
	Integer qty3;
	Double price1;
	Double price2;
	Double price3;
	String priceType;
	String unitOfMeasure;
	Double vatRate;
	Double unitDivider;
	String upUnitOfMeasure;
	Double unitPrice1;
	Double unitPrice2;
	Double unitPrice3;

}
