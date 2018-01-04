package com.rljc.controller.Dll.module;

import com.sun.jna.Structure;

public class ZLNET_DEVICEINFO extends Structure {
	
	 public byte[] sSerialNumber = new byte[48]; //序列号
	 public byte   byAlarmInPortNum; //DVR报警输入个数
	 public byte   byAlarmOutPortNum; //DVR报警输出个数
	 public byte   byDiskNum; //DVR硬盘个数
	 public byte   byDVRType; //DVR类型, 见枚举ZLNET_DEVICE_TYPE
	 public byte   byChanNum; //DVR通道个数
	 
	 public static class ByReference extends ZLNET_DEVICEINFO implements Structure.ByReference {}  
     public static class ByValue extends ZLNET_DEVICEINFO implements Structure.ByValue {} 

}
