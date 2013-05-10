/**
 * 
 */
package org.mspring.msns.api.spider.utils;

import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 * @author Gao Youbo
 * @since 1994-4-14
 * @Description
 * @TODO
 */
public class ConnectionUtils {
    /**
     * 获取Connection对象
     * 
     * @param url
     * @return
     */
    public static Connection getConnection(String url) {
        return getConnection(url, 3000);
    }

    /**
     * 获取Connection对象
     * 
     * @param url
     * @param timeout
     *            超时时间
     * @return
     */
    public static Connection getConnection(String url, int timeout) {
        Connection conn = Jsoup.connect(url).timeout(timeout);
        return conn;
    }

    /**
     * 获取Connection对象
     * 
     * @param url
     * @param timeout
     * @param headers
     *            头信息
     * @return
     */
    public static Connection getConnection(String url, int timeout, Map<String, String> headers) {
        Connection conn = Jsoup.connect(url).timeout(timeout);
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.header(entry.getKey(), entry.getValue());
            }
        }
        return conn;
    }
}
