package com.rljc.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.rljc.dao.BaseDao;
public abstract class BaseService<T> implements InitializingBean{
	protected Specification<T> spec = null;
	public BaseDao<T> baseDao;
	@Autowired
	public EntityManagerFactory factory;
	
	protected HttpServletRequest request; 
	
	public Specification<T> buildSpecification(final T t){
		return new Specification<T>(){
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
	@Transactional(readOnly=false)
	public T saveOrUpdate(T t){
		return baseDao.save(t);
	}
	@Transactional(readOnly=true)
	public Long count(T t){
		spec = buildSpecification(t);
		return baseDao.count(spec);
	}
	@Transactional(readOnly=true)
	public Page<T> findAll(T t, PageRequest pageable){
		spec = buildSpecification(t);
		return baseDao.findAll(spec, pageable);
	}
	@Transactional(readOnly=true)
	public List<T> findAll(T t){
		spec = buildSpecification(t);
		return baseDao.findAll(spec);
	}
	@Transactional(readOnly=true)
	public T findOne(Integer id){
		return baseDao.findOne(id);
	}
	@Transactional(readOnly=true)
	public T findOne(T t){
		spec = buildSpecification(t);
		return baseDao.findOne(spec);
	}
	
	public void delete(Integer id){
		baseDao.delete(id);
	}
	
	public void delete(Iterable<T> entities){
		baseDao.delete(entities);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initDao();
	}
	protected abstract void initDao();

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
