package com.rljc.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rljc.module.BaseEntity;
import com.rljc.service.BaseService;

import net.sf.json.JSONObject;

@Controller
public abstract class BaseController<T> implements InitializingBean{
	private static Logger log = LoggerFactory.getLogger(BaseController.class);
	private final static Integer PAGE_NUM = 1;
	private final static Integer PAGE_SIZE = 10;
	private final static String PREFIX = "admin/";
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	
	/**
	 * 表单（Command）类型
	 */
	protected Class<T>			entityClass;
	/**
	 * Controller主要操作的实体的类名，框架会自动使用范型判断，一般不需要设置；如果自动判断失败，可以配置或在初始化设置。
	 */
	protected String	entityClassName;
	/**
	 * 编辑页面使用的View，缺省是{lastPackageName}/{entityClassName}Form
	 */
	protected String	formView;
	/**
	 * 列表页面使用的View，缺省是{lastPackageName}/{entityClassName}List
	 */
	protected String	listItemView;
	public BaseService<T> baseService;
	
	/**
	 * 初始化Controller，ModelAttribute在方法上面表示请求该类的每个Controller前都会首先执行它
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void init(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		baseService.setRequest(request);
		try {
			String path = request.getServletPath();
			path = path.substring(1);
			String newPath = path;
			path = path.substring(0, path.indexOf("/"));
			if(!"/index".equals("/"+path)){
				String permissionUrls = (String) request.getSession().getAttribute("permissionUrls");
				if(StringUtils.isNotEmpty(permissionUrls)){
					String[] arrPath = newPath.split("/");
					String cPath = "";
					if(arrPath.length > 0 && arrPath.length < 4){
						cPath = arrPath[1];
						String value=""+request.getParameter("type");
						if(StringUtils.equals(arrPath[2],"auditSearch")){
							cPath = arrPath[1]+"/"+arrPath[2];
							permissionUrls = permissionUrls+"/basicInfo/auditSearch,";
						}else if(StringUtils.equals(arrPath[2],"add") || StringUtils.equals(arrPath[2],"aboutForm") || StringUtils.equals(arrPath[2],"aboutSave")){
							cPath = arrPath[1];
							permissionUrls = permissionUrls+"/"+cPath+",";
						}else if(StringUtils.isNotBlank(value) && !StringUtils.equals(value, "null") && !StringUtils.equals(value, "999")){
							cPath = cPath+"?type="+value;
						} 
						if(StringUtils.equals(arrPath[2], "audit")){
							cPath = arrPath[1]+"/"+arrPath[2];
							if(StringUtils.isNotBlank(value) && !StringUtils.equals(value, "null") && !StringUtils.equals(value, "999")){
								cPath = cPath+"?type="+value;
							} 
						}
						
						if(StringUtils.equals(arrPath[2], "searchAddress") || StringUtils.equals(arrPath[1], "operateLog")){
							cPath = "index";
							permissionUrls = permissionUrls+"/index,";
						}
					}else if(arrPath.length > 3){
						cPath = arrPath[1];
						if(StringUtils.equals(cPath, "basicInfo")){
							if(StringUtils.equals(arrPath[2], "detail")){
								cPath = arrPath[1]+"/"+arrPath[2];
								permissionUrls = permissionUrls+"/basicInfo/detail,";
							}else{
								cPath = arrPath[1]+"/"+arrPath[2];
								permissionUrls = permissionUrls+"/basicInfo/audit,";
							}
						}else if(StringUtils.equals(cPath, "artWork")){
							if(StringUtils.equals(arrPath[2], "detail")){
								cPath = arrPath[1]+"/"+arrPath[2];
								permissionUrls = permissionUrls+"/artWork/detail,";
							}else{
								cPath = arrPath[1]+"/"+arrPath[2];
								permissionUrls = permissionUrls+"/artWork/audit,";
							}
						}
					}
					if(!permissionUrls.contains("/"+cPath+",")){
						System.out.println("-------"+permissionUrls);
						System.out.println("-------"+cPath);
						System.out.println("-------"+request.getHeader("x-requested-with"));
						response.sendRedirect(request.getContextPath()+"/401.jsp");
					}
				}
			}
			String menuNavSession = request.getSession().getAttribute("mnav")+"";
			if(StringUtils.isEmpty(menuNavSession)){
				request.getSession().setAttribute("mnav", "-1");
			}
			String menuNav = request.getParameter("mnav");
			if(StringUtils.isNotEmpty(menuNav)){
				request.getSession().setAttribute("mnav", menuNav);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public BaseController() {
		entityClass = getSuperClassGenricType(getClass(), 0);
	}
	
	@RequestMapping("/index")
	public String index(T entity){
		preIndex();
		return PREFIX+entityClassName+"List";
	}
	
	@RequestMapping("/search")
	public String search(T entity){
		try {
			PageRequest pageRequest = buildPageRequest();
			Page<T> page = null;
			preSearch();
			page = baseService.findAll(entity, pageRequest);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PREFIX+"include/"+entityClassName+"Item";
		//return listItemView;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(@Validated T t, BindingResult result){
		request.getParameter("clause");
		JSONObject json = new JSONObject();
		try {
			preSave(t, result);
			if(result.hasErrors()){
				request.setAttribute(entityClassName, t);
				json.put("success", false);
			} else {
				Subject subject = SecurityUtils.getSubject();
				BaseEntity entity = (BaseEntity)t;
				if(entity.getId() == null){
					entity.setCreateDate(new Date());
					if(subject != null){
						String userId = subject.getSession(true).getAttribute("userId")+"";
						if(StringUtils.isNotBlank(userId)){
							entity.setCreatedBy(Integer.valueOf(userId));
						}
					}
				} else {
					if(entity.getCreatedBy() == null){
						T tt = baseService.findOne(entity.getId());
						BaseEntity temp = (BaseEntity)tt;
						entity.setCreateDate(temp.getCreateDate());
						entity.setCreatedBy(temp.getCreatedBy());
					}
					entity.setUpdateDate(new Date());
					if(subject != null){
						String userId = subject.getSession(true).getAttribute("userId")+"";
						if(StringUtils.isNotBlank(userId)){
							entity.setUpdateBy(Integer.valueOf(userId));
						}
					}
				}
				t = baseService.saveOrUpdate(t);
				json.put("success", true);
				postSave(t, json);
				entity = (BaseEntity)t;
				json.put(entityClassName, t);
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", false);
		}
		return json.toString();
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(){
		preEdit(null);
		return formView;
	}
	
	@RequestMapping({"/edit/{id}", "/add"})
	public String edit(@PathVariable Integer id){
		try {
			if(id != null){
				T t = this.baseService.findOne(id);
				preEdit(id);
				request.setAttribute(entityClassName, t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formView;
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable Integer id){
		JSONObject json = new JSONObject();
		try {
			baseService.delete(id);
			json.put("success", true);
		} catch (Exception e) {
			json.put("success", false);
			e.printStackTrace();
		}
		return json.toString();
	}
	@RequestMapping("/multiDelete")
	@ResponseBody
	public String multiDelete(){
		JSONObject json = new JSONObject();
		try {
			String[] ids = request.getParameterValues("multiIds");
			for (String id : ids) {
				baseService.delete(Integer.valueOf(id));
			}
			json.put("success", true);
		} catch (Exception e) {
			json.put("success", false);
			e.printStackTrace();
		}
		return json.toString();
	}
	
	/**
     * 创建分页请求.
     */
	public PageRequest buildPageRequest() {
		String pageNum = request.getParameter("pageNum");
		if(StringUtils.isEmpty(pageNum)){
			pageNum = PAGE_NUM + "";
		}
		String pageSize = request.getParameter("pageSize");
		if(StringUtils.isEmpty(pageSize)){
			pageSize = PAGE_SIZE + "";
		}
		String sortType = request.getParameter("sortType");
        Sort sort = null;
        if ("auto".equals(sortType)) {
        	sort = new Sort(Direction.ASC, "id");
        } else {
        	sort = new Sort(Direction.DESC, "id");
        }
        return new PageRequest(Integer.valueOf(pageNum) - 1, Integer.valueOf(pageSize), sort);
    }
	
	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(Class clazz, int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			log.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			log.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			log.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			return Object.class;
		}
		return (Class) params[index];
	}
	
	
	/**
	 * 子类可覆盖此方法，进行保存之前的绑定、验证、转换等处理。
	 */
	protected void preSave(T entity, BindingResult errors){}
	/**
	 * 子类可覆盖此方法，进行保存之后的绑定、验证、转换等处理。
	 */
	protected void postSave(T entity, JSONObject json){}
	/**
	 * 子类可覆盖此方法，进入index前
	 */
	protected void preIndex(){}
	/**
	 * 子类可覆盖此方法，查询列表前
	 */
	protected void preSearch(){}
	/**
	 * 子类可覆盖此方法，进行编辑之前
	 */
	protected void preEdit(Integer Id){}

	/**
	 * 子类可覆盖此方法，进行保存成功之后的一些操作
	 * 
	 * @param request
	 * @param entity
	 */
	protected void postSave (HttpServletRequest request, T entity){
		/*//保存操作成功的提示信息
		String msgKey = (isEntityNew(request)) ? "common.added"	: "common.updated";
		String message = getMessage(msgKey, new Object[] { getEntityTypeMessage(), getEntityName(entity) });
		saveMessage(request, message);*/
	}
	
	/**
	 * 令到Spring在完成设置后调用，并进一步调用各Controller的初始化方法
	 * @throws Exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			if (StringUtils.isEmpty(entityClassName))
			    entityClassName = entityClass.getSimpleName().substring(0, 1).toLowerCase()  + entityClass.getSimpleName().substring(1);
			if (StringUtils.isEmpty(listItemView))
				listItemView = entityClassName + "Item";
			if (StringUtils.isEmpty(formView))
			    formView = PREFIX+entityClassName + "Form";
		} catch (Exception e) {
			e.printStackTrace();
		}
		initService();
	}
	
	protected abstract void initService();
	
	/**
     * 完整的堆栈信息
     * @param e Exception
     * @return Full StackTrace
     */
    public String getStackTrace(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
}
