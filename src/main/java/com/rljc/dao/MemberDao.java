package com.rljc.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rljc.module.Member;

@Repository
public interface MemberDao extends BaseDao<Member> {
	
	@Modifying
	@Query(value="update Member o set o.status = :status where o.id =:id")
	@Transactional
	public int changeStatus(@Param("id")Integer id,@Param("status")Short status);
	
	@Modifying
	@Query(value="update member set balance=?1 where id =?2",nativeQuery=true)
	@Transactional
	public int changeMoney(Double money,Integer id);

}