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

import com.rljc.dao.RoleDao;
import com.rljc.dao.UserDao;
import com.rljc.module.Role;

@Component
@Transactional
public class RoleService extends BaseService<Role>{
	
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserDao userDao;

	@Override
	protected void initDao() {
		this.baseDao = roleDao;
	}
	@Override
	public Specification<Role> buildSpecification(final Role role){
		return new Specification<Role>(){
			public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(role.getRolename())){
                	predicate.add(cb.like(root.get("rolename").as(String.class), "%" + role.getRolename() + "%"));
                }
                if(role.getParentId() !=null && role.getParentId() == -1){
                	predicate.add(cb.isNull(root.get("parentId").as(Integer.class)));
                } else if(role.getParentId() !=null && role.getParentId() != -1 ){
                	predicate.add(cb.equal(root.get("parentId").as(Integer.class), role.getParentId()));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		userDao.deleteByRoldId(id);
		super.delete(id);
	}
}
