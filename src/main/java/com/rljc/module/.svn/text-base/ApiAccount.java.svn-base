package com.rljc.module;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * API账号管理
 * @author hzq
 */
@Entity
@Table(name="api_account")
public class ApiAccount extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String appId;
	
	private String appkey;

	private Integer status; //状态 0=停用 1=启用
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
