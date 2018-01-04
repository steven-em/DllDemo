package com.rljc.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rljc.module.SystemUser;
import com.rljc.service.UserService;

@Controller
public class AdminLoginController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/admin/login"}, method = RequestMethod.POST)
	public String doLogin(String username, String password, HttpServletRequest request){
		//String userAgent = request.getHeader("user-agent");
		if(StringUtils.isNotEmpty(username)){
			SystemUser user = userService.getUserByName(username);
			if(user != null){
				if(user.getStatus()){
					// 如果登陆成功
					if (user.getPassword().equals(password)) {
						UsernamePasswordToken token = new UsernamePasswordToken("<-admin->"+user.getUsername(), user.getPassword().toString());
						Subject subject = SecurityUtils.getSubject();
						subject.login(token);
						request.getSession().setAttribute("userId", user.getId());
						request.getSession().setAttribute("nickName", user.getNickname());
						if(StringUtils.isNotBlank(user.getStores())){
							List<Integer> list = new ArrayList<Integer>();
							for(String str : user.getStores().split(",")){
								list.add(Integer.parseInt(str));
							}
							request.getSession().setAttribute("userStoresList", list);
						}
						request.getSession().setAttribute("userRole", user.getRole());
					} else {
						return "redirect:/admin/login.jsp?msg=2";
					}
				} else {
					return "redirect:/admin/login.jsp?msg=3";
				}
			} else {
				return "redirect:/admin/login.jsp?msg=1";
			}
		}
		return "redirect:/admin/index";
	}
	
	/**
	 * 退出
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/admin/logout"}, method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/admin/login.jsp";
	}
}
