package com.rljc.controller.BioAuth;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rljc.controller.BioAuth.module.ResponseBodyCloud;
import com.rljc.service.RegisteredMenService;

import net.sf.json.JSONObject;

@Controller	
@RequestMapping(value="/api")
public class BioAuthControllerApi extends ApiBaseController {
	
	private static Logger log = LoggerFactory.getLogger(BioAuthControllerApi.class);
	
	@Autowired
	private RegisteredMenService registeredMenService;
	
	/**
	 * 注册详细信息appId =1 image =1
	 * @return
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public String register(@RequestBody String json,String appId,String sign,String image){
		JSONObject jsonObject = new JSONObject(); 
		try{
			if(StringUtils.isBlank(appId)){
				JSONObject jsonObj = JSONObject.fromObject(json);
				appId = jsonObj.getString("appId");
				sign = jsonObj.getString("sign");
				image = jsonObj.getString("image");
			}
			Integer id = this.registeredMenService.register(appId, sign, image);
			jsonObject.put("success", true);
			jsonObject.put("id", id);
			jsonObject.put("errCode", 0);
			jsonObject.put("errMsg", "操作成功");
		}catch(Exception e){
			jsonObject.put("success", false);
			String msg = e.getMessage();
			if(msg.contains("=")){
				jsonObject.put("errCode", msg.split("=")[0]);
				jsonObject.put("errMsg", msg.split("=")[1]);
			}else{
				jsonObject.put("errCode", -11);
				jsonObject.put("errMsg", "系统错误");
			}
		}
		String str = jsonObject.toString();
		System.out.println(str);
		return str;
	}
	
	@RequestMapping(value="/compare", method=RequestMethod.POST)
	@ResponseBody
	public String compare(@RequestBody String json,String appId,String sign,String image){
		JSONObject jsonObject = new JSONObject();
		try{
			if(StringUtils.isBlank(appId)){
				JSONObject jsonObj = JSONObject.fromObject(json);
				appId = jsonObj.getString("appId");
				sign = jsonObj.getString("sign");
				image = jsonObj.getString("image");
			}
			ResponseBodyCloud response = this.registeredMenService.compare(appId, sign, image);
			if(response == null){
				throw new Exception("-5=对比失败");
			}
			if(!response.getErrorcode().equals("0")){
				throw new Exception("-5=对比失败");
			}
			jsonObject.put("success", true);
			jsonObject.put("errCode", 0);
			jsonObject.put("errMsg", response.getErrormsg());
			jsonObject.put("similarity", response.getSimilarity());
			jsonObject.put("ref_thres", response.getRef_thres());
			jsonObject.put("image", response.getImage());
		}catch(Exception e){
			jsonObject.put("success", false);
			String msg = e.getMessage();
			if(msg.contains("=")){
				jsonObject.put("errCode", msg.split("=")[0]);
				jsonObject.put("errMsg", msg.split("=")[1]);
			}else{
				jsonObject.put("errCode", -11);
				jsonObject.put("errMsg", "系统错误");
			}
		}
		String str = jsonObject.toString();
		System.out.println(str);
		return str;
	}
	
	
}
