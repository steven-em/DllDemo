package com.rljc.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

public class ZkUserFilter extends UserFilter {

	@Override
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		String url = "/"+req.getServletPath()+"/";
        String loginUrl = getLoginUrl();
        if(url.contains("/admin/")){
        	loginUrl = "/admin/login.jsp";
        } else if(url.contains("/wap/")){
        	loginUrl = "/wap/login.jsp";
        }
        WebUtils.issueRedirect(request, response, loginUrl);
    }

	
}