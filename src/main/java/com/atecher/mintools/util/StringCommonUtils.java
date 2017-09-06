package com.atecher.mintools.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 描述：字符串工具类
 * @作者    mark.han
 * @邮箱    hongwei.han@qq.com
 * @日期    2014-7-29
 * @版本    v1.0
 */
public class StringCommonUtils {
	static String regEx = " ";
	private static final String src = "abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";

	public static String stringFilter(String str, String regEx)
			throws PatternSyntaxException {
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
	
	public static String trimByRegEx(String source, String regEx){
		if(source!=null){
			return source.replaceAll(regEx, "");
		}else{
			return source;
		}
	}
	
	public static String invCharfilter(String str) {

		if (str != null && str.startsWith(regEx)) {
			return str.replace(regEx, "");
		} else {
			return str;
		}
	}

	public static String invCharConvert(String str) {

		if (str != null) {
			return str.replaceAll(regEx, "&nbsp;");
		} else {
			return str;
		}
	}
	/**
	 * 描述：生成指定位数的随机字符串
	 * @作者 mark.han
	 * @日期 2014-7-29
	 * @邮箱 hongwei.han@qq.com
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		Random random = new Random();
		StringBuffer sf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);
			sf.append(src.charAt(number));
		}
		return sf.toString();
	}
	
	public static String generateActivationCode(){
		return String.format("%06d", new Random().nextInt(999999));
	}

}
