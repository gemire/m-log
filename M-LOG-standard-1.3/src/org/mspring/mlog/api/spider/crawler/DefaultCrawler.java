/**
 * 
 */
package org.mspring.mlog.api.spider.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mspring.mlog.api.spider.vo.Rule;

/**
 * @author Gao Youbo
 * @since 2013-3-8
 * @description
 * @TODO
 */
public class DefaultCrawler implements Crawler {

    @Override
    public String getTitle(Document doc, Rule rule) {
        // TODO Auto-generated method stub
        Elements elements = doc.select(rule.getTitleRule());
        if (elements != null && elements.size() > 0) {
            return elements.get(0).text();
        }
        return null;
    }

    @Override
    public Element getContent(Document doc, Rule rule) {
        // TODO Auto-generated method stub
        Elements elements = doc.select(rule.getContentRule());
        if (elements != null && elements.size() > 0) {
            return elements.get(0);
        }
        return null;
    }
}
