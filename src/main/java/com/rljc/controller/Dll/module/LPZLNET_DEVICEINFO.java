package com.rljc.controller.Dll.module;

import com.sun.jna.Structure;

public class LPZLNET_DEVICEINFO extends Structure {
	
	 public byte[]  sSerialNumber = new byte[1024];
	 public byte  byAlarmInPortNum;
	 public byte   byAlarmOutPortNum;
	 public byte   byDiskNum;
	 public byte   byDVRType;
	 public byte   byChanNum;
	 
	 public static class ByReference extends LPZLNET_DEVICEINFO implements Structure.ByReference {}  
	 public static class ByValue extends LPZLNET_DEVICEINFO implements Structure.ByValue {}

}
