package com.rljc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rljc.module.RegisteredMen;

@Repository
public interface RegisteredMenDao extends BaseDao<RegisteredMen> {

	@Query(value="select * from registered_men where api_account_id=?1 limit ?2,?3",nativeQuery=true)
	public List<RegisteredMen> queryListByOffSet(Integer appId,Integer offset,Integer limit);
	
}