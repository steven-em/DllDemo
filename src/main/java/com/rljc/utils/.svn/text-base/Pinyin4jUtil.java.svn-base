package com.rljc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pinyin4jUtil {
	
	private static Logger	log = LoggerFactory.getLogger(Pinyin4jUtil.class); 
	
	// 字母Z使用了两个标签，这里有２７个值
	// i, u, v都不做声母, 跟随前面的字母
	private static char[] chartable = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈',
			'击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌', '塌',
			'挖', '昔', '压', '匝', '座' };
	private static char[] alphatableb = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z' };
	private static char[] alphatables = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y', 'z' };
	private static int[] table = new int[27]; // 初始化
	{
		for (int i = 0; i < 27; ++i) {
			table[i] = gbValue(chartable[i]);
		}
	}
	
	
	// 将汉字转换为全拼
	public static String getPingYin(String src) {
		log.info("需要转换成拼音的值：" + src);
		char[] t1 = null;
		String t4 = null;
		// 判断需要输入的值是否为空
		if(StringUtils.isNotBlank(src)){
			t4 = "";
			t1 = src.toCharArray();
			String[] t2 = new String[t1.length];
			HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
	
			t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			t3.setVCharType(HanyuPinyinVCharType.WITH_V);
			int t0 = t1.length;
			try {
				for (int i = 0; i < t0; i++) {
					// 判断是否为汉字字符
					if (java.lang.Character.toString(t1[i]).matches(
							"[\\u4E00-\\u9FA5]+")) {
						t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
						t4 += t2[0];
					} else
						t4 += java.lang.Character.toString(t1[i]);
				}
				log.info("-------------------------------------最终转换成拼音的值：" + t4);
				return t4;
			} catch (BadHanyuPinyinOutputFormatCombination e1) {
				e1.printStackTrace();
				log.error("-------------------------------------转换拼音出错：",e1);
			}
		}
		return t4;
	}

	// 返回中文的首字母
	public static String getPinYinHeadChar(String str) {

		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}

	// 将字符串转移为ASCII码
	public static String getCnASCII(String cnStr) {
		StringBuffer strBuf = new StringBuffer();
		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
		}
		return strBuf.toString();
	}

	// 根据一个包含汉字的字符串返回第一个汉字拼音首字母的字符串
	public static String String2AlphaFirst(String SourceStr, String type) {
		String Result = "";
		try {
			System.out.println("sourceStr："+SourceStr);
			Result += Char2Alpha(SourceStr.charAt(0), type);
		} catch (Exception e) {
			Result = "";
		}
		System.out.println("result："+Result);
		return Result;
	}
	
	public static char Char2Alpha(char ch, String type) {
		if (ch >= 'a' && ch <= 'z')
			return (char) (ch - 'a' + 'A');// 为了按字母排序先返回大写字母
		// return ch;
		if (ch >= 'A' && ch <= 'Z')
			return ch;

		int gb = gbValue(ch);
		if (gb < table[0])
			return '0';

		int i;
		for (i = 0; i < 26; ++i) {
			if (match(i, gb))
				break;
		}

		if (i >= 26) {
			return '0';
		} else {
			if (StringUtils.equals("b",type)) {// 大写
				return alphatableb[i];
			} else {// 小写
				return alphatables[i];
			}
		}
	}
	
	private static boolean match(int i, int gb) {
		if (gb < table[i])
			return false;
		int j = i + 1;

		// 字母Z使用了两个标签
		while (j < 26 && (table[j] == table[i]))
			++j;
		if (j == 26)
			return gb <= table[j];
		else
			return gb < table[j];
	}

	// 取出汉字的编码
	private static int gbValue(char ch) {
		String str = new String();
		str += ch;
		try {
			byte[] bytes = str.getBytes("GBK");
			if (bytes.length < 2)
				return 0;
			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 
	 * 根据首字母排序
	 * @author wuwh 
	 * @param list
	 * @return
	 *
	 */
	public static List sort(List list) {
		List retList = new ArrayList();
		List arraylist = new ArrayList();
		String[] alphatableb = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
				"V", "W", "X", "Y", "Z" };
		for (String a : alphatableb) {
			Map map = new HashMap();
			for (int i = 0; i < list.size(); i++) {// 为了排序都返回大写字母
				System.out.println(a);
				if (StringUtils.equals(a,String2AlphaFirst(list.get(i).toString(), "b"))) {
					arraylist.add(list.get(i));
				}
			}
			if(null != arraylist && arraylist.size() >0){
				map.put(a, arraylist);
				arraylist = new ArrayList();
				retList.add(map);
			}
		}
		return retList;
	}
}
