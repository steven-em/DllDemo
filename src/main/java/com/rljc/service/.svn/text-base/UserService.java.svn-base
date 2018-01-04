package com.rljc.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rljc.dao.UserDao;
import com.rljc.module.Role;
import com.rljc.module.SystemUser;

@Component
@Transactional
public class UserService extends BaseService<SystemUser>{
	
	@Autowired
	private UserDao userDao;
	
	public SystemUser getUserByName(String username){
		return userDao.getUserByName(username);
	}
	
	public int updateStatus(Boolean status, List<Integer> ids){
		return this.userDao.updateStatus(status, ids);
	}
	public int updatePassword(Integer id, String password){
		return this.userDao.updatePassword(id, password);
	}
	
	@Override
	public Specification<SystemUser> buildSpecification(final SystemUser entity){
		return new Specification<SystemUser>(){
			@Override
			public Predicate toPredicate(Root<SystemUser> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(entity.getUsername())){
                	predicate.add(cb.like(root.get("username").as(String.class), "%" + entity.getUsername() + "%"));
                }
                if(entity.getRoleId() != null){
                	if(entity.getRoleId() == -1){
                		Predicate p1 = cb.equal(root.get("roleId").as(Integer.class), 9);
						Predicate p2 = cb.equal(root.get("roleId").as(Integer.class), 10);
						Predicate p3 = cb.or(p1, p2);
						predicate.add(p3);
                    }else{
                    	predicate.add(cb.equal(root.get("roleId").as(Integer.class), entity.getRoleId()));
                    }
                }else if(request !=null &&request.isRequestedSessionIdValid() && request.getSession() != null){
                	if(request.getSession().getAttribute("userRole") !=null){
						Role role  = (Role)request.getSession().getAttribute("userRole");
						String path = (StringUtils.isNotBlank(role.getRolePath()) ? role.getRolePath():"")+role.getId()+".";
						Join<SystemUser,Role> userJoin = root.join(root.getModel().getSingularAttribute("role",Role.class),JoinType.LEFT ) ;
						predicate.add(cb.like(userJoin.get("rolePath").as(String.class), path + "%"));
                	}
				}
                if(entity.getStatus() != null){
                	predicate.add(cb.equal(root.get("status").as(Boolean.class), entity.getStatus()));
                }
                if(StringUtils.isNotBlank(entity.getStores())){
                	predicate.add(cb.like(root.get("stores").as(String.class), "%"+entity.getStores() + "%"));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
	/*@Autowired
	private EntityManagerFactory factory;

	public Page<User> findAll(Specification<User> spec, PageRequest pageable){
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery ("select u from User u");
		List results = query.getResultList ();
		em.close();
		em.clear();
		return userDao.findAll(spec, pageable);
	}*/

	@Override
	protected void initDao() {
		this.baseDao = userDao;
	}
	
	/**
	 * 根据门店ID获取一个工位订单的陪看人员
	 * @param storeId 门店ID
	 * @return
	 */
	public SystemUser getAccompanyForOrder(final Integer storeId){
		List<SystemUser> list = userDao.findAll(new Specification<SystemUser>() {
			@Override
			public Predicate toPredicate(Root<SystemUser> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				List<Predicate> predicate = new ArrayList<Predicate>();
				predicate.add(cb.equal(root.get("roleId").as(Integer.class), 9));
				predicate.add(cb.equal(root.get("status").as(Boolean.class), true));
				predicate.add(cb.like(root.get("stores").as(String.class), "%"+storeId+"%"));
				Predicate[] pre = new Predicate[predicate.size()];
	            query.where(predicate.toArray(pre)).getRestriction();
				return null;
			}
		});
		for(SystemUser user : list){
			String stores = user.getStores();
			if(StringUtils.isNotBlank(stores)){
				String str [] = stores.split(",");
				for(String id : str){
					if(Integer.parseInt(id) == storeId)
						return user;
				}
			}
		}
		return null;
	}
	
}
