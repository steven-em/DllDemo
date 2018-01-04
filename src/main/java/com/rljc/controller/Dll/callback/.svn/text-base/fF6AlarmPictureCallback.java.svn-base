package com.rljc.controller.Dll.callback;

import com.rljc.controller.Dll.module.ZLNET_NEWF6_ALARM_PICTURE;
import com.sun.jna.Callback;
import com.sun.jna.NativeLong;

public interface fF6AlarmPictureCallback extends Callback {
	

	/**
	 * @param loginHandle 设备登陆句柄，ZLNET_Login、ZLNET_LoginEx的返回值 
	 * @param pAlarm  报警图片 
	 * @param dwUser  用户自定义数据，就是上面的dwUser 
	 * @return
	 */
	public void callback(NativeLong loginHandle, ZLNET_NEWF6_ALARM_PICTURE pAlarm,int dwUser);
}
