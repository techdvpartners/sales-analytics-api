package com.dvpartners.salesanalytics.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.dvpartners.salesanalytics.model.PriceSensitivity;
import com.dvpartners.salesanalytics.model.SalesFilter;
import com.dvpartners.salesanalytics.repository.PriceSensitivityRepository;

@Service
public class PriceSensitivityService {

	@Autowired
	PriceSensitivityRepository priceSensitivityRepository;
	
	public List<PriceSensitivity> getAll() {
		return (List<PriceSensitivity>) priceSensitivityRepository.findAll();
	}
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<PriceSensitivity> getFilteredData(SalesFilter salesFilter){
		StringBuilder filterParam = new StringBuilder("select ps.product_code,ps.product_name, ps.product_master_category1, ps.product_master_category2, ps.product_master_category3, ps.brand, ps.sensitivity, ps.sensitivity_variance, ps.number_of_measurements, ps.salesl12m, ps.opportunity_score from price_sensitivity as ps INNER JOIN price AS price ON ps.product_code = price.product_code ");
		boolean and = false;
		
		List<String> cat = salesFilter.getCategory();
		List<String> grp = salesFilter.getGroup();
		List<String> subgrp = salesFilter.getSubGroup();
		
		if(!ObjectUtils.isEmpty(cat) || !ObjectUtils.isEmpty(grp) || !ObjectUtils.isEmpty(subgrp)) {
			filterParam = filterParam.append(" WHERE ");
		}
		
		List<List<String>> filterList = new ArrayList<List<String>>();
		
		filterList.add(cat);
		filterList.add(grp);
		filterList.add(subgrp);
		
		for(List<String> list : filterList) {
			
			if(!ObjectUtils.isEmpty(list)) {
				if(and) filterParam.append(" AND ");
				filterParam.append(" price.category IN(");
				for (String category : list) {
					filterParam.append("'"+category+"'"+',');
				}
				//cat.forEach( category -> filterParam.append(category+','));
				filterParam.deleteCharAt(filterParam.length()-1);
				filterParam.append(") ");
				and = true;
			}
			
		}
		
		Query query = entityManager.createNativeQuery(filterParam.toString(),PriceSensitivity.class);
		List<PriceSensitivity> list = query.getResultList();
		return list;
		//return (List<PriceSensitivity>) priceSensitivityRepository.findByFilterParameters(filterParam.toString());
	}
	
}
