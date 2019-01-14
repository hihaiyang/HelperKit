package cn.hy.java.db;

import java.sql.*;

/**
 * JDBC帮助工具类 提供了oracle、mysql、sqlserver、db2、postgresql获取连接方法
 *
 * @author yanghy
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

    /**
     * 打印结果集字符串
     *
     * @param rs
     */
    public static void printResultSet(ResultSet rs) {
        if (rs == null) {
            return;
        }
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
