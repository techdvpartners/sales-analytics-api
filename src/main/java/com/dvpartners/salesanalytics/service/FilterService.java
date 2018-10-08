package com.dvpartners.salesanalytics.service;

import java.util.Collections;
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
	
	
	public List<String> getDistinctSuperCategory(){
		List<String> superCategory = filterRepository.findDistinctSuperCategory();
		Collections.sort(superCategory);
		return superCategory;
	}
	public List<String> getDistinctCategory(){
		List<String> category = filterRepository.findDistinctCategory();
		Collections.sort(category);
		return category;
	}
	public List<String> getDistinctGrp(){
		List<String> grp = filterRepository.findDistinctGrp();
		Collections.sort(grp);
		return grp;
	}
	public List<String> getDistinctSubgroup(){
		List<String> subGroup = filterRepository.findDistinctSubgroup();
		Collections.sort(subGroup);
		return subGroup;
	}
	public FilterCategoryModel getFilteredSuperCategory(SalesFilter salesFilter){
		List<String> catList;
		List<String> grpList;
		List<String> subgroupList;
		if(salesFilter.getSuperCategory().size()>0) {
			catList = filterRepository.findDistinctCatBySuperCategoryIn(salesFilter.getSuperCategory());
			grpList = filterRepository.findDistinctGrpBySuperCategoryIn(salesFilter.getSuperCategory());
			subgroupList = filterRepository.findDistinctSubgroupBySuperCategoryIn(salesFilter.getSuperCategory());
		}else {
			catList = filterRepository.findDistinctCategory();
			grpList = filterRepository.findDistinctGrp();
			subgroupList = filterRepository.findDistinctSubgroup();
		}
		
		Collections.sort(catList);
		Collections.sort(grpList);
		Collections.sort(subgroupList);
		
		FilterCategoryModel filterCategoryModel = new FilterCategoryModel(catList, grpList, subgroupList);
		
		return filterCategoryModel;
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
		
		Collections.sort(grpList);
		Collections.sort(subgroupList);
		
		FilterCategoryModel filterCategoryModel = new FilterCategoryModel(null, grpList, subgroupList);
		
		return filterCategoryModel;
	}
	public FilterCategoryModel getFilteredGrp(SalesFilter salesFilter){
		List<String> subgroupList;
		if(salesFilter.getGroup().size()>0) {
			subgroupList = filterRepository.findDistinctSubgroupByGrpIn(salesFilter.getGroup());
		}else {
			subgroupList = filterRepository.findDistinctSubgroup();
		}
		
		Collections.sort(subgroupList);
		
		FilterCategoryModel filterCategoryModel = new FilterCategoryModel(null, null, subgroupList);
		return filterCategoryModel;
	}
}
