package com.rljc.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class HttpRequest {
	
	public static String result;
	public final static String GET = "GET";
	public final static String POST = "POST";
	public final static String PUT = "PUT";
	public final static String DELETE = "DELETE";
	
	public static void setProperty(HttpURLConnection conn,Boolean needAdmin){
		// 设置通用的请求属性
		conn.setRequestProperty("accept", "*/*");
		conn.setConnectTimeout(3000);
		conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
		conn.setRequestProperty("connection", "Keep-Alive");
		// 验证
		//conn.setRequestProperty("Authorization", "Basic bnhhZG1pbjoxMjM0NTY=");
		conn.setRequestProperty("Authorization", result);
		if(needAdmin){
			// 当前登录管理员Id
			Subject subject = SecurityUtils.getSubject();
			conn.setRequestProperty("x-admin-id", subject.getSession(true).getAttribute("appUserId")+"");
		} else {
			conn.setRequestProperty("x-admin-id", "1");
		}
	}
	
	public static String sendHttp(String httpType, String url, String param){
		String result = "";
		HttpURLConnection conn = null;
		OutputStream writer = null;
		ByteArrayOutputStream baos = null;
		try {
			URL realUrl = new URL(url);
			conn = (HttpURLConnection) realUrl.openConnection();
			conn.setRequestMethod(httpType);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			if(StringUtils.isNotEmpty(param)){
				writer = conn.getOutputStream();
				// 发送参数
				writer.write(param.getBytes("UTF-8"));
			}
			conn.connect();
			int status = conn.getResponseCode();
			System.err.println("**********************start**********************");
			System.err.println("发送"+httpType+"请求："+url);
			System.err.println("参数："+param); 
			System.err.println("返回状态："+status); 
			if(status == 200){
				baos=new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				int len = 0;
				InputStream is = conn.getInputStream();
				while((len=is.read(buf))!=-1){
					baos.write(buf, 0, len);
				}
				result = baos.toString("utf8");
				System.err.println("返回值："+result); 
				if(result.contains("error")){
					result = null;
				}
			} else {
				
			}
			System.err.println("**********************end**********************");
		} catch (Exception e) {
			System.out.println(url+" 发送" + httpType + "请求出现异常！" + e.getMessage());
			//e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			if (writer != null) {
		        try {
		        	writer.flush();
		        	writer.close();
		        } catch (IOException exception) {
		            exception.printStackTrace();
		        }
		    }
			if (baos != null) {
		        try {
		        	baos.flush();
		        	baos.close();
		        } catch (IOException exception) {
		            exception.printStackTrace();
		        }
		    }
		    if (conn != null) {
		    	conn.disconnect();
		    }
		}
		return result;
	}
	
	/**
	 * 向指定URL发送GET方法的请求
	 * @param url 发送请求的URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		if(StringUtils.isNotEmpty(param)){
			url += "?"+param;
		}
		return sendHttp(GET, url, null);
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * @param url 发送请求的 URL
	 * @param param 请求参数，json形式{xxx:xxx}。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		return sendHttp(POST, url, param);
	}
	
	/**
	 * 向指定 URL 发送PUT方法的请求
	 * @param url 发送请求的 URL
	 * @param param 请求参数，json形式{xxx:xxx}。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPutByJson(String url, String param) {
		return sendHttp(PUT, url, param);
	}

	
	/**
	 * 向指定URL发送GET方法的请求
	 * @param url 发送请求的URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendPut(String url, String param) {
		if(StringUtils.isNotEmpty(param)){
			url += "?"+param;
		}
		return sendHttp(PUT, url, null);
	}
	
	
	/**
	 * 向指定 URL 发送DELETE方法的请求
	 * @param url 发送请求的 URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendDelete(String url, String param) {
		if(StringUtils.isNotEmpty(param)){
			url += "?"+param;
		}
		return sendHttp(DELETE, url, null);
	}
	
}
