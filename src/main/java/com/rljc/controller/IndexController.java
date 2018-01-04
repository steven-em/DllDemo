package com.rljc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController { 
	

	
	@RequestMapping(value = { "/", "/index" })
	public String index(HttpServletRequest request) {
		return "redirect:/admin/index";
	}
	
	/**
	 * 检测人脸对比的结果
	 * @param base64 base64位的图片数据
	 * @return
	 */
	@RequestMapping(value = "checkFace")
	@ResponseBody
	public String checkFace(String base64){
		return null;
	}
	
	

}
