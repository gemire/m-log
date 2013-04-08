/**
 * 
 */
package org.mspring.mlog.api.spider.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.mspring.mlog.api.spider.vo.Rule;

/**
 * @author Gao Youbo
 * @since 2013-3-8
 * @description
 * @TODO
 */
public interface Crawler {
    public String getTitle(Document doc, Rule rule);

    public Element getContent(Document doc, Rule rule);
}
