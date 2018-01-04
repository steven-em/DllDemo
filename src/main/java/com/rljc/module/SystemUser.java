package com.rljc.module;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.rljc.module.BaseEntity;
/**
 * 后台系统用户类
 * @author hzq
 */
@Entity
@Table(name="system_user")
public class SystemUser extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Column(name="username", nullable=false, length=128, unique=true)
	private String username;
	@Column(name="password", nullable=false, length=128)
	private String password;
	@Column(name="nickname", nullable=false, length=512)
	private String nickname;
	@Column(name="headurl", nullable=false, length=512)
	private String headurl;
	private String mobile;
	private Boolean status;
	@Column(name="role_id")
	private Integer roleId;
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="role_id", insertable=false, updatable=false)//加入一列作为外键
	private Role role;
	
	private String stores; //用户管理的门店，多个逗号隔开
	
	private Boolean  isSmsReceive; // 是否接收管辖范围的短信
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadurl() {
		return headurl;
	}
	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getStores() {
		return stores;
	}
	public void setStores(String stores) {
		this.stores = stores;
	}
	public Boolean getIsSmsReceive() {
		return isSmsReceive;
	}
	public void setIsSmsReceive(Boolean isSmsReceive) {
		this.isSmsReceive = isSmsReceive;
	}
}
