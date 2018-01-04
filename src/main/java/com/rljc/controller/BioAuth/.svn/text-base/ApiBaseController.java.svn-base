package com.rljc.controller.BioAuth;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ApiBaseController implements InitializingBean{
	private static Logger	log = LoggerFactory.getLogger("ApiBaseController"); 
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	 
	
	/**
	 * 初始化Controller，ModelAttribute在方法上面表示请求该类的每个Controller前都会首先执行它
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void init(HttpServletRequest request, HttpServletResponse response) {
		this.request = getRequest();
		this.response = getResponse();
		
		log.info("-----------APP请求开始-------------");
		log.info("request："+request.getServletPath()+"，token：" +request.getHeader("token")+"，User-Agent："+request.getHeader("User-Agent")+"，remoteAddr："+getRemoteAddress(request));
	}
	/**
	 * 获取客户端的IP
	 */
	public static String getRemoteAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	public  String beanToJson(Object obj){
		String responseBody = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
			//非空
			mapper.setSerializationInclusion(Include.NON_NULL); 
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			mapper.setDateFormat(format);  
			responseBody = mapper.writeValueAsString(obj);
			log.info("-----------APP请求结束------------/n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return responseBody;
	}
	
	/**
	 * 将json字符串转换为对象
	 * @param jsonString
	 * @param prototype
	 * @return
	 */
	public  <T> T jsonToBean(String json, Class<T> prototype) {
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	    	//log.info("-----------APP请求json: "+json);
	    	return (T) objectMapper.readValue(json, prototype);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
	}
	
	/**
     * 完整的堆栈信息
     * @param e Exception
     * @return Full StackTrace
     */
    public static String getStackTrace(Exception e) {
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
    
    public HttpServletRequest getRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	public HttpServletResponse getResponse(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
	}
	
	
	
	
	/**
	 * 将json字符串转换为对象 第三方UUID
	 * @param jsonString
	 * @return
	 */
	public  <T> T jsonToBeanForUU(String json, Class<T> prototype) {
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	    	return (T) objectMapper.readValue(json, prototype);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return null;
	}
	
	/**
	 * 根据 retCode 获取详细内容
	 * @param retCode
	 * @return
	 */
	public String getretCodeMsg(String retCode){
		if(retCode == null)
			return null;
		if(retCode.equals("1000")){
			return "注册不成功，账号已存在";
		}
		if(retCode.equals("1001")){
			return "密码不符合规则";
		}
		if(retCode.equals("1002")){
			return "验证码不正确";
		}
		if(retCode.equals("1003")){
			return "用户不存在";
		}
		if(retCode.equals("1004")){
			return "帐号密码不正确";
		}
		if(retCode.equals("1008")){
			return "登录帐号未激活";
		}
		if(retCode.equals("1009")){
			return "登录账号被冻结";
		}
		return null;
	}
	
	protected  String beanToJsonNoEncrypt(Object obj){
		String responseBody = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
			//非空
			mapper.setSerializationInclusion(Include.NON_NULL); 
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			mapper.setDateFormat(format);  
			responseBody = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return responseBody;
	}
}
