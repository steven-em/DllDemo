package com.rljc.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;

import com.rljc.controller.BioAuth.BioAuthAPI;
import com.rljc.controller.BioAuth.module.ResponseBodyCloud;
import com.rljc.module.RegisteredMen;
import com.rljc.service.RegisteredMenService;

public class JobTask implements Runnable {

	private static Logger log = LoggerFactory.getLogger(RegisteredMenService.class);
	
	private Integer begin;
	
	private Integer num;
	
	private Integer pageSize;
	
	private RegisteredMenService service;
	
	private String image; //待对比图像数据
	
	private RegisteredMen param;
	
	private TaskCountDownLatch latch; 
	
	public JobTask(Integer begin,Integer num,Integer pageSize,RegisteredMenService service,RegisteredMen param,String image, TaskCountDownLatch latch){
		this.begin = begin;
		this.num = num;
		this.pageSize = pageSize;
		this.service = service;
		this.param  = param;
		this.image = image;
		this.latch = latch;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			PageRequest pageale = new PageRequest(begin,this.pageSize);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			for(int i = 0 ; i < num && !latch.isStop();i++ ){
				log.info("开始对比的页数："+pageale.getPageNumber());
				long time = System.currentTimeMillis();
				List<RegisteredMen> list = this.service.queryListByOffSet(param.getApiAccountId(), pageale.getOffset(), this.pageSize);
				long end = System.currentTimeMillis();
				log.info("查询数据使用的时间："+(end-time));
				for(RegisteredMen entity : list)
				{
					if(latch.isStop()){
						break;
					}
					try{
						ResponseBodyCloud response = BioAuthAPI.compare(image, entity.getImage1(),format.format(new Date()) + entity.getId() );
						if(response.getErrorcode().equals("0")){
							response.setId(String.valueOf(entity.getId()));
							response.setImage(entity.getImage1());
							if(latch.setSynResult(response)){
								latch.setStop(true);
								latch.getThread().interrupt();
								break;
							}
						}
					}catch(Exception e){
						
					}
				}
				pageale = (PageRequest)pageale.next();
			}
		}finally{
			latch.countDown();
		}
	}

	public Integer getBegin() {
		return begin;
	}

	public void setBegin(Integer begin) {
		this.begin = begin;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public RegisteredMenService getService() {
		return service;
	}

	public void setService(RegisteredMenService service) {
		this.service = service;
	}
	
	public RegisteredMen getParam() {
		return param;
	}

	public void setParam(RegisteredMen param) {
		this.param = param;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public TaskCountDownLatch getLatch() {
		return latch;
	}

	public void setLatch(TaskCountDownLatch latch) {
		this.latch = latch;
	}
	
}
