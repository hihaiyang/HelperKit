package cn.hy.java.kit;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Properties文件读取
 * 
 * @author yanghy
 */
public class PropKit {

	private PropKit() {
	}

	/** 已读文件存储 */
	private static Map<String, Properties> map = new HashMap<String, Properties>();

	/**
	 * 读取Properties文件
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public static Properties getProp(String fileName) {
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = PropKit.class.getClassLoader().getResourceAsStream(fileName);
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			CloseKit.close(is);
		}
		return p;
	}
	
	/**
	 * 读取Properties文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param charsetName
	 *            编码名称
	 * @return
	 */
	public static Properties getProp(String fileName, String charsetName) {
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = PropKit.class.getClassLoader().getResourceAsStream(fileName);
			p.load(new InputStreamReader(is, charsetName));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			CloseKit.close(is);
		}
		return p;
	}

	/**
	 * 读取Properties文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param key
	 *            key
	 * @return value
	 */
	public static String getPropStr(String fileName, String key) {
		if (null == fileName || "".equals(fileName)) {
			return null;
		}
		Properties p = null;
		if (map.containsKey(fileName)) {
			p = map.get(fileName);
		} else {
			p = getProp(fileName);
		}
		return p.getProperty(key);
	}
}
