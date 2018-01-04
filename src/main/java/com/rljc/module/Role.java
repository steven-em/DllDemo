package com.rljc.module;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 系统角色类
 * @author hzq
 */
@Entity
@Table(name="role")
public class Role extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String rolename;
	private String menus;
	private Integer parentId;
	@ManyToOne
	@JoinColumn(name = "parentId", referencedColumnName = "id", insertable=false, updatable=false)  
	private Role role;
	@OneToMany
	@JoinColumn(name="parentId",insertable=false, updatable=false)
	@OrderBy("sortOrder ASC")
	private List<Role> children = new ArrayList<Role>();
	private String rolePath;//角色全路径，如：1.2.3.
	@Transient
	private String rolePathStr;
	private Boolean status;
	private Integer sortOrder;
	
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getMenus() {
		return menus;
	}
	public void setMenus(String menus) {
		this.menus = menus;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<Role> getChildren() {
		return children;
	}
	public void setChildren(List<Role> children) {
		this.children = children;
	}
	public String getRolePath() {
		return rolePath;
	}
	public void setRolePath(String rolePath) {
		this.rolePath = rolePath;
	}
	public String getRolePathStr() {
		return rolePathStr;
	}
	public void setRolePathStr(String rolePathStr) {
		this.rolePathStr = rolePathStr;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	
}
