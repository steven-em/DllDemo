package com.rljc.common;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 对整个应用环境的集中管理，包括初始化，刷新，和访问。
 * 
 */
public final class ContextUtil {
	private final static ContextUtil instance = new ContextUtil();
    private ServletContext servletContext;
    private ApplicationContext springContext;
    private ContextUtil() {}
	public final static ContextUtil getInstance() {
		return instance;
	}
	public static ServletContext getServletContext() {
		return getInstance().servletContext;
	}
	public static ApplicationContext getSpringContext() {
		return getInstance().springContext;
	}
	/**
	 * 整个系统的初始化工作
	 */
	public void init(ServletContext servletContext) {
		this.servletContext = servletContext;
		this.springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	}
	/**
	 * 系统清理工作
	 */
	public void cleanup() {
		this.servletContext = null;
		this.springContext = null;
	}
}
