package com.rljc.controller.BioAuth;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.rljc.utils.Md5Encrypt;

public class SignatureUtil {
	
	
	
	public static String getSignature(String timestamp, String none) 
		throws Exception{
		String hmacSha1 = null;
		
		try{
			String message = URLEncoder.encode(timestamp.concat(none), "UTF-8");
			Mac mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec spec = new SecretKeySpec(BioAuthAPI.appkey.getBytes("UTF-8"), "HmacSHA1");
			mac.init(spec);
			byte[] byteHMAC = mac.doFinal(message.getBytes("UTF-8"));
			hmacSha1 = byteArray2String(byteHMAC);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return hmacSha1;
	}
	
	public static String byteArray2String(byte[] bs){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < bs.length; i++){
			String inTmp = null;
			String text = Integer.toHexString(bs[i]);
			if(text.length() >= 2){
				inTmp = text.substring(text.length() - 2, text.length());
			}else{
				char[] array = new char[2];
				Arrays.fill(array, 0, 2 - text.length(), '0');
				System.arraycopy(text.toCharArray(), 0, array, 2 - text.length(), text.length());
				inTmp = new String(array);
			}
			sb.append(inTmp);
		}
		return sb.toString().toUpperCase();
	}
	
	public static Boolean verifySign(String sign,String key,Map<String,String>paramMap){
        List<String> keys =  new ArrayList<String>();
        for(String str : paramMap.keySet()){
        	keys.add(str);
        }
        Collections.sort(keys);
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for(String str : keys){
        	if(first){
        		builder.append(str+"="+paramMap.get(str));
        		first = false;
        	}else{
        		builder.append("&"+str+"="+paramMap.get(str));
        	}
        }
        builder.append("&key="+key);
        String mySign = Md5Encrypt.md5(builder.toString());
        return mySign.toLowerCase().equals(sign);
	}
	
	
}
