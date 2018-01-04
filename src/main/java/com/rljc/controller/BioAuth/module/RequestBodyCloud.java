package com.rljc.controller.BioAuth.module;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestBodyCloud {
	
	@JsonProperty(value = "app_id")
	private String appId;
	
	@JsonProperty(value = "person_id")
	private String personId;
	
	public RequestBodyCloud(){
		
	}
	
	public RequestBodyCloud(String appId,String personId){
		this.personId = personId;
		this.appId = appId;
	}
	
	private ImageData image; 
	
	private ImageData image1;
	
	private ImageData image2;
	
	private Terminal terminal = new Terminal();;
	
	public class Terminal{
		
		private String sdk;
		
		private String type;
		
		private String system;
		
		public Terminal(){
			this.sdk = "o236";
			
			this.type = "pc";
			
			this.system = "JAVA";
		}

		public String getSdk() {
			return sdk;
		}

		public void setSdk(String sdk) {
			this.sdk = sdk;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getSystem() {
			return system;
		}

		public void setSystem(String system) {
			this.system = system;
		}
		
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
	
	public ImageData getImage() {
		return image;
	}

	public void setImage(ImageData image) {
		this.image = image;
	}

	public ImageData getImage1() {
		return image1;
	}

	public void setImage1(ImageData image1) {
		this.image1 = image1;
	}

	public ImageData getImage2() {
		return image2;
	}

	public void setImage2(ImageData image2) {
		this.image2 = image2;
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}
	
}
