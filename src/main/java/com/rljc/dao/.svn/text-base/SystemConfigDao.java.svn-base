package com.rljc.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rljc.module.SystemConfig;

@Repository
public interface SystemConfigDao extends BaseDao<SystemConfig> {
	
	@Modifying
	@Query(value="update SystemConfig o set o.content=:content where o.id =:id")
	@Transactional
	public int updateContent(@Param("id")Integer id,@Param("content")String content);
}