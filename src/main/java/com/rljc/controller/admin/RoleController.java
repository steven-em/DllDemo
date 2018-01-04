package com.rljc.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rljc.common.ZTreeBean;
import com.rljc.module.Role;
import com.rljc.service.RoleService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController<Role>{
	
	@Autowired
	private RoleService roleService;
	
	
	public void initZTree(Role rootrole, ZTreeBean rootZTree){
		if(rootrole.getChildren().size() > 0){
			for (Role role : rootrole.getChildren()) {
				ZTreeBean zTree = new ZTreeBean();
				zTree.setName(role.getRolename());
				zTree.setOpen(role.getChildren().size()>0);
				zTree.setIsParent(role.getChildren().size()>0);
				zTree.setId(role.getId()+"");
				zTree.putDiy("id", role.getId());
				zTree.putDiy("pId", role.getParentId());
				zTree.putDiy("rolePath", role.getRolePath());
				zTree.putDiy("status", role.getStatus());
				zTree.putDiy("version", role.getVersion());
				zTree.putDiy("menus", role.getMenus());
				if(role.getChildren().size() > 0){
					initZTree(role, zTree);
				}
				rootZTree.addChildren(zTree);
			}
		}
	}
	
	@RequestMapping("/getJsonRole")
	@ResponseBody
	public String getJsonRole(Role entity){
		String roleId = null;
		List<ZTreeBean> zTreeList;
		try {
			roleId = request.getParameter("roleId");
			entity.setParentId(entity.getId() == null ? -1 : entity.getId());
			List<Role> roleList = roleService.findAll(entity);
			
//			rootZTree = new ZTreeBean();
//			rootZTree.setName("系统角色");
//			rootZTree.setIsParent(roleList.size()>0);
//			rootZTree.setOpen(roleList.size()>0);
//			rootZTree.setId("-1");
//			rootZTree.putDiy("pId", "0");
//			rootZTree.putDiy("id", "-1");
			zTreeList = new ArrayList<ZTreeBean>();
			for (Role role : roleList) {
				ZTreeBean zTree = new ZTreeBean();
				zTree.setName(role.getRolename());
				zTree.setOpen(role.getChildren().size()>0);
				zTree.setIsParent(role.getChildren().size()>0);
				zTree.setId(role.getId()+"");
				zTree.putDiy("id", role.getId());
				zTree.putDiy("pId", role.getParentId());
				zTree.putDiy("rolePath", role.getRolePath());
				zTree.putDiy("status", role.getStatus());
				zTree.putDiy("version", role.getVersion());
				zTree.putDiy("menus", role.getMenus());
				initZTree(role, zTree);
				zTreeList.add(zTree);
			}
			JSONArray ja = JSONArray.fromObject(zTreeList);
			String result = ja.toString();
			if(StringUtils.isNotEmpty(roleId)){
				result = ja.toString();
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id){
		Role role = roleService.findOne(id);
		String rolePath = role.getRolePath();
		StringBuffer sb = new StringBuffer("系统角色");
		if(StringUtils.isNotEmpty(rolePath)){
			String[] ids = rolePath.split("\\.");
			for (String s : ids) {
				Role prole = roleService.findOne(Integer.valueOf(s));
				sb.append("》").append(prole.getRolename());
			}
		}
		role.setRolePathStr(sb.toString());
		request.setAttribute("role", role);
		return "/admin/roleForm";
	}
	
	@Override
	protected void preSave(Role role, BindingResult errors){
		if(role.getParentId() != null){
			Role parentRole = roleService.findOne(role.getParentId());
			role.setRolePath((parentRole.getRolePath()==null?"":parentRole.getRolePath())+role.getParentId()+".");
		}
	}
	
	@Override
	protected void initService() {
		this.baseService = roleService;
	}
	
}
