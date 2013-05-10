/**
 * 
 */
package org.mspring.msns.api.spider;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.dbcp.ConnectionFactory;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.mspring.msns.api.spider.crawler.DefaultCrawler;
import org.mspring.msns.api.spider.handler.impl.ContentImageHandler;
import org.mspring.msns.api.spider.utils.ConnectionUtils;
import org.mspring.msns.api.spider.vo.Rule;

/**
 * @author Gao Youbo
 * @since 2013-3-8
 * @description
 * @TODO
 */
public class SpiderTest {
    public static void main(String[] args) throws MalformedURLException, IOException {
        // new SpiderTest().test();
        new SpiderTest().testOSC();
    }

    public void test() throws MalformedURLException, IOException {
        Connection con = ConnectionUtils.getConnection("http://www.mspring.org/post/319.html", 10000);
        con.header("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022)");
        Document doc = con.get();
        Rule rule = getRule();

        Spider spilder = new Spider(new DefaultCrawler());
        spilder.addContentHandler(new ContentImageHandler());

        Element content = spilder.getContent(doc, rule);
        String a = content.html();
        System.out.println(a);
    }

    public Rule getRule() {
        Rule rule = new Rule();
        rule.setTitleRule(".post-entity .post-title");
        rule.setContentRule(".post-entity .post-content");
        return rule;
    }

    public void testOSC() throws IOException {
        Connection con = ConnectionUtils.getConnection("http://www.oschina.net/news/39480/db-engines-ranking-april", 10000);
        con.header("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022)");

        Document doc = con.get();
        Rule rule = getOSCRule();

        Spider spilder = new Spider(new DefaultCrawler());
        spilder.addContentHandler(new ContentImageHandler());

        Element content = spilder.getContent(doc, rule);
        String a = content.html();
        System.out.println(a);
    }

    public Rule getOSCRule() {
        Rule rule = new Rule();
        rule.setTitleRule(".NewsEntity .OSCTitle");
        rule.setContentRule(".NewsEntity .NewsContent");
        return rule;
    }
}
