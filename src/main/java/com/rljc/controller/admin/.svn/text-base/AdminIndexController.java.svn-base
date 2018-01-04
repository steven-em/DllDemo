package com.rljc.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rljc.module.Menu;
import com.rljc.module.SystemUser;
import com.rljc.service.MenuService;
import com.rljc.service.UserService;

@Controller
public class AdminIndexController{
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"/admin/index","/admin","/admin/"})
	public ModelAndView index(HttpServletRequest request){
		Menu menu = new Menu();
		menu.setIsIndex(true);
		List<Menu> menuTree = menuService.findAll(menu);
		request.getSession().getServletContext().setAttribute("menuTree", menuTree);
		request.getSession().setAttribute("menuNav", "");
		String userId = request.getSession().getAttribute("userId")+"";
		SystemUser user = userService.findOne(Integer.valueOf(userId));
		String menus = ",";
		if(user.getRoleId() != null){
			if(user.getRoleId() != 1){
				menus += user.getRole().getMenus();
				request.getSession().setAttribute("menus", menus);
				String urls = new String();
				urls = RecursionMenu(menus, urls, menuTree);
				if(StringUtils.isNotEmpty(urls)){
					request.getSession().setAttribute("permissionUrls", urls);
				}
			}
			request.getSession().setAttribute("roleId", user.getRoleId());
		}
		//commentService.test();
		return new ModelAndView("admin/index");
	}
	
	

	public String RecursionMenu(String menus, String url, List<Menu> menuList){
		for (Menu m : menuList) {
			if(menus.contains(","+m.getId()+",")){
				if(StringUtils.isNotBlank(m.getUrl()) && !"/index".equals(m.getUrl())){
					url += m.getUrl().replaceAll("/index", "")+",";
				}
				//System.out.println("-------"+m.getUrl());
				if(m.getChildren() != null){
					url = RecursionMenu(menus,url, m.getChildren());
				}
			}
		}
		//System.out.println(url);
		return url;
	}
}
