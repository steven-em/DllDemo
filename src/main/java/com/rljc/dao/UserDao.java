package com.rljc.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rljc.module.SystemUser;

@Component
public interface UserDao extends BaseDao<SystemUser> {
	//u.status = 1 and 
	@Query("select u from SystemUser u where u.username = :name")
	public SystemUser getUserByName(@Param("name") String userName);
	
	@Transactional
	@Modifying(clearAutomatically=true)
	@Query("update SystemUser s set s.status= ?1 where s.id in ?2")
	public int updateStatus(Boolean status, List<Integer> ids);
	
	@Transactional
	@Modifying(clearAutomatically=true)
	@Query("update SystemUser s set s.password= ?2 where s.id in ?1")
	public int updatePassword(Integer id, String password);
	
	/*@Query("select u from User u where u.firstname = :#{#user.firstname} or u.lastname = :#{#user.lastname}")
	Iterable<User> findByFirstnameOrLastname(User user);*/
	
	@Transactional
	@Modifying(clearAutomatically=true)
	@Query("delete SystemUser s where s.roleId =?1")
	public int deleteByRoldId(Integer roleId);
	
}
