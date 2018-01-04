package com.rljc.job;

import java.util.concurrent.CountDownLatch;

import com.rljc.controller.BioAuth.module.ResponseBodyCloud;

public class TaskCountDownLatch extends CountDownLatch {

	private ResponseBodyCloud result;
	
	private Thread thread;
	
	private volatile boolean  isStop = false ; //表示是否已经停止
	
	public TaskCountDownLatch(Integer count,Thread thread){
		super(count);
		this.thread  = thread;
	}


	public synchronized ResponseBodyCloud getResult() {
		return result;
	}

	public void setResult(ResponseBodyCloud result) {
	    this.result = result;
	}
	
	/**
	 * 同步设置结果
	 * @param result
	 * @return
	 */
	public boolean setSynResult(ResponseBodyCloud result){
		if(this.result == null){
			this.result = result;
			return true;
		}
		return false;
	}

	public boolean isStop() {
		return isStop;
	}

	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}


	public Thread getThread() {
		return thread;
	}


	public void setThread(Thread thread) {
		this.thread = thread;
	}
	
}
