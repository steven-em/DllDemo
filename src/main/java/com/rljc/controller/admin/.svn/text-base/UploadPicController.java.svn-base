package com.rljc.controller.admin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.rljc.common.RljcProperties;
import com.rljc.service.RegisteredMenService;

import net.sf.json.JSONSerializer;
import sun.misc.BASE64Encoder;


@Controller
@RequestMapping(value = "/admin/upd")
public class UploadPicController {
	    
	    @Autowired
		private RegisteredMenService registeredMenService;
	
	    @RequestMapping(value = "/pic", method=RequestMethod.POST)
	    @ResponseBody
	    public String uploadPic(@RequestParam(value = "image", required = false) CommonsMultipartFile image,Integer type,String ids) {
	        String responseString="";
	        String checkFlag="yes";
	        boolean checkTrue=true;
	        Map<String, String> map = new HashMap<String, String>();
	        try {
	        	String originFilename = image.getOriginalFilename();
	            //做图片验证操作
	            if(!Pattern.compile("jpg$|gif$|bmp$|jpge$|png$",Pattern.CASE_INSENSITIVE).matcher(originFilename).find()){ //文件格式
	                checkFlag="errorExt";
	                checkTrue=false;
	            } else if(Pattern.compile(";|%",Pattern.CASE_INSENSITIVE).matcher(originFilename).find()){
	            	checkFlag="errorPoint";
	                checkTrue=false;
	            }else if(image.getSize()/1048576>30){//3M
	                checkFlag="errorSize";
	                checkTrue=false;
	            }
	            if(checkTrue){
		            int idx = originFilename.lastIndexOf(".");
		            String ext = originFilename.substring(idx + 1);
		            String uuid = UUID.randomUUID().toString();
		            //File tempFile = new File(String.format("%s/%s.%s", "/home/b2c/apache-tomcat-8.0.23/webapps/qjs/upload/pic", uuid, ext));
		            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		            File tempFile = new File(String.format("%s/%s.%s", RljcProperties.uploadPath+"/media/pic/"+sf.format(new Date()), uuid, ext));
		            tempFile.mkdirs();
		            if(!tempFile.exists()){
		            	checkFlag = "error";
		            }
		            // 判断是否多张图片上传
		            
		            map.put("url", String.format("%s/%s.%s", "/media/pic/"+sf.format(new Date()), uuid, ext));
		            image.transferTo(tempFile);
	            }
	        } catch (Exception e) {
	        	checkFlag = "error";
	            e.printStackTrace();
	        }
	        map.put("checkFlag", checkFlag);
	        responseString = JSONSerializer.toJSON(map).toString();
	        return responseString;
	    }
	 
	    /**
	     * 上传word文件
	     * @param file
	     * @return
	     */
	    @RequestMapping(value = "/file", method=RequestMethod.POST)
	    @ResponseBody
	    public String uploadfile(@RequestParam(value = "file", required = false) CommonsMultipartFile file) {
	        String responseString="";
	        String checkFlag=null;
	        boolean checkTrue=true;
	        Map<String, String> map = new HashMap<String, String>();
	        try {
	        	String originFilename = file.getOriginalFilename();
	            //做图片验证操作
	            if(!Pattern.compile("doc$|docx$",Pattern.CASE_INSENSITIVE).matcher(originFilename).find()){ //文件格式
	                checkFlag="只能上传.doc或者.docx的文件";
	                checkTrue=false;
	            }
	            if(checkTrue){
		            int idx = originFilename.lastIndexOf(".");
		            String ext = originFilename.substring(idx + 1);
		            String uuid = UUID.randomUUID().toString();
		            //File tempFile = new File(String.format("%s/%s.%s", "/home/b2c/apache-tomcat-8.0.23/webapps/qjs/upload/pic", uuid, ext));
		            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		            File tempFile = new File(String.format("%s/%s.%s", RljcProperties.uploadPath+"/media/pic/"+sf.format(new Date()), uuid, ext));
		            tempFile.mkdirs();
		            if(!tempFile.exists()){
		            	checkFlag = "error";
		            }
		            // 判断是否多张图片上传
		            map.put("url", String.format("%s/%s.%s", "/media/pic/"+sf.format(new Date()), uuid, ext));
		            file.transferTo(tempFile);
		            map.put("fileName", originFilename);
	            }
	        } catch (Exception e) {
	        	checkFlag = "上传错误，联系管理员";
	            e.printStackTrace();
	        }
	        if(StringUtils.isNotBlank(checkFlag)){
	        	 map.put("checkFlag", checkFlag);
	        }
	        responseString = JSONSerializer.toJSON(map).toString();
	        return responseString;
	    }
	 
	    @RequestMapping(value = "/base64", method=RequestMethod.POST)
	    @ResponseBody
	    public String base64(@RequestParam(value = "image", required = false) CommonsMultipartFile image,Integer type,String ids) {
	        String responseString="";
	        String checkFlag="yes";
	        boolean checkTrue=true;
	        Map<String, String> map = new HashMap<String, String>();
	        try {
	        	String originFilename = image.getOriginalFilename();
	            //做图片验证操作
	            if(!Pattern.compile("jpg$|gif$|bmp$|jpge$|png$",Pattern.CASE_INSENSITIVE).matcher(originFilename).find()){ //文件格式
	                checkFlag="errorExt";
	                checkTrue=false;
	            } else if(Pattern.compile(";|%",Pattern.CASE_INSENSITIVE).matcher(originFilename).find()){
	            	checkFlag="errorPoint";
	                checkTrue=false;
	            }else if(image.getSize()/1048576>20){//2M
	                checkFlag="errorSize";
	                checkTrue=false;
	            }
	            if(checkTrue){
	            	BASE64Encoder encoder = new BASE64Encoder();
	            	String image1 = encoder.encode(image.getBytes());
	            	registeredMenService.batchRegister(image1);
	            	map.put("data", image1);
	            }
	        } catch (Exception e) {
	        	checkFlag = "error";
	            e.printStackTrace();
	        }
	        map.put("checkFlag", checkFlag);
	        responseString = JSONSerializer.toJSON(map).toString();
	        return responseString;
	    }
    
}
