/**
 * 
 */
package org.mspring.platform.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * @author Gao Youbo
 * @since 2012-11-15
 * @Description
 * @TODO
 */
public class JdbcUtils {
    private static final Logger log = Logger.getLogger(JdbcUtils.class);

    public static void close(Connection con) {
        if (con != null) try {
            con.close();
        }
        catch (SQLException ex) {
            log.warn("Could not close JDBC Connection", ex);
        }
    }

    public static void close(Statement stmt) {
        if (stmt != null) try {
            stmt.close();
        }
        catch (SQLException ex) {
            log.warn("Could not close JDBC Statement", ex);
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) try {
            rs.close();
        }
        catch (SQLException ex) {
            log.warn("Could not close JDBC ResultSet", ex);
        }
    }

    public static void close(Statement stmt, ResultSet rs) {
        close(null, stmt, rs);
    }

    public static void close(Connection con, Statement stmt) {
        close(con, stmt, null);
    }

    public static void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }

    /**
     * 执行SQL
     */
//    public static void execute(Connection con, String sql) {
//        try {
//            Statement stmt = con.createStatement();
//            stmt.execute(sql);
//            stmt.close();
//        }
//        catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}
