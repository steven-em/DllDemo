package com.rljc.module;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 后台系统配置表
 * @author hzq
 */
@Entity
@Table(name="system_config")
public class SystemConfig extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String name; //名称
	private Integer type; //类型 1 整数、 2 浮点数、 3 字符串、 4 时间、5、 文本  6 、上传文件类型 excel文件 7、安卓安装包 8、链接文本 9、纯文本 10、上传图片
	@Column(columnDefinition="text")
	private String content; //内容
	
	private Short tabId; //配置所属的tabId 1 基本配置 2 网站编辑
	
	@Column(length=500)
	private String remark; //备注说明
	
	private String  barCode;  //编码 唯一性
	
	@Transient
	private String picPath; // 图片地址
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTypeName(){
		String value = null;
		if(null != type){
			switch(type){
			case 1:
				value = "整数";
				break;
			case 2:
				value = "浮点数";
				break;
			case 3:
				value = "字符串";
				break;
			case 4:
				value = "时间";
				break;
			case 5:
				value = "文本";
				break;
			}
		}
		return value;
	}
	public Short getTabId() {
		return tabId;
	}
	public void setTabId(Short tabId) {
		this.tabId = tabId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	
}
