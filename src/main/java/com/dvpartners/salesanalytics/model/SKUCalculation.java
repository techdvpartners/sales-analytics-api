package com.dvpartners.salesanalytics.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@lombok.Getter
@lombok.Setter
public class SKUCalculation {
	private String calculationType;
	private int tier1;
	private int tier2;
	private int tier3;
	private int tier4;
	private int total;
	private String unit;
	private List<SkuDialogModel> sku_tier1;
	private List<SkuDialogModel> sku_tier2;
	private List<SkuDialogModel> sku_tier3;
	private List<SkuDialogModel> sku_tier4;
	private List<SkuDialogModel> sku_total;
	
	public SKUCalculation(String calculationType, List<RangeFileData> listRangeFileData, Map<String, Double> mapPriceSensitivity, Map<String,ProductSip> mapProductSip) {
		
		List<RangeFileData> listTier1 = new ArrayList<>();
		List<RangeFileData> listTier2 = new ArrayList<>();
		List<RangeFileData> listTier3 = new ArrayList<>();
		List<RangeFileData> listTier4 = new ArrayList<>();

		for(RangeFileData rangeFileData : listRangeFileData) {
			String productCode = rangeFileData.getProductCode();
			Double sensitivity = mapPriceSensitivity.get(productCode);
			if(mapProductSip.containsKey(productCode)) {
				listTier1.add(rangeFileData);
			}
			else if(sensitivity != null && sensitivity <= -1) {
				listTier2.add(rangeFileData);
			}
			else if(sensitivity != null) {
				listTier3.add(rangeFileData);
			}
			else {
				listTier4.add(rangeFileData);
			}
		}
		
		this.calculationType = calculationType;
		this.unit = "count";
		this.tier1 = listTier1.size();
		this.tier2 = listTier2.size();
		this.tier3 = listTier3.size();
		this.tier4 = listTier4.size();
		this.total = this.tier1 + this.tier2 + this.tier3 + this.tier4;
		this.sku_tier1 = new ArrayList<>();
		this.sku_tier2 = new ArrayList<>();
		this.sku_tier3 = new ArrayList<>();
		this.sku_tier4 = new ArrayList<>();
		this.sku_total = new ArrayList<>();
		for(RangeFileData rangeData : listTier1) {
			this.sku_tier1.add(new SkuDialogModel(rangeData.getProductCode(), rangeData.getDescription()));
		}
		for(RangeFileData rangeData : listTier2) {
			this.sku_tier2.add(new SkuDialogModel(rangeData.getProductCode(), rangeData.getDescription()));
		}
		for(RangeFileData rangeData : listTier3) {
			this.sku_tier3.add(new SkuDialogModel(rangeData.getProductCode(), rangeData.getDescription()));
		}
		for(RangeFileData rangeData : listTier4) {
			this.sku_tier4.add(new SkuDialogModel(rangeData.getProductCode(), rangeData.getDescription()));
		}
		
		this.sku_total.addAll(this.sku_tier1);
		this.sku_total.addAll(this.sku_tier2);
		this.sku_total.addAll(this.sku_tier3);
		this.sku_total.addAll(this.sku_tier4);
	}
	
}


