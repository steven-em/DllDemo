package com.rljc.controller.Dll.callback;

import org.springframework.stereotype.Component;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;


@Component(value="fZLRealDataCallBackExImpl")
public class fZLRealDataCallBackExImpl implements fZLRealDataCallBackEx {

	@Override
	public void callback(NativeLong lRealHandle, int dwDataType, Pointer pBuffer,
			int dwBufSize, Pointer param,
			NativeLong dwUser) {
		// TODO Auto-generated method stub
		System.out.println(dwBufSize);
		System.out.println(121212);
		
	}

}
