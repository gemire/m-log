/**
 * 
 */
package org.mspring.mlog.api.spider;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 * @author Gao Youbo
 * @since 2013-3-8
 * @description
 * @TODO
 */
public class ConnectionFactory {
    public static Connection getConnection(String url) {
        return getConnection(url, 3000);
    }

    public static Connection getConnection(String url, int timeout) {
        Connection conn = Jsoup.connect(url).timeout(timeout);
        return conn;
    }
}
