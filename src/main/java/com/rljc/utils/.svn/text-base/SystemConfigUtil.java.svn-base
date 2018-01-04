package com.rljc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.rljc.module.SystemConfig;

public class SystemConfigUtil {
	
	public static Map<String,SystemConfig> configMap = new HashMap<String,SystemConfig>();
	
	/**
	 * 获取系统最大折扣
	 * @return
	 */
	public static Double getSysemDiscount(){
		Double discount = null;
		try{
			if(configMap.containsKey("0908080203")){
				SystemConfig config = configMap.get("0908080203");
				if(config.getType() == 2 && StringUtils.isNotBlank(config.getContent()))
					discount = Double.parseDouble(config.getContent());
			}
		}catch(Exception e){
			
		}
		return discount;
	}
	
	/**
	 * 获取系统最大的免租期小时数
	 * @return
	 */
	public static Double getSysemfreePeriod(){
		Double freePeriod = null;
		try{
			if(configMap.containsKey("0908080202")){
				SystemConfig config = configMap.get("0908080202");
				if(config.getType() == 2 && StringUtils.isNotBlank(config.getContent()))
					freePeriod = Double.parseDouble(config.getContent());
			}
		}catch(Exception e){
			
		}
		return freePeriod;
	}
	
	/**
	 * 获取移动工位每天最大的预定人数
	 * @return
	 */
	public static Integer getMaxMobileOfficeNumber(){
		Integer maxMobile = 100;
		try{
			if(configMap.containsKey("0908080204")){
				SystemConfig config = configMap.get("0908080204");
				if(config.getType() == 1 && StringUtils.isNotBlank(config.getContent()))
					maxMobile = Integer.parseInt(config.getContent());
			}
		}catch(Exception e){
			maxMobile = 100;
		}
		return maxMobile;
	}
	
	/**
	 * 获取黑白打印的单价
	 * @param pageType 纸张类型 
	 * @return
	 */
	public static Double getPrintPrice(String pageType){
		Double printPrice = 0.1;
		pageType = (pageType != null && pageType =="A3") ? "A3" : "A4";
		try{
			if(configMap.containsKey(pageType+"_0908080205")){
				SystemConfig config = configMap.get(pageType+"_0908080205");
				if(config.getType() == 2 && StringUtils.isNotBlank(config.getContent()))
					printPrice = Double.parseDouble(config.getContent());
			}
		}catch(Exception e){
			printPrice = 0.1;
		}
		return printPrice;
	}
	
	/**
	 * 获取彩色打印的单价
     * @param pageType 纸张类型 
	 * @return
	 */
	public static Double getPrintColorPrice(String pageType){
		Double printPrice = 0.1;
		pageType = (pageType != null && pageType =="A3") ? "A3" : "A4";
		try{
			if(configMap.containsKey(pageType+"_0908080206")){
				SystemConfig config = configMap.get(pageType+"_0908080206");
				if(config.getType() == 2 && StringUtils.isNotBlank(config.getContent()))
					printPrice = Double.parseDouble(config.getContent());
			}
		}catch(Exception e){
			printPrice = 0.1;
		}
		return printPrice;
	}
	
	/**
	 * 获取黑白复印的单价
	 * @param pageType 纸张类型 
	 * @return
	 */
	public static Double getCopyPrice(String pageType){
		Double printPrice = 0.1;
		pageType = (pageType != null && pageType =="A3") ? "A3" : "A4";
		try{
			if(configMap.containsKey(pageType+"_0908080210")){
				SystemConfig config = configMap.get(pageType+"_0908080210");
				if(config.getType() == 2 && StringUtils.isNotBlank(config.getContent()))
					printPrice = Double.parseDouble(config.getContent());
			}
		}catch(Exception e){
			printPrice = 0.1;
		}
		return printPrice;
	}
	
	/**
	 * 获取彩色复印的单价
     * @param pageType 纸张类型 
	 * @return
	 */
	public static Double getColorCopyPrice(String pageType){
		Double printPrice = 0.1;
		pageType = (pageType != null && pageType =="A3") ? "A3" : "A4";
		try{
			if(configMap.containsKey(pageType+"_0908080211")){
				SystemConfig config = configMap.get(pageType+"_0908080211");
				if(config.getType() == 2 && StringUtils.isNotBlank(config.getContent()))
					printPrice = Double.parseDouble(config.getContent());
			}
		}catch(Exception e){
			printPrice = 0.1;
		}
		return printPrice;
	}
	
	/**
	 * 获取门禁开门的key
	 * @return
	 */
	public static String getRoomAccessKey(){
		try{
			if(configMap.containsKey("0908080207")){
				SystemConfig config = configMap.get("0908080207");
				if(config.getType() == 3 && StringUtils.isNotBlank(config.getContent()))
					return config.getContent();
			}
		}catch(Exception e){
			
		}
		return null;
	}
	
	/**
	 * 获取门禁开门的服务uri
	 * @return
	 */
	public static String getRoomAccessUri(){
		try{
			if(configMap.containsKey("0908080208")){
				SystemConfig config = configMap.get("0908080208");
				if(config.getType() == 3 && StringUtils.isNotBlank(config.getContent()))
					return config.getContent();
			}
		}catch(Exception e){
			
		}
		return null;
	}
	
	public static void init(List<SystemConfig> configs ){
		SystemConfigUtil.configMap.clear();
		for(SystemConfig config : configs){
			if(StringUtils.isNotBlank(config.getBarCode())){
				SystemConfigUtil.configMap.put(config.getBarCode(),config);
			}
		}
	}
	public static void main(String[] args) {
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse("2016-08-16");
			Date date1 = format.parse("2016-08-24");
			System.out.println((date1.getTime()-date.getTime()) / 24 / 60 /60 /1000 + 1);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
