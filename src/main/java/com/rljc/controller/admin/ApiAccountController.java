package com.rljc.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rljc.module.ApiAccount;
import com.rljc.service.ApiAccountService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/admin/apiAccount")
public class ApiAccountController extends BaseController<ApiAccount> {

	@Autowired
	private ApiAccountService apiAccountService;
	
	@Override
	protected void initService() {
		this.baseService = apiAccountService;
	}
	
	@RequestMapping(value="/overSave")
	@ResponseBody
	public String overSave(ApiAccount apiAccount){
		JSONObject json =  new JSONObject();
		try{
			json.put("success", true);
			this.apiAccountService.overSave(apiAccount);
		}catch(Exception e){
			json.put("success", false);
			json.put("msg", e.getMessage());
		}
		
		return json.toString();
	}
}
