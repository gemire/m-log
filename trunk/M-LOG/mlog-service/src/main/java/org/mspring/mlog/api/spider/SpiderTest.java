/**
 * 
 */
package org.mspring.mlog.api.spider;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.mspring.mlog.api.spider.crawler.DefaultCrawler;
import org.mspring.mlog.api.spider.handler.impl.ContentImageHandler;
import org.mspring.mlog.api.spider.vo.Rule;

/**
 * @author Gao Youbo
 * @since 2013-3-8
 * @description
 * @TODO
 */
public class SpiderTest {
    public static void main(String[] args) throws MalformedURLException, IOException {
        new SpiderTest().test();
    }

    public void test() throws MalformedURLException, IOException {
        Connection con = ConnectionFactory.getConnection("http://www.mspring.org/post/319.html", 10000);
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
}
