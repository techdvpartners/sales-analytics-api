package com.dvpartners.salesanalytics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dvpartners.salesanalytics.model.FilterCategoryModel;
import com.dvpartners.salesanalytics.model.SalesFilter;
import com.dvpartners.salesanalytics.repository.FilterRepository;

@Service
public class FilterService {
	
	@Autowired
	private FilterRepository filterRepository;
	
	public List<String> getDistinctCategory(){
		return filterRepository.findDistinctCategory();
	}
	public List<String> getDistinctGrp(){
		return filterRepository.findDistinctGrp();
	}
	public List<String> getDistinctSubgroup(){
		return filterRepository.findDistinctSubgroup();
	}
	public FilterCategoryModel getFilteredCategory(SalesFilter salesFilter){
		List<String> grpList;
		List<String> subgroupList;
		if(salesFilter.getCategory().size()>0) {
			grpList = filterRepository.findDistinctGrpByCategoryIn(salesFilter.getCategory());
			subgroupList = filterRepository.findDistinctSubgroupByCategoryIn(salesFilter.getCategory());
		}else {
			grpList = filterRepository.findDistinctGrp();
			subgroupList = filterRepository.findDistinctSubgroup();
		}
		
		FilterCategoryModel filterCategoryModel = new FilterCategoryModel(grpList, subgroupList);
		
		return filterCategoryModel;
	}
	public FilterCategoryModel getFilteredGrp(SalesFilter salesFilter){
		List<String> subgroupList;
		if(salesFilter.getGroup().size()>0) {
			subgroupList = filterRepository.findDistinctSubgroupByGrpIn(salesFilter.getGroup());
		}else {
			subgroupList = filterRepository.findDistinctSubgroup();
		}
		
		FilterCategoryModel filterCategoryModel = new FilterCategoryModel(null, subgroupList);
		return filterCategoryModel;
	}
}
