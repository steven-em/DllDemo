package com.rljc.controller.Dll.module;

import com.sun.jna.Structure;

public class ZLNET_ALARM_SUBSCRIBE extends Structure {

	public int bNeedVideo;
	
	public int nPicType;
	
	public int nLinkType;
	
	public int bByChannel;
	
	public int nChannel;
	
	public int bByType;
	
	public int nTypes;
	
	public int nVideoStreamType;
	
	public int[] nRes= new int[32];
	
}
