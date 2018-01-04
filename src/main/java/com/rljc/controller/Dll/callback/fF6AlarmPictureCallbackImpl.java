package com.rljc.controller.Dll.callback;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.rljc.controller.BioAuth.BioAuthAPI;
import com.rljc.controller.BioAuth.module.ResponseBodyCloud;
import com.rljc.controller.Dll.OpenDoorUtil;
import com.rljc.controller.Dll.module.ZLNET_NEWF6_ALARM_PICTURE;
import com.sun.jna.NativeLong;

import sun.misc.BASE64Encoder;

@Component(value="fF6AlarmPictureCallbackImpl")
public class fF6AlarmPictureCallbackImpl implements fF6AlarmPictureCallback {

	/*@Autowired
	private RegisteredMenService registeredMenService;*/
	
	@Override
	public void callback(NativeLong loginHandle, ZLNET_NEWF6_ALARM_PICTURE pAlarm, int dwUser) {
		// TODO Auto-generated method stub
		try{
			byte[] by = pAlarm.pBuf.getByteArray(0, pAlarm.nBufLen);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String time = format.format(new Date());
			saveToImgByStr(by,"D://images",time+"_test.jpg");
			BASE64Encoder encoder = new BASE64Encoder();
			File file = new File("D://images//sample");
			for(String fi : file.list()){
				ResponseBodyCloud  responseBody =  BioAuthAPI.compare(BioAuthAPI.getImageStr("D://images//sample//"+fi), BioAuthAPI.getImageStr("D://images//"+time+"_test.jpg"), new Date().getTime()+"");
				System.out.println("image1:"+"D://images//sample//"+fi+":image2="+"D://images//"+time+"_test.jpg");
				if(responseBody.getErrorcode().equals("0")){
					System.out.println(":similarity="+responseBody.getSimilarity());
					String response = OpenDoorUtil.openDoor();
					System.out.println(response);
					break;
				}else{
					System.out.println("对比失败");
				}
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 将捕获到的数据进行校验
	 * @param imgByte
	 * @param imgPath
	 * @param imgName
	 * @return
	 */
	private  int saveToImgByStr(byte[] imgByte,String imgPath,String imgName){
        int stateInt = 1;
        if(imgByte != null && imgByte.length > 0){
            try {
                 
                // 将字符串转换成二进制，用于显示图片  
                // 将上面生成的图片格式字符串 imgStr，还原成图片显示  
     
                InputStream in = new ByteArrayInputStream(imgByte);
     
                File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
                FileOutputStream fos=new FileOutputStream(file);
                   
                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = in.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }
                fos.flush();
                fos.close();
                in.close();
     
            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {
            }
        }
        return stateInt;
    }

}
