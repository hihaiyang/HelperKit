package cn.one.java.db;

import cn.one.java.kit.EncoderKit;

/**
 * 数据库连接字符串信息
 *
 * @author yanghy
 */
public class DbSupport {
    // TODO Oracle
    /**
     *  获取oracle连接字符串
     *
     * @param ip 服务IP
     * @param port 端口
     * @param id ID或服务名 示例:ID:orcl SERVER_NAME:/orcl
     * @return {String} jdbc:oracle:this:@ip:port:orcl
     */
    public static String getOracleUrl(String ip, String port, String id) {
        return "jdbc:oracle:thin:@".concat(ip).concat(":").concat(port).concat(id);
    }

    // TODO MySQL
    /**
     * 获取mysql连接字符串 端口默认3306字符编码默认UTF-8
     *
     * @param ip
     *            服务器IP
     * @param project
     *            数据库名称
     * @return {String}
     */
    public static String getDefaultMySqlUrl(String ip, String project) {
        return "jdbc:mysql://".concat(ip).concat(":3306").concat("/")
                .concat(project).concat("?characterEncoding=UTF-8")
                .concat("&zeroDateTimeBehavior=convertToNull");
    }

    /**
     * 获取mysql连接字符串 端口默认3306字符编码默认UTF-8
     *
     * @param ip
     *            服务器IP
     * @param project
     *            数据库名称
     * @param user
     *            登录用户
     * @param password
     *            登录密码
     * @return {String}
     */
    public static String getDefaultMySqlUrl(String ip, String project, String user, String password) {
        return getDefaultMySqlUrl(ip, "3306", project, user, password, EncoderKit.Charset.UTF8.toString());
    }

    /**
     * 获取MySQL连接字符串
     * @param uri jdbc:mysql://127.0.0.1:3306/pro
     * @param user
     * @param password
     * @return {String}
     */
    public static String getDefaultMySqlUrl(String uri, String user, String password) {
        StringBuffer sb = new StringBuffer();
        sb.append(uri);
        sb.append("?user=").append(user);
        sb.append("&password=").append(password);
        sb.append("&useUnicode=true");
        sb.append("&characterEncoding=UTF-8");
        return sb.toString();
    }

    /**
     * 获取mysql连接字符串
     *
     * @param ip
     *            服务器IP
     * @param port
     *            端口
     * @param project
     *            数据库名称
     * @param user
     *            登录用户
     * @param password
     *            登录密码
     * @param encode
     *            编码方式
     * @return {String}
     */
    public static String getDefaultMySqlUrl(String ip, String port, String project, String user, String password, String encode) {
        StringBuffer sb = new StringBuffer();
        sb.append("jdbc:mysql://");
        sb.append(ip).append(":").append(port).append("/").append(project);
        sb.append("?user=").append(user);
        sb.append("&password=").append(password);
        sb.append("&useUnicode=true");
        sb.append("&characterEncoding=").append(encode);
        return sb.toString();
    }

    // TODO SqlServer
    /**
     * 获取sqlserver连接字符串
     *
     * @param host
     *            服务器IP或主机
     * @param port
     *            端口
     * @param db
     *            数据库名称
     * @return {String}
     */
    public static String getDefaultSqlServerUrl(String host, String port, String db) {
        StringBuffer sb = new StringBuffer();
        sb.append("jdbc:sqlserver://" + host + ":" + port + "; DatabaseName=" + db);
        return sb.toString();
    }

    /**
     * 获取sqlserver连接字符串根据数据库实例名称 带端口 integratedSecurity=false
     *
     * @param host
     *            服务器IP或主机
     * @param port
     *            端口
     * @param instance
     *            数据库实例名
     * @param db
     *            数据库名称
     * @return {String}
     */
    public static String getSqlServerUrlByInstance(String host, String port, String instance, String db) {
        StringBuilder sb = new StringBuilder();
        sb.append("jdbc:sqlserver://" + host + ":" + port + "; instanceName=" + instance + "; DatabaseName=" + db);
        return sb.toString();
    }

    /**
     * 获取sqlserver连接字符串根据数据库实例名称 不带端口
     *
     * @param host
     *            服务器IP或主机
     * @param instance
     *            数据库实例名
     * @param db
     *            数据库名称
     * @return {String}
     */
    public static String getSqlServerUrlByInstance(String host, String instance, String db) {
        StringBuilder sb = new StringBuilder();
        sb.append("jdbc:sqlserver://" + host + "; instanceName=" + instance + "; DatabaseName=" + db);
        return sb.toString();
    }

    // TODO Postgresql
    /**
     * 获取postgresql连接字符串
     *
     * @param host
     *            服务器IP
     * @param port
     *            端口
     * @param project
     *            数据库名称
     * @return {String}
     */
    public static String getDefaultPostgresql(String host, String port, String project) {
        StringBuffer sb = new StringBuffer();
        sb.append("jdbc:postgresql:").append(host).append(":").append(host).append("/").append(project);
        return sb.toString();
    }
}
