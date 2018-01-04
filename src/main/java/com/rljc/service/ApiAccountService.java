package com.rljc.service;

import com.rljc.module.ApiAccount;
import com.rljc.dao.ApiAccountDao;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

@Component
@Transactional
public class ApiAccountService extends BaseService<ApiAccount> {

	@Autowired
	private ApiAccountDao apiAccountDao;

	@Override
	protected void initDao() {
		this.baseDao = apiAccountDao;
	}
	
	@Override
	public Specification<ApiAccount> buildSpecification(final ApiAccount apiAccount){
		return new Specification<ApiAccount>(){
			public Predicate toPredicate(Root<ApiAccount> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				if(StringUtils.isNotBlank(apiAccount.getAppId())){
					predicate.add(cb.equal(root.get("appId").as(String.class), apiAccount.getAppId()));
				}
				if(apiAccount.getStatus() != null){
					predicate.add(cb.equal(root.get("status").as(Integer.class), apiAccount.getStatus()));
				}
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
	/**
	 * 保存数据
	 * @param apiAccount
	 * @throws Exception
	 */
	public void overSave(final ApiAccount apiAccount)throws Exception{
		
	  long count = this.apiAccountDao.count(new Specification<ApiAccount>() {

		@Override
		public Predicate toPredicate(Root<ApiAccount> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			// TODO Auto-generated method stub
			List<Predicate> predicate = new ArrayList<Predicate>();
			predicate.add(cb.equal(root.get("appId").as(String.class), apiAccount.getAppId()));
			if(apiAccount.getId() != null){
				predicate.add(cb.notEqual(root.get("id").as(Integer.class), apiAccount.getId()));
			}
            Predicate[] pre = new Predicate[predicate.size()];
            return query.where(predicate.toArray(pre)).getRestriction();
		}
	  });
	  
	  if(count != 0){
		  throw new Exception("账号已存在！");
	  }
	  
	  this.saveOrUpdate(apiAccount);
	}
	
}
