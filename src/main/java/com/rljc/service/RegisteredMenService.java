package com.rljc.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rljc.controller.BioAuth.BioAuthAPI;
import com.rljc.controller.BioAuth.SignatureUtil;
import com.rljc.controller.BioAuth.module.ResponseBodyCloud;
import com.rljc.dao.RegisteredMenDao;
import com.rljc.job.JobTask;
import com.rljc.job.TaskCountDownLatch;
import com.rljc.module.ApiAccount;
import com.rljc.module.RegisteredMen;


@Component
@Transactional
public class RegisteredMenService extends BaseService<RegisteredMen> {
	
	private static Logger log = LoggerFactory.getLogger(RegisteredMenService.class);
	
	@Autowired
	private RegisteredMenDao registeredMenDao;
	@Autowired
	private ApiAccountService apiAccountService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Override
	protected void initDao() {
		this.baseDao = registeredMenDao;
	}
	
	@Override
	public Specification<RegisteredMen> buildSpecification(final RegisteredMen registeredMen){
		return new Specification<RegisteredMen>(){
			public Predicate toPredicate(Root<RegisteredMen> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				if(registeredMen.getApiAccountId() != null){
					predicate.add(cb.equal(root.get("apiAccountId").as(Integer.class), registeredMen.getApiAccountId()));
				}
				if(registeredMen.getApiAccount() != null && StringUtils.isNotBlank(registeredMen.getApiAccount().getAppId())){
					Join<RegisteredMen,ApiAccount> apiJoin = root.join(root.getModel().getSingularAttribute("apiAccount", ApiAccount.class), JoinType.LEFT);
					predicate.add(cb.equal(apiJoin.get("appId").as(String.class), registeredMen.getApiAccount().getAppId()));
				}
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
	/**
	 * 注册详细信心
	 * @param appId = 账号信息
	 * @param sign  = 签名
	 * @param image  = 图片数据
	 * @throws Exception
	 */
	public Integer register(String appId,String sign,String image)throws Exception{
		ApiAccount param = new ApiAccount();
		param.setAppId(appId);
		ApiAccount apiAccount = apiAccountService.findOne(param);
		if(apiAccount == null){
			throw new Exception("-1=账号不存在");
		}
		if(apiAccount.getStatus() == null || apiAccount.getStatus() == 0){
			throw new Exception("-2=账号已禁用");
		}
		HashMap<String,String>paramMap = new HashMap<String,String>();
		paramMap.put("appId", appId);
		paramMap.put("image", image);
		if(!SignatureUtil.verifySign(sign, apiAccount.getAppkey(), paramMap)){
			throw new Exception("-3=签名错误");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		ResponseBodyCloud response = BioAuthAPI.detect(image, format.format(new Date()));
		if(!response.getErrorcode().equals("0")){
			throw new Exception("-6=注册图片无法检测到人脸");
		}
		RegisteredMen registeredMen = new RegisteredMen();
		registeredMen.setImage1(image);
		registeredMen.setApiAccountId(apiAccount.getId());
		if(StringUtils.isBlank(registeredMen.getImage1() )){
			throw new Exception("-4=图片数据必填");
		}
		this.saveOrUpdate(registeredMen);
		return registeredMen.getId();
	}
	
	public void batchRegister(String image)throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		ResponseBodyCloud response = BioAuthAPI.detect(image, format.format(new Date()));
		if(!response.getErrorcode().equals("0")){
			throw new Exception("-6=注册图片无法检测到人脸");
		}
		RegisteredMen registeredMen = new RegisteredMen();
		registeredMen.setImage1(image);
		registeredMen.setApiAccountId(1);
		if(StringUtils.isBlank(registeredMen.getImage1() )){
			throw new Exception("-4=图片数据必填");
		}
		this.saveOrUpdate(registeredMen);
	}
	
	/**
	 * 注册详细信心
	 * @param appId = 账号信息
	 * @param sign  = 签名
	 * @param image  = 图片数据
	 * @throws Exception
	 */
	public ResponseBodyCloud compare(String appId,String sign,String image)throws Exception{
		ApiAccount param = new ApiAccount();
		param.setAppId(appId);
		ApiAccount apiAccount = apiAccountService.findOne(param);
		if(apiAccount == null){
			throw new Exception("-1=账号不存在");
		}
		if(apiAccount.getStatus() == null || apiAccount.getStatus() == 0){
			throw new Exception("-2=账号已禁用");
		}
		HashMap<String,String>paramMap = new HashMap<String,String>();
		paramMap.put("appId", appId);
		paramMap.put("image", image);
		if(!SignatureUtil.verifySign(sign, apiAccount.getAppkey(), paramMap)){
			throw new Exception("-3=签名错误");
		}
		if(StringUtils.isBlank(image )){
			throw new Exception("-4=图片数据必填");
		}
		RegisteredMen paramAccount = new RegisteredMen();
		paramAccount.setApiAccountId(apiAccount.getId());
		Integer count = this.count(paramAccount).intValue();
		if(count == 0){
			return null;
		}
		log.info("待对比的记录："+count);
		Integer pageSize = 3;
		Integer pageNum = (count % pageSize == 0 ? 0 : 1)  + count / pageSize ;
		log.info("待对比的页数："+pageNum);
		TaskCountDownLatch latch = null;
		Long beginTime = System.currentTimeMillis();
		if(pageNum <= 40){ //总共小于40个任务
			latch = new TaskCountDownLatch(pageNum,Thread.currentThread());
			Integer interval = 1 ; 
			for(int i = 0;i< pageNum; i++){
				JobTask task = new JobTask(i*interval,interval,pageSize,this,paramAccount,image,latch);
				taskExecutor.submit(task);
			}
		}else{ //最多只开启40个线程
		    latch = new TaskCountDownLatch(40,Thread.currentThread());
			Integer interval = (pageNum / 40),gap = pageNum % 40  ;
			
			for(int i = 0;i< 39; i++){
				JobTask task = new JobTask(i*interval,interval+( gap > 0 ? 1 : 0),pageSize,this,paramAccount,image,latch);
				taskExecutor.execute(task);
				gap --;
			}
			JobTask task = new JobTask(39*interval,interval+( gap > 0 ? 1 : 0),pageSize,this,paramAccount,image,latch);
			taskExecutor.submit(task);
		}
		try{
			latch.await(); //等待latch返回
		}catch(InterruptedException e){
			log.error("线程被终端："+e);
		}
		log.info("对比使用的时间："+(System.currentTimeMillis() - beginTime ));
		if(latch.getResult() != null){
			return latch.getResult();
		}
		return null;
	}
	
	/**
	 * 注册详细信心
	 * @param appId = 账号信息
	 * @param sign  = 签名
	 * @param image  = 图片数据
	 * @throws Exception
	 */
	public ResponseBodyCloud test()throws Exception{
		RegisteredMen paramAccount = new RegisteredMen();
		paramAccount.setApiAccountId(1);
		List<RegisteredMen> lists = this.findAll(paramAccount);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		for(RegisteredMen entity : lists){
			ResponseBodyCloud response = BioAuthAPI.compare(entity.getImage1(), entity.getImage1(),format.format(new Date()) + entity.getId() );
			if(response.getErrorcode().equals("0")){
				response.setId(String.valueOf(entity.getId()));
				return response;
			}
		}
		return null;
	}
	
	public List<RegisteredMen> queryListByOffSet(Integer appId,Integer offset,Integer limit){
		return this.registeredMenDao.queryListByOffSet(appId, offset, limit);
	}
}
