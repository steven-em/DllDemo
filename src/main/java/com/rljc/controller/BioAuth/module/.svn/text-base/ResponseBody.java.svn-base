package com.rljc.controller.BioAuth.module;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseBody {
	
	private String detectID;
	
	private String time;
	
	private String errorcode;
	
	private String errormsg;
	
	private Image image;
	

	public class Image{
		private String name;
		private String similarity;
		@JsonProperty(value="content_type")
		private String contentType;
		private String data;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSimilarity() {
			return similarity;
		}
		public void setSimilarity(String similarity) {
			this.similarity = similarity;
		}
		public String getContentType() {
			return contentType;
		}
		public void setContentType(String contentType) {
			this.contentType = contentType;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
	}

	public String getDetectID() {
		return detectID;
	}


	public void setDetectID(String detectID) {
		this.detectID = detectID;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getErrorcode() {
		return errorcode;
	}


	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}


	public String getErrormsg() {
		return errormsg;
	}


	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}


	public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}
	
}
