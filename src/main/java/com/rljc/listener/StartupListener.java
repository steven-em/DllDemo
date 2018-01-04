package com.rljc.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.rljc.common.ContextUtil;
import com.rljc.controller.Dll.api.ZLNETSDK;
import com.rljc.controller.Dll.callback.fF6AlarmPictureCallback;
import com.rljc.controller.Dll.callback.fZLDisConnect;
import com.rljc.controller.Dll.callback.fZLRealDataCallBackEx;
import com.rljc.controller.Dll.module.LPZLNET_DEVICEINFO;
import com.rljc.controller.Dll.module.ZLNET_ALARM_SUBSCRIBE;
import com.sun.jna.NativeLong;

public class StartupListener extends ContextLoaderListener implements ServletContextListener{
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ContextUtil.getInstance().cleanup();
		super.contextDestroyed(event);
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		try {
			super.contextInitialized(event);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException("Spring context failed to startup.", e);
		}
		ContextUtil.getInstance().init(servletContext);
		
		ZLNETSDK.INSTANCE.ZLNET_Init(impl, 0);
		LPZLNET_DEVICEINFO.ByReference lpDeviceInfo = new LPZLNET_DEVICEINFO.ByReference();
		Integer error = 0 ;
		NativeLong lLoginID = ZLNETSDK.INSTANCE.ZLNET_LoginEx("192.168.0.86", 8000, "admin", "123456", 0, null, lpDeviceInfo, error);
		if(lLoginID != null && lLoginID.longValue() > 0){
			ZLNET_ALARM_SUBSCRIBE param = new ZLNET_ALARM_SUBSCRIBE();
			param.nPicType = 1;
			ZLNETSDK.INSTANCE.ZLNET_F6_ListenAlarm(lLoginID, param);
			fF6AlarmPictureCallback callback = WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("fF6AlarmPictureCallbackImpl", fF6AlarmPictureCallback.class);
			ZLNETSDK.INSTANCE.ZLNET_F6_SetAlarmPictureCallback(lLoginID, callback, 0);
			/*NativeLong lhandle = ZLNETSDK.INSTANCE.ZLNET_RealPlayEx(lLoginID, 0, null, 0);
			fZLRealDataCallBackEx callback1 = WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("fZLRealDataCallBackExImpl", fZLRealDataCallBackEx.class);
			int i = ZLNETSDK.INSTANCE.ZLNET_SetRealDataCallBackEx(lhandle, callback1, 0, 0);
			System.out.println(lhandle.longValue());*/
		}
	}
	
	
	public static class fZLDisConnectImpl implements fZLDisConnect{

		@Override
		public void callback(NativeLong lLoginID, String pchDVRIP, Long nDVRPort, int dwUser) {
			// TODO Auto-generated method stub
			System.out.println("lLoginID="+lLoginID);
			System.out.println("pchDVRIP="+pchDVRIP);
		}
		
	}
	
	public static fZLDisConnect impl = new fZLDisConnectImpl();
	
}
