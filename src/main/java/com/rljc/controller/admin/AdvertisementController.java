package com.rljc.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rljc.module.AdPosition;
import com.rljc.module.Advertisement;
import com.rljc.service.AdPositionService;
import com.rljc.service.AdvertisementService;

@Controller
@RequestMapping(value="/admin/advertisement")
public class AdvertisementController extends BaseController<Advertisement> {

	@Autowired
	private AdvertisementService advertisementService;
	
	@Autowired
	private AdPositionService adPositionService;
	
	protected void preEdit(Integer id){
		try {
			List<AdPosition> adPositionList = new ArrayList<AdPosition>();
			AdPosition adPosition = new AdPosition();
			adPositionList = this.adPositionService.findAll(adPosition);
			request.setAttribute("adPositionList", adPositionList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void initService() {
		this.baseService = advertisementService;
	}
	
	@Override
	protected void preIndex() {
		List<AdPosition> adPositionList = new ArrayList<AdPosition>();
		AdPosition adPosition = new AdPosition();
		adPositionList = this.adPositionService.findAll(adPosition);
		request.setAttribute("adPositionList", adPositionList);
	}
}
