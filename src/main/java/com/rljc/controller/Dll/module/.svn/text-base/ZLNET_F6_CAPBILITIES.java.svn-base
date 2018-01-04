package com.rljc.controller.Dll.module;

import com.sun.jna.Structure;

public class ZLNET_F6_CAPBILITIES extends Structure {
	
	public static class ByReference extends ZLNET_F6_CAPBILITIES implements Structure.ByReference {}  
	public static class ByValue extends ZLNET_F6_CAPBILITIES implements Structure.ByValue {} 
	
	/**
	 * 总能力。1表示支持新二代协议，0表示不支持 
	 */
	public int nEnable ;
	
	/**
	 * 设备能力。1表示支持，0表示不支持，见下表 
	 */
	public byte[] bCaps  = new byte[256];
	
	/**
	 * 预留 
	 */
	public int[] nRes = new int[1024] ;
	

}
