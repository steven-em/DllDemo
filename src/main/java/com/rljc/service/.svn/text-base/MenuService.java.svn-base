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

import com.rljc.dao.MenuDao;
import com.rljc.module.Menu;

@Component
@Transactional
public class MenuService extends BaseService<Menu>{
	
	@Autowired
	private MenuDao menuDao;

	@Override
	protected void initDao() {
		this.baseDao = menuDao;
	}
	
	
	@Override
	public Specification<Menu> buildSpecification(final Menu menu){
		return new Specification<Menu>(){
			@Override
			public Predicate toPredicate(Root<Menu> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				
				if(menu.getIsIndex()){
					predicate.add(cb.isNull(root.get("parentId").as(Integer.class)));
					predicate.add(cb.equal(root.get("status").as(Boolean.class), 1));
	                Predicate[] pre = new Predicate[predicate.size()];
	                query.where(predicate.toArray(pre));
	                query.orderBy(cb.asc(root.get("sortOrder").as(Integer.class)));
	                return query.where(predicate.toArray(pre)).getRestriction();
				} else {
					if(StringUtils.isNotBlank(menu.getTitle())){
	                	predicate.add(cb.like(root.get("title").as(String.class), "%" + menu.getTitle() + "%"));
	                }
	                if(menu.getParentId() == -1){
	                	predicate.add(cb.isNull(root.get("parentId").as(Integer.class)));
	                } else if(menu.getParentId() != -1 && menu.getParentId() != null){
	                	predicate.add(cb.equal(root.get("parentId").as(Integer.class), menu.getParentId()));
	                }
	                
	                /*if(searchArticle.getPostTimeEnd()!=null){
	                    predicate.add(cb.lessThanOrEqualTo(root.get("postTime").as(Date.class), searchArticle.getPostTimeEnd()));
	                }
	                if(searchArticle.getRecTimeStart()!=null){
	                    predicate.add(cb.greaterThanOrEqualTo(root.get("recommendTime").as(Date.class), searchArticle.getRecTimeStart()));
	                }
	                if (searchArticle.getRecTimeEnd()!=null){
	                    predicate.add(cb.lessThanOrEqualTo(root.get("recommendTime").as(Date.class), searchArticle.getRecTimeEnd()));
	                }
	                if (StringUtils.isNotBlank(searchArticle.getNickname())){
	                    //两张表关联查询
	                    Join<Article,User> userJoin = root.join(root.getModel().getSingularAttribute("user",User.class),JoinType.LEFT);
	                    predicate.add(cb.like(userJoin.get("nickname").as(String.class), "%" + searchArticle.getNickname() + "%"));
	                }*/
	                Predicate[] pre = new Predicate[predicate.size()];
	                return query.where(predicate.toArray(pre)).getRestriction();
				}
			}
			
		};
	}
}
