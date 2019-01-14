package cn.hy.java.kit;

/**
 * 编码类
 *
 * @author yanghy
 */
public class EncoderKit {
    public final static String[] encodes = { "ISO-8859-1", "UTF-8", "GBK", "GB2312" };

    /**
     * 默认集成编码
     *
     * @author Administrator
     */
    public enum Charset {
        ISO88591("ISO-8859-1")
        , UTF8("UTF-8")
        , GBK("GBK")
        , GB2312("GB2312")
        ;

        private String name;

        private Charset(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * 获取字符串编码格式
     *
     * @param str
     * @return
     */
    public static String getEncode(String str) {
        byte[] data = str.getBytes();
        byte[] b = null;
        a: for (int i = 0; i < encodes.length; i++) {
            try {
                b = str.getBytes(encodes[i]);
                if (b.length != data.length)
                    continue;
                for (int j = 0; j < b.length; j++) {
                    if (b[j] != data[j]) {
                        continue a;
                    }
                }
                return encodes[i];
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }

    /**
     * 将字符串转换为指定编码
     *
     * @param str
     * @param encode
     * @return
     */
    public static String transcoding(String str, String encode) {
        String df = encodes[0];
        try {
            String en = getEncode(str);
            if (null == en)
                en = df;
            return new String(str.getBytes(en), encode);
        } catch (Exception e) {
            return null;
        }
    }
}
