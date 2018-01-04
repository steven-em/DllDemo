package com.rljc.controller.BioAuth.module;

public class ResponseBodyCloud {
	
	private String errormsg;
	
	private String similarity;
	
	private String errorcode;
	
	private String ref_thres;
	
	private String id;
	
	private String image; //对比成功的图片

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public String getSimilarity() {
		return similarity;
	}

	public void setSimilarity(String similarity) {
		this.similarity = similarity;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getRef_thres() {
		return ref_thres;
	}

	public void setRef_thres(String ref_thres) {
		this.ref_thres = ref_thres;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
