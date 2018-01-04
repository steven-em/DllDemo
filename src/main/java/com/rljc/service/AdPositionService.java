package com.rljc.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rljc.dao.AdPositionDao;
import com.rljc.module.AdPosition;

@Component
@Transactional
public class AdPositionService extends BaseService<AdPosition> {

	@Autowired
	private AdPositionDao adPositionDao;

	@Override
	protected void initDao() {
		this.baseDao = adPositionDao;
	}
	
	@Override
	public Specification<AdPosition> buildSpecification(final AdPosition adPosition){
		return new Specification<AdPosition>(){
			public Predicate toPredicate(Root<AdPosition> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				if(StringUtils.isNotEmpty(adPosition.getPositionCode())){
                	predicate.add(cb.equal(root.get("positionCode").as(String.class), adPosition.getPositionCode()));
                }
				if(adPosition.getStatus() != null){
					predicate.add(cb.equal(root.get("status").as(Short.class), adPosition.getStatus()));
				}
				if(StringUtils.isNotBlank(adPosition.getTitle())){
					predicate.add(cb.like(root.get("title").as(String.class), "%"+adPosition.getTitle()+"%"));
				}
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
}
