package com.rljc.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rljc.module.AdPosition;
import com.rljc.service.AdPositionService;

@Controller
@RequestMapping(value="/admin/adPosition")
public class AdPositionController extends BaseController<AdPosition> {

	@Autowired
	private AdPositionService adPositionService;
	
	@Override
	protected void initService() {
		this.baseService = adPositionService;
	}
}
