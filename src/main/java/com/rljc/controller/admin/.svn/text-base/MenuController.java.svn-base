package com.rljc.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rljc.common.ZTreeBean;
import com.rljc.module.Menu;
import com.rljc.module.Role;
import com.rljc.service.MenuService;
import com.rljc.service.RoleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/menu")
public class MenuController extends BaseController<Menu>{
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private RoleService roleService;
	
	public void initZTree(Menu rootMenu, ZTreeBean rootZTree, String menus){
		if(rootMenu.getChildren().size() > 0){
			for (Menu menu : rootMenu.getChildren()) {
				ZTreeBean zTree = new ZTreeBean();
				zTree.setName(menu.getTitle());
				zTree.setOpen(menu.getChildren().size()>0);
				zTree.setIsParent(menu.getChildren().size()>0);
				zTree.setId(menu.getId()+"");
				zTree.putDiy("id", menu.getId());
				zTree.putDiy("pId", menu.getParentId());
				zTree.putDiy("menuPath", menu.getMenuPath());
				zTree.putDiy("url", menu.getUrl());
				zTree.putDiy("status", menu.getStatus());
				zTree.putDiy("version", menu.getVersion());
				zTree.putDiy("sortOrder", menu.getSortOrder());
				if(menus.contains(","+menu.getId()+",")){
					zTree.setChecked(true);
				}
				if(menu.getChildren().size() > 0){
					initZTree(menu, zTree, menus);
				}
				rootZTree.addChildren(zTree);
			}
		}
	}
	
	/**
	 * 获取菜单树形列表
	 * @param entity
	 * @return
	 */
	@RequestMapping("/getJsonMenu")
	@ResponseBody
	public String getJsonMenu(Menu entity){
		String roleId = request.getParameter("roleId");
		String menus = ",";
		if(StringUtils.isNotEmpty(roleId)){
			Role role = roleService.findOne(Integer.valueOf(roleId));
			if(role.getId() != null){
				menus += role.getMenus();
			}
		}
		PageRequest pageRequest = buildPageRequest();
		entity.setParentId(entity.getId() == null ? -1 : entity.getId());
		Page<Menu> page = menuService.findAll(entity, pageRequest);
		List<Menu> menuList = page.getContent();
		ZTreeBean rootZTree = new ZTreeBean();
		rootZTree.setName("菜单根目录");
		rootZTree.setIsParent(menuList.size()>0);
		rootZTree.setOpen(menuList.size()>0);
		rootZTree.setId("-1");
		rootZTree.putDiy("pId", "0");
		rootZTree.putDiy("id", "-1");
		List<ZTreeBean> zTreeList = new ArrayList<ZTreeBean>();
		for (Menu menu : menuList) {
			ZTreeBean zTree = new ZTreeBean();
			zTree.setName(menu.getTitle());
			zTree.setOpen(menu.getChildren().size()>0);
			zTree.setIsParent(menu.getChildren().size()>0);
			zTree.setId(menu.getId()+"");
			zTree.putDiy("id", menu.getId());
			zTree.putDiy("pId", menu.getParentId());
			zTree.putDiy("menuPath", menu.getMenuPath());
			zTree.putDiy("url", menu.getUrl());
			zTree.putDiy("status", menu.getStatus());
			zTree.putDiy("version", menu.getVersion());
			zTree.putDiy("sortOrder", menu.getSortOrder());
			if(menus.contains(","+menu.getId()+",")){
				zTree.setChecked(true);
			}
			initZTree(menu, zTree, menus);
			zTreeList.add(zTree);
			rootZTree.addChildren(zTree);
		}
		JSONArray ja = JSONArray.fromObject(zTreeList);
		JSONObject jo = JSONObject.fromObject(rootZTree);
		String result = jo.toString();
		if(StringUtils.isNotEmpty(roleId)){
			result = ja.toString();
		}
		return result;
	}
	
	/**
	 * 修改菜单信息
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id){
		Menu menu = menuService.findOne(id);
		String menuPath = menu.getMenuPath();
		StringBuffer sb = new StringBuffer("菜单根目录");
		if(StringUtils.isNotEmpty(menuPath)){
			String[] ids = menuPath.split("\\.");
			for (String s : ids) {
				Menu pmenu = menuService.findOne(Integer.valueOf(s));
				sb.append("》").append(pmenu.getTitle());
			}
		}
		menu.setMenuPathStr(sb.toString());
		request.setAttribute("menu", menu);
		return "admin/menuForm";
	}
	@Override
	protected void preSave(Menu menu, BindingResult errors){
		if(menu.getId() == null){
			if(menu.getParentId() != null){
				menu.setMenuPath(menu.getParentId()+".");
			}
		}
	}
	@Override
	public PageRequest buildPageRequest() {
        Sort sort = new Sort(Direction.ASC, "sortOrder");
        PageRequest pr = super.buildPageRequest();
        return new PageRequest(pr.getPageNumber(), 30, sort);
    }

	@Override
	protected void initService() {
		this.baseService = menuService;
	}
}
