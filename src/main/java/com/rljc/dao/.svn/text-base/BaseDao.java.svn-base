package com.rljc.dao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseDao<T> extends 
						/*PagingAndSortingRepository<T, Integer>,*/ 
						JpaSpecificationExecutor<T>, 
						CrudRepository<T, Integer>/*, 
						JpaRepository<T, Integer>*/ {
	
}
