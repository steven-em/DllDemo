package com.rljc.controller.Dll.module;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class ZLNET_NEWF6_ALARM_VIDEO extends Structure {

	public Pointer pBuf ;//视频流数据
	
	public int nBufLen ; //视频流大小
	
	public int bRealtime; //是否是实时数据
	
	/**
	 * 通道号 
	 */
	public int nChannel ;
	
	/**
	 * 事件索引
	 */
	public byte[] szIndex = new byte[32];
	
	/**
	 * 预留
	 */
	public int[] nRes = new int[62];
	
}
