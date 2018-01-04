package com.rljc.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rljc.service.RegisteredMenService;
import com.rljc.module.RegisteredMen;

@Controller
@RequestMapping(value="/admin/registeredMen")
public class RegisteredMenController extends BaseController<RegisteredMen> {

	@Autowired
	private RegisteredMenService registeredMenService;
	
	@Override
	protected void initService() {
		this.baseService = registeredMenService;
	}
}
