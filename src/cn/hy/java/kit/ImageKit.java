package cn.hy.java.kit;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 图片简单操作
 *
 * @author yanghy
 */
public class ImageKit {

    /**
     * 提取网络图片
     *
     * @param uri
     *            图片URI
     * @param outPath
     *            存储路径
     * @return
     */
    public static boolean catchNetImage(String uri, String outPath) {
        boolean is = false;
        URL url = null;
        HttpURLConnection httpConn = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        byte[] b = new byte[1024];
        int len = 0;
        try {
            File file = new File(outPath);
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            url = new URL(uri);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.connect();
            bis = new BufferedInputStream(httpConn.getInputStream());
            is = !(bis.available() == 0);
            if (is) {
                fos = new FileOutputStream(outPath);
                while ((len = bis.read(b)) != -1) {
                    fos.write(b, 0, len);
                }
                fos.flush();
                is = true;
            }
        }
        catch (Exception e) {
            is = false;
            e.printStackTrace();
        }
        finally {
            try {
                if (null != fos) {
                    fos.close();
                }
                if (null != bis) {
                    bis.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return is;
    }
}
