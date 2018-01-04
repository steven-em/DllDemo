package com.rljc.module;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * 
 * @author wuwh
 * 用户
 * @date 2016年9月19日 下午2:21:51  
 *
 */
@Entity
@Table(name="member")
public class Member extends BaseEntity {

	private static final long serialVersionUID = 1L; 
	
	private String mobile; // 手机号
	
	private String password;//密码 
	
	private Short status; // 状态 0：未激活 1：激活
	
	private Long collectTotal; // 收藏总数
	
	private Long followTotal; // 关注总数
	
	private Short regType; // 注册类型 0:app普通注册 1:微信注册
	
	private String openid; // 第三方登录标识
	
	@Transient
	private Integer basicInfoId; // 基础信息ID
	
	@Transient
	private Short basicInfoType; // 基础信息类型
	
	@Transient
	private String likeMobile; // 模糊查找手机码
	
	public Member() {}
	
	//create:WHJ date:2016-06-01
	public Member(Integer id, Long followTotal,  String mobile, Long collectTotal) {
		super();
		this.id = id;
		this.followTotal = followTotal;
		this.collectTotal = collectTotal;
		this.mobile = mobile; 
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Long getCollectTotal() {
		return collectTotal;
	}

	public void setCollectTotal(Long collectTotal) {
		this.collectTotal = collectTotal;
	}

	public Long getFollowTotal() {
		return followTotal;
	}

	public void setFollowTotal(Long followTotal) {
		this.followTotal = followTotal;
	}

	public Integer getBasicInfoId() {
		return basicInfoId;
	}

	public void setBasicInfoId(Integer basicInfoId) {
		this.basicInfoId = basicInfoId;
	}

	public Short getBasicInfoType() {
		return basicInfoType;
	}

	public void setBasicInfoType(Short basicInfoType) {
		this.basicInfoType = basicInfoType;
	}

	public Short getRegType() {
		return regType;
	}

	public void setRegType(Short regType) {
		this.regType = regType;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getLikeMobile() {
		return likeMobile;
	}

	public void setLikeMobile(String likeMobile) {
		this.likeMobile = likeMobile;
	}
}
