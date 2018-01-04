package com.rljc.controller.BioAuth.module;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestBody {
	
	@JsonProperty(value = "app_id")
	private String appId;
	
	@JsonProperty(value = "person_id")
	private String personId;
	
	@JsonProperty(value="content_type")
	private String contentType;
	
	@JsonProperty(value="image_data")
	private String[] imageData;
	
	@JsonProperty(value="imageNum")
	private Integer imageNum;
	
	public  RequestBody(){
		
	}
	
	public RequestBody(String appId,String personId,String contentType){
		this.appId = appId;
		this.personId = personId;
		this.contentType = contentType;
	}
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String[] getImageData() {
		return imageData;
	}

	public void setImageData(String[] imageData) {
		this.imageData = imageData;
	}

	public Integer getImageNum() {
		return imageNum;
	}

	public void setImageNum(Integer imageNum) {
		this.imageNum = imageNum;
	}
	
	
}
