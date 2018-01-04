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

import com.rljc.module.BaseEntity;


/**
 * 后台系统菜单
 * @author hzq
 */
@Entity
@Table(name="menu")
public class Menu extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Integer parentId;
	@ManyToOne
	@JoinColumn(name = "parentId", referencedColumnName = "id", insertable=false, updatable=false)  
	private Menu parent;
	@OneToMany
	@JoinColumn(name="parentId",insertable=false, updatable=false)
	@OrderBy("sortOrder ASC")
	private List<Menu> children = new ArrayList<Menu>();
	private String title;
	private String url;
	private String menuPath;//菜单全路径，如：1.2.3.
	@Transient
	private String menuPathStr;
	private Boolean status;
	private Integer sortOrder;
	@Transient
	private Boolean isIndex = false;
	public Boolean getIsIndex() {
		return isIndex;
	}
	public void setIsIndex(Boolean isIndex) {
		this.isIndex = isIndex;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMenuPath() {
		return menuPath;
	}
	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}
	public String getMenuPathStr() {
		return menuPathStr;
	}
	public void setMenuPathStr(String menuPathStr) {
		this.menuPathStr = menuPathStr;
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
