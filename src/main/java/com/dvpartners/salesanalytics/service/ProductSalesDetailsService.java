package com.dvpartners.salesanalytics.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.dvpartners.salesanalytics.model.ProductSalesDetails;
import com.dvpartners.salesanalytics.model.SalesFilter;
import com.dvpartners.salesanalytics.repository.ProductSalesDetailsRepository;

@Service
public class ProductSalesDetailsService {

	@Autowired
	private ProductSalesDetailsRepository productSalesDetailsRepository;
	
	public List<ProductSalesDetails> getAll() {
		return (List<ProductSalesDetails>) productSalesDetailsRepository.findAll();//.findByRange();
	}
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<ProductSalesDetails> getFilteredData(SalesFilter salesFilter){
		
		//StringBuilder filterParam = new StringBuilder("select psd.* from range_file_data as rfd INNER JOIN product_sales_details as psd ON rfd.product_code = psd.product_code");
		StringBuilder filterParam = new StringBuilder("select * product_sales_details as psd ON rfd.product_code = psd.product_code");
		boolean and = false;
		
		List<String> supcat = salesFilter.getSuperCategory();
		List<String> cat = salesFilter.getCategory();
		List<String> grp = salesFilter.getGroup();
		List<String> subgrp = salesFilter.getSubGroup();
		
		if(!ObjectUtils.isEmpty(supcat) || !ObjectUtils.isEmpty(cat) || !ObjectUtils.isEmpty(grp) || !ObjectUtils.isEmpty(subgrp)) {
			filterParam = filterParam.append(" WHERE ");
		}
			if(!ObjectUtils.isEmpty(supcat)) {
				if(and) filterParam.append(" AND ");
				filterParam.append(" rfd.super_category IN(");
				for (String supcategory : supcat) {
					filterParam.append("'"+supcategory+"'"+',');
				}
				filterParam.deleteCharAt(filterParam.length()-1);
				filterParam.append(") ");
				and = true;
			}
			if(!ObjectUtils.isEmpty(cat)) {
				if(and) filterParam.append(" AND ");
				filterParam.append(" rfd.category IN(");
				for (String category : cat) {
					filterParam.append("'"+category+"'"+',');
				}
				//cat.forEach( category -> filterParam.append(category+','));
				filterParam.deleteCharAt(filterParam.length()-1);
				filterParam.append(") ");
				and = true;
			}
			if(!ObjectUtils.isEmpty(grp)) {
				if(and) filterParam.append(" AND ");
				filterParam.append(" rfd.grp IN(");
				for (String group : grp) {
					filterParam.append("'"+group+"'"+',');
				}
				//cat.forEach( category -> filterParam.append(category+','));
				filterParam.deleteCharAt(filterParam.length()-1);
				filterParam.append(") ");
				and = true;
			}
			if(!ObjectUtils.isEmpty(subgrp)) {
				if(and) filterParam.append(" AND ");
				filterParam.append(" rfd.subgroup IN(");
				for (String sgrp : subgrp) {
					filterParam.append("'"+sgrp+"'"+',');
				}
				//cat.forEach( category -> filterParam.append(category+','));
				filterParam.deleteCharAt(filterParam.length()-1);
				filterParam.append(") ");
				and = true;
			}
		
		Query query = entityManager.createNativeQuery(filterParam.toString(), ProductSalesDetails.class);
		List<ProductSalesDetails> list = (List<ProductSalesDetails>)query.getResultList();
		return list;
		//return (List<ProductSalesDetails>) productSalesDetailsRepository.findByFilterParameters(filterParam.toString());
	}
	
}
