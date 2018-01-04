package com.rljc.controller.Dll.callback;

import com.sun.jna.Callback;
import com.sun.jna.NativeLong;

public interface fZLDisConnect extends Callback {
	
	/**
	 * @param lLoginID 登陆句柄，ZLNET_Login、ZLNET_LoginEx的返回值
	 * @param pchDVRIP 设备IP 
	 * @param nDVRPort 端口 
	 * @param dwUser  用户自定义数据，就是上面的dwUser  
	 */
	public void callback(NativeLong lLoginID, String pchDVRIP,Long nDVRPort,int dwUser);
	
}
