package com.rljc.controller.Dll.module;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class ZLNET_NEWF6_ALARM_PICTURE extends Structure {

	/*public static class ByReference extends ZLNET_NEWF6_ALARM_PICTURE implements Structure.ByReference {}  
	public static class ByValue extends ZLNET_NEWF6_ALARM_PICTURE implements Structure.ByValue {} 
	*/
	public Pointer pBuf ;//图片数据
	
	public int nBufLen ; //图片大小
	
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
	 * 抓图时间，在报警前多少秒。例如0表示报警图，5表示报警前5秒的图 
	 */
	public int nSnapTime;
	
	/**
	 * 枚举值 含义 
	 *	ZLNET_PT_NONE 无图片 
	 *	ZLNET_PT_SMALL 小图 
	 *	ZLNET_PT_BIG 大图 
	 *	ZLNET_PT_ORIGINAL 原图 
	 */
	public int nType;
	
	
	
	/**
	 * 图片编号，用于同一事件有多张图片的情况 
	 */
	public int nSequence;
	
	/**
	 * 本条报警的图片是否回调完成。TRUE：是，FALSE：否(此参数为TRUE时的报警图片可忽略) 
	 */
	public boolean bFinished;
	
	/**
	 * 预留
	 */
	public int[] nRes = new int[62];
	
	
}
