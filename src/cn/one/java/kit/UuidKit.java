package cn.one.java.kit;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * UUID
 * 
 * @author yanghy
 */
public class UuidKit {

	/**
	 * 封装JDK自带的UUID
	 * 
	 * @return 32位xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
	 */
	public static String getUuid() {
		return getUuid2().replace("-", "");
	}

	/**
	 * 封装JDK自带的UUID
	 * 
	 * @return 36位xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
	 */
	public static String getUuid2() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 日期格式ymdhmsS类型UUID
	 * 
	 * @return yyyyMMddHHmmssSSS
	 */
	public static String getYmdhmsS() {
		return new SimpleDateFormat("yyyyMMddHHmssS").format(new Date());
	}

	/**
	 * 随机UUID
	 * 
	 * @return 18位的随机数字
	 */
	public static String getRandomValue() {
		return String.valueOf(Math.abs(new SecureRandom().nextLong()));
	}

	/**
	 * 日期long类型
	 * 
	 * @return 示例:1486430910131
	 */
	public static String getCurrentTimeMillis() {
		return String.valueOf(System.currentTimeMillis());
	}

}
