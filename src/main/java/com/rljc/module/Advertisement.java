package com.rljc.module;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 *广告表
 * @author hzq
 */
@Entity
@Table(name="advertisement")
public class Advertisement extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String title;//广告标题
	private Integer positionId;//广告位
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="positionId", insertable=false, updatable=false)//加入一列作为外键
	private AdPosition adPosition;
	private String imageUrl;//广告图片
	private String videoUrl;//视频链接
	private String redirectUrl;//跳转路径
	private String shortDesc;//简单描述
	private Short status;//状态 1=显示，0=不显示
	private Short sortOrder;//排序
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	public AdPosition getAdPosition() {
		return adPosition;
	}
	public void setAdPosition(AdPosition adPosition) {
		this.adPosition = adPosition;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Short getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Short sortOrder) {
		this.sortOrder = sortOrder;
	}
}
