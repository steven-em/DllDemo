package com.rljc.controller.admin;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rljc.module.SystemConfig;
import com.rljc.service.SystemConfigService;

@Controller
@RequestMapping(value="/admin/systemConfig")
public class SystemConfigController extends BaseController<SystemConfig> {

	@Autowired
	private SystemConfigService systemConfigService;
	
	@Override
	protected void initService() {
		this.baseService = systemConfigService;
	}
	
	@RequestMapping(value="/contentForm")
	public String contentForm(SystemConfig systemConfig){
		List<SystemConfig> list = systemConfigService.findAll(systemConfig);
		request.setAttribute("configs", list);
		return "/admin/include/configContentForm";
	}

	@Override
	protected void preSave(SystemConfig entity, BindingResult errors) {
		// TODO Auto-generated method stub
		if(entity.getType() == 5){
			entity.setContent(request.getParameter("details"));
		}
	}
	
	@RequestMapping(value="/saveBatch")
	@ResponseBody
	public String saveBatch(Integer[] id){
		JSONObject json = new JSONObject();
		try{
			json.put("code", 1);
			this.systemConfigService.updateBatch(id, request);
		}catch(Exception e){
			e.printStackTrace();
			json.put("code", 0);
		}
		return json.toString();
	}
}
