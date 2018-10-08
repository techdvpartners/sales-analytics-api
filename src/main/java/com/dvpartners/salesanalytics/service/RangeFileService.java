package com.dvpartners.salesanalytics.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.dvpartners.salesanalytics.model.RangeFileData;
import com.dvpartners.salesanalytics.model.SalesFilter;
import com.dvpartners.salesanalytics.repository.RangeFileRepository;

@Service
public class RangeFileService {
	
	@Autowired
	private RangeFileRepository rangeFileRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<RangeFileData> getAll() {
		return (List<RangeFileData>) rangeFileRepository.findAll();
	}
	
	
	
	public List<RangeFileData> getFilteredData(SalesFilter salesFilter){
		
		StringBuilder filterParam = new StringBuilder("select * from range_file_data");
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
				filterParam.append(" super_category IN(");
				for (String supcategory : supcat) {
					filterParam.append("'"+supcategory+"'"+',');
				}
				//cat.forEach( category -> filterParam.append(category+','));
				filterParam.deleteCharAt(filterParam.length()-1);
				filterParam.append(") ");
				and = true;
			}
			if(!ObjectUtils.isEmpty(cat)) {
				if(and) filterParam.append(" AND ");
				filterParam.append(" category IN(");
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
				filterParam.append(" grp IN(");
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
				filterParam.append(" subgroup IN(");
				for (String sgrp : subgrp) {
					filterParam.append("'"+sgrp+"'"+',');
				}
				//cat.forEach( category -> filterParam.append(category+','));
				filterParam.deleteCharAt(filterParam.length()-1);
				filterParam.append(") ");
				and = true;
			}
			
		
		
		Query query = entityManager.createNativeQuery(filterParam.toString(), RangeFileData.class);
		List<RangeFileData> list = (List<RangeFileData>)query.getResultList();
		return list;
		
	}
}
