/**
 * 
 */
package org.mspring.msns.api.spider.utils;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;

/**
 * @author Gao Youbo
 * @since 2013-4-15
 * @description
 * @TODO
 */
public class DocumentUtils {
    public static Document getDocument(String url) {
        try {
            Connection con = ConnectionUtils.getConnection(url, 10000);
            con.header("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022)");
            Document doc = con.get();
            return doc;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
