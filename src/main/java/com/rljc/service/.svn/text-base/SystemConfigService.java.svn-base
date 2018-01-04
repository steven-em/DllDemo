package com.rljc.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rljc.dao.SystemConfigDao;
import com.rljc.module.SystemConfig;
import com.rljc.utils.SystemConfigUtil;

@Component
@Transactional
public class SystemConfigService extends BaseService<SystemConfig> {

	@Autowired
	private SystemConfigDao systemConfigDao;

	@Override
	protected void initDao() {
		this.baseDao = systemConfigDao;
	}
	
	@Override
	public Specification<SystemConfig> buildSpecification(final SystemConfig systemConfig){
		return new Specification<SystemConfig>(){
			public Predicate toPredicate(Root<SystemConfig> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<Predicate>();
			    if(StringUtils.isNotBlank(systemConfig.getName())){
			    	predicate.add(cb.like(root.get("name").as(String.class), "%"+systemConfig.getName()+"%"));
			    }
			    if(systemConfig.getTabId() !=null){
			    	predicate.add(cb.equal(root.get("tabId").as(Short.class), systemConfig.getTabId()));
			    }
			    if(StringUtils.isNotBlank(systemConfig.getBarCode())){ //根据条形码查找唯一性
			    	predicate.add(cb.equal(root.get("barCode").as(String.class), systemConfig.getBarCode()));
			    }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
	public void updateBatch(Integer[]id,HttpServletRequest request){
		for(Integer configId : id){
			this.systemConfigDao.updateContent(configId, request.getParameter("content_"+configId));
		}
		List<SystemConfig> list = findAll(new SystemConfig()); //更新内存配置的信息
		SystemConfigUtil.init(list);
	}
}
