package com.rljc.common;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@ToString(exclude="totalPages")
public class PageHelp<T> {
	private int pageSize;
	private int pageNum;
	private int totalPages;//总页数
	private int totalElements;//总记录数
	private int number;
	private Boolean hasNext; // 下一页
	private Boolean hasPrevious; // 上一页
	private List<T> content;
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	public Boolean getHasNext() {
		return hasNext;
	}
	public void setHasNext(Boolean hasNext) {
		this.hasNext = hasNext;
	}
	public Boolean getHasPrevious() {
		return hasPrevious;
	}
	public void setHasPrevious(Boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}
}
