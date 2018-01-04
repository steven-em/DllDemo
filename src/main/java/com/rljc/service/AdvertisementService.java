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

import com.rljc.dao.AdvertisementDao;
import com.rljc.module.Advertisement;

@Component
@Transactional
public class AdvertisementService extends BaseService<Advertisement> {

	@Autowired
	private AdvertisementDao advertisementDao;

	@Override
	protected void initDao() {
		this.baseDao = advertisementDao;
	}
	
	@Override
	public Specification<Advertisement> buildSpecification(final Advertisement advertisement){
		return new Specification<Advertisement>(){
			public Predicate toPredicate(Root<Advertisement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				if(advertisement.getPositionId() != null){
                	predicate.add(cb.equal(root.get("positionId").as(Integer.class), advertisement.getPositionId()));
                }
				if(advertisement.getStatus() != null){
                	predicate.add(cb.equal(root.get("status").as(Short.class), advertisement.getStatus()));
                }
				if(StringUtils.isNotBlank(advertisement.getTitle())){
					predicate.add(cb.like(root.get("title").as(String.class), "%"+advertisement.getTitle()+"%"));
				}
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
}
