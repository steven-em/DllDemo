package com.rljc.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rljc.module.Role;
import com.rljc.module.SystemUser;
import com.rljc.service.RoleService;
import com.rljc.service.UserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController<SystemUser>{
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Override
	protected void initService() {
		this.baseService = userService;
	}
	@Override
	protected void preEdit(Integer id){
		String userEdit = request.getParameter("userEdit");
		if(StringUtils.isNotBlank(userEdit)){
			this.formView = "admin/include/editUserForm";
		} else {
			this.formView = "admin/systemUserForm";
			PageRequest pageRequest = super.buildPageRequest();
			Page<Role> rolePage = roleService.findAll(new Role(), pageRequest);
			request.setAttribute("roleList", rolePage.getContent());
		}
		//构造区域对象树
	}
	
	@Override
	protected void preSave(SystemUser user, BindingResult errors){
		if(user.getId() != null){
			String setPwd = request.getParameter("setPwd");
			if(StringUtils.isEmpty(setPwd)){
				SystemUser temp = this.userService.findOne(user.getId());
				user.setPassword(temp.getPassword());
			}
		} else {
			
		}
		/*String roleId = request.getParameter("roleId");
		if(StringUtils.isNotEmpty(roleId)){
			Role role = new Role();
			role.setId(Integer.valueOf(roleId));
			//user.setRole(role);
		}*/
	}
	
	@RequestMapping("/updateStatus/{status}")
	@ResponseBody
	public String updateStatus(@PathVariable("status") Boolean status){
		JSONObject json = new JSONObject();
		try {
			String[] multiIds = request.getParameterValues("multiIds");
			List<Integer> ids = new ArrayList<Integer>();
			for (String id : multiIds) {
				ids.add(Integer.valueOf(id));
			}
			this.userService.updateStatus(status, ids);
			json.put("success", true);
		} catch (Exception e) {
			json.put("success", false);
			e.printStackTrace();
		}
		return json.toString();
	}
	
	@RequestMapping("/updatePassword")
	@ResponseBody
	public String updatePassword(Integer id, String password){
		JSONObject json = new JSONObject();
		try {
			this.userService.updatePassword(id, password);
			json.put("success", true);
		} catch (Exception e) {
			json.put("success", false);
			e.printStackTrace();
		}
		return json.toString();
	}
	
	@RequestMapping("/loadUser")
	public String loadUserInfo(SystemUser systemUser){
		PageRequest pageRequest = buildPageRequest();
		Page<SystemUser> page = null;
		systemUser.setRoleId(-1);
		page = userService.findAll(systemUser, pageRequest);
		request.setAttribute("page", page);
		return "admin/include/loadUserItem";
	}
	
	public static void main(String[] args) {
		System.out.println((0.1*10 + 0.2*10)/10);
	}
}
