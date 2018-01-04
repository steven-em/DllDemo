package com.rljc.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class RljcProperties {

	public static String uploadPath;
	
	public static String username;
	public static String password;
	
	
	@Value("#{configProperties['upload.path']}")
	public void setUploadPath(String uploadPath) {
		RljcProperties.uploadPath = uploadPath;
	}

	
	@Value("#{configProperties['jdbc.username']}")
	public void setUsername(String username) {
		RljcProperties.username = username;
	}

	@Value("#{configProperties['jdbc.password']}")
	public  void setPassword(String password) {
		RljcProperties.password = password;
	}
	
	
}
