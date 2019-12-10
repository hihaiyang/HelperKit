package cn.one.java.kit;

/**
 * 身份证号
 *
 * @author yanghy
 */
public class SfzhKit {

	private static final int[] NUMBERS = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
	private static final String[] LETTERS = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };

	/**
	 * 验证字符串是否为空
	 *
	 * @param str
	 * @return true空
	 */
	private static boolean isNull(String str) {
		if (null == str || str.replaceAll(" |　", "").length() < 1 || str.toLowerCase().equals("null")) {
			return true;
		}
		return false;
	}

	/**
	 * 身份证转换
	 * <br/>15位转18位
	 * <br/>18位转15位
	 *
	 * @param sfzh
	 * @return
	 */
	public static String convert(String sfzh) {
		if (null == sfzh || (sfzh.trim().length() != 18 && sfzh.trim().length() != 15)) {
			return sfzh;
		}
		if (sfzh.length() == 15) {
			int i, j, s = 0;
			sfzh = sfzh.substring(0, 6) + "19" + sfzh.substring(6);
			for (i = 0; i < sfzh.length(); i++) {
				j = Integer.parseInt(sfzh.substring(i, i + 1)) * NUMBERS[i];
				s = s + j;
			}
			s = s % 11;
			return sfzh + LETTERS[s];
		}
		else {
			return sfzh.substring(0, 6) + sfzh.substring(8, 17);
		}
	}

	/**
	 * 转换成15位身份证
	 *
	 * @param sfzh
	 * @return
	 */
	public static String to15Bit(String sfzh) {
		if (!isNull(sfzh) && sfzh.length() == 15) {
			return sfzh;
		}
		return convert(sfzh);
	}

	/**
	 * 转换成18位身份证
	 *
	 * @param sfzh
	 * @return
	 */
	public static String to18Bit(String sfzh) {
		if (!isNull(sfzh) && sfzh.length() == 18) {
			return sfzh;
		}
		return convert(sfzh);
	}

}
