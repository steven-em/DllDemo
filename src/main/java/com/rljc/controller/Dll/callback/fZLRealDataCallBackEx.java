package com.rljc.controller.Dll.callback;

import com.sun.jna.Callback;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;

public interface fZLRealDataCallBackEx extends Callback {
	
	/**
	 * @param lRealHandle 实时监视句柄，ZLNET_RealPlay等接口的返回值 
	 * @param dwDataType 回调出来的数据类型，见下表：
	 * @param pBuffer 回调数据，根据数据类型的不同，每次回调不同长度的数据。除类型0外，其他数据类型都是按帧回调，每次回调一帧数据 
	 * @param dwBufSize
	 * @param param
	 * @param dwUser
	 */
	public void callback(NativeLong lRealHandle, int dwDataType,
			Pointer pBuffer,int dwBufSize,
			Pointer param,NativeLong dwUser);
}
