package com.rljc.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.rljc.dao.MemberDao;
import com.rljc.module.Member;

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
public class MemberService extends BaseService<Member> {

	@Autowired
	private MemberDao memberDao;

	@Override
	protected void initDao() {
		this.baseDao = memberDao;
	}
	
	public Member getMemberByMobile(String mobile){
		Member member = new Member();
		member.setMobile(mobile);
		return memberDao.findOne(buildSpecification(member));
	}
	
	public int changeStatus(Integer id, Short status){
		return this.memberDao.changeStatus(id, status);
	}
	
	@Override
	public Specification<Member> buildSpecification(final Member member){
		return new Specification<Member>(){
			public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				if(null != member){
					if(StringUtils.isNotEmpty(member.getMobile())){
						predicate.add(cb.equal(root.get("mobile").as(String.class),member.getMobile()));
					}
					
					if(StringUtils.isNotEmpty(member.getLikeMobile())){
						predicate.add(cb.like(root.get("mobile").as(String.class),"%"+member.getLikeMobile()+"%"));
					}
					
					if(member.getStatus() !=null){
						predicate.add(cb.equal(root.get("status").as(Short.class),member.getStatus()));
					}
					
					if(member.getRegType() != null){
						predicate.add(cb.equal(root.get("regType").as(Short.class),member.getRegType()));
					}
					
					if(StringUtils.isNotBlank(member.getOpenid())){
						predicate.add(cb.equal(root.get("openid").as(String.class),member.getOpenid()));
					}
				}
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
}
