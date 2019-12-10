package cn.one.java.kit;

import java.net.URLDecoder;

public class StringKit {

	/**
	 * 空字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if (str == null || str.trim().length() < 1 || str.toLowerCase().equals("null")) {
			return true;
		}
		return false;
	}

	/**
	 * 是否含有中文
	 * 
	 * @param word
	 * @return
	 */
	public static boolean hasChinese(String word) {
		if (isNull(word)) {
			return false;
		}
		boolean is = false;
		String regex = "^[\u4e00-\u9fa5]{1,}$";
		for (int i = 0; i < word.length(); i++) {
			is = word.substring(i, i + 1).matches(regex);
			if (is) {
				break;
			}
		}
		return is;
	}

	/**
	 * newString解码,解码ISO-8859-1转UTF-8
	 * 
	 * @param str
	 * @return
	 */
	public static String decodeNewString(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		}
		catch (Exception e) {
			return str;
		}
	}

	/**
	 * URLDecoder解码,解码UTF-8
	 * 
	 * @param str
	 * @return
	 */
	public static String decodeURLDecoder(String str) {
		try {
			return URLDecoder.decode(str, "UTF-8");
		}
		catch (Exception e) {
			return str;
		}
	}

}
