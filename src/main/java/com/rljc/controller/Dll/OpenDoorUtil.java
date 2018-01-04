package com.rljc.controller.Dll;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenDoorUtil {
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			try {
				sendHttp("GET", "http://192.168.0.61/cdor.cgi?open=0");
				System.out.println("-----------------------------"+(i+1));
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public  static String openDoor(){
		String response = sendHttp("GET", "http://192.168.0.61/cdor.cgi?open=0");
		return response;
	}
	
	public static String sendHttp(String httpType, String url){
		String result = "";
		HttpURLConnection conn = null;
		OutputStream writer = null;
		ByteArrayOutputStream baos = null;
		try {
			URL realUrl = new URL(url);
			conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setConnectTimeout(3000);
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 验证
			conn.setRequestProperty("Authorization", "Basic YWRtaW46ODg4ODg4");
			conn.setRequestMethod(httpType);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();
			int status = conn.getResponseCode();
//			System.err.println("**********************start**********************");
//			System.err.println("发送"+httpType+"请求："+url);
//			System.err.println("返回状态："+status); 
			if(status == 200){
				baos=new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				int len = 0;
				InputStream is = conn.getInputStream();
				while((len=is.read(buf))!=-1){
					baos.write(buf, 0, len);
				}
				result = baos.toString("utf8");
				//System.err.println("返回值："+result); 
				if(result.contains("error")){
					result = null;
				}
			} else {
				
			}
			//System.err.println("**********************end**********************");
		} catch (Exception e) {
			System.out.println(url+" 发送" + httpType + "请求出现异常！" + e.getMessage());
			e.printStackTrace();
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
	
}
