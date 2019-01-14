package cn.hy.java.kit;

import java.io.*;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 常用户InputStream\OutputStream流close调用
 *
 * @author yanghy
 */
public class CloseKit {
    public static void close(FileInputStream fis) {
        try {
            if (null != fis) {
                fis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(FileOutputStream fos) {
        try {
            if (null != fos) {
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(ByteArrayOutputStream outputStream) {
        try {
            if (null != outputStream) {
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(ByteArrayInputStream inputStream) {
        try {
            if (null != inputStream) {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(InputStream is) {
        try {
            if (null != is) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(OutputStream os) {
        try {
            if (null != os) {
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(BufferedWriter bw) {
        try {
            if (null != bw) {
                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(InputStreamReader isr) {
        try {
            if (null != isr) {
                isr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(BufferedReader br) {
        try {
            if (null != br) {
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(PrintStream ps) {
        if (null != ps) {
            ps.close();
        }
    }

    public static void close(BufferedInputStream bis) {
        try {
            if (null != bis) {
                bis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(ZipOutputStream zos) {
        try {
            if (null != zos) {
                zos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(ZipInputStream zis) {
        try {
            if (null != zis) {
                zis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(Reader reader) {
        try {
            if (null != reader) {
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
