package cn.one.java.db;

import cn.one.java.kit.IoClose;

import java.io.BufferedReader;
import java.io.StringWriter;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * JDBC帮助工具类 提供了oracle、mysql、sqlserver、db2、postgresql获取连接方法
 *
 * @author yanghy
 * @describe
 * 1、提供了oracle\mysql\sqlserver\db2\postgresql获取连接方法
 * <br/>2、Connection\PreparedStatement\ResultSet关闭
 */
public class JdbcKit {

    /** Orace Driver字符串 */
    public static final String DRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";

    /** MySql Driver字符串 */
    public static final String DRIVER_MYSQL = "org.gjt.mm.mysql.Driver";

    /** MySql5 Driver字符串 */
    public static final String DRIVER_MYSQL_5 = "com.mysql.jdbc.Driver";

    /** SqlServer Driver字符串 */
    public static final String DRIVER_SQLSERVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    /** DB2 Driver字符串 */
    public static final String DRIVER_DB2 = "com.ibm.db2.jdbc.app.DB2Driver";

    /** Postgresql Driver字符串 */
    public static final String DRIVER_POSTGRESQL = "org.postgresql.Driver";

    /**
     * 加载Oracle驱动
     *
     * @throws ClassNotFoundException
     */
    public static void loadOracleDriver() throws ClassNotFoundException {
        Class.forName(JdbcKit.DRIVER_ORACLE);
    }

    /**
     * 加载MySql驱动
     *
     * @throws ClassNotFoundException
     */
    public static void loadMySqlDriver() throws ClassNotFoundException {
        Class.forName(JdbcKit.DRIVER_MYSQL);
    }

    /**
     * 加载SqlServer驱动
     *
     * @throws ClassNotFoundException
     */
    public static void loadSqlServerDriver() throws ClassNotFoundException {
        Class.forName(JdbcKit.DRIVER_SQLSERVER);
    }

    /**
     * 加载Postgresql驱动
     *
     * @throws ClassNotFoundException
     */
    public static void loadPostgresqlDriver() throws ClassNotFoundException {
        Class.forName(DRIVER_POSTGRESQL);
    }

    public static Connection getOracleConn(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static Connection getMySqlConn(String url) throws SQLException {
        return DriverManager.getConnection(url);
    }

    /**
     * 关闭连接
     *
     * @param conn
     */
    public static void close(Connection conn) {
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭结果集
     *
     * @param rs
     */
    public static void close(ResultSet rs) {
        if (null != rs) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭PreparedStatement
     *
     * @param ps
     */
    public static void close(PreparedStatement ps) {
        if (null != ps) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭Statement
     *
     * @param statement
     */
    public static void close(Statement statement) {
        if (null != statement) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭连接信息
     *
     * @param conn
     * @param st
     * @param rs
     */
    public static void close(Connection conn, Statement st, ResultSet rs) {
        close(rs);
        close(st);
        close(conn);
    }

    /**
     * 关闭连接信息
     *
     * @param conn
     * @param ps
     * @param rs
     */
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        close(rs);
        close(ps);
        close(conn);
    }

    public static void close(Connection... conns) {
        if (null == conns) return;
        for (Connection conn : conns) {
            close(conn);
        }
    }

    public static void close(PreparedStatement... pss) {
        if (null == pss) return;
        for (PreparedStatement ps : pss) {
            close(ps);
        }
    }

    public static void close(ResultSet... rss) {
        if (null == rss) return;
        for (ResultSet rs : rss) {
            close(rs);
        }
    }

    /**
     * 获取ResultSet字段名称(有别名按别名返回)
     * @param rs
     * @return {String}
     */
    public static String getColumnLabels(ResultSet rs) {
        String cols = "";
        if (null == rs) return cols;
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i=1; i<columnCount; i++) {
                cols += "," + rsmd.getColumnLabel(i).toLowerCase();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return cols.replaceFirst(",", "");
    }

    /**
     * 获取ResultSet原始字段名称
     * @param rs
     * @return {String}
     */
    public static String getColumnNames(ResultSet rs) {
        String cols = "";
        if (null == rs) return cols;
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i=1; i<columnCount; i++) {
                cols += "," + rsmd.getColumnName(i).toLowerCase();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return cols.replaceFirst(",", "");
    }

    /**
     * ResultSet转Map
     * <br/>
     * 1、rs.next()
     * 2、map = resultToMap(rs)
     * @param rs
     * @return {Map}
     */
    public static Map<String, String> resultToMap(ResultSet rs) {
        Map<String, String> m = new HashMap<>();
        if (null == rs) return m;
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i=1; i<columnCount; i++) {
                String value = "";
                String columnTypeName = rsmd.getColumnTypeName(i);
                if (columnTypeName.equals("BLOB")) {
                    continue;
                } else if (columnTypeName.equals("CLOB")) {
                    BufferedReader br = null;
                    StringWriter sw = null;
                    try {
                        br = new BufferedReader(rs.getClob(i).getCharacterStream());
                        sw = new StringWriter();
                        int c;
                        while ((c=br.read()) != -1) {
                            sw.write(c);
                        }
                        value = sw.toString();
                    } catch (Exception e) {

                    } finally {
                        IoClose.close(br);
                        IoClose.close(sw);
                    }
                } else {
                    value = rs.getString(i);
                }
                m.put(rsmd.getColumnName(i).toLowerCase(), value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return m;
        }
    }

    /**
     * 打印输出
     *
     * @param rs
     */
    public static void printResultSet(ResultSet rs) {
        if (rs == null) return;
        try {
            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            StringBuffer b = new StringBuffer();
            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    b.append(meta.getColumnName(i) + "=");
                    b.append(rs.getString(i) + "/t");
                }
                b.append("/n");
            }
            System.out.print(b.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
