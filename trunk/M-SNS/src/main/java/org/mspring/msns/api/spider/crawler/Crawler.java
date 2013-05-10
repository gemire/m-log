/**
 * 
 */
package org.mspring.msns.api.spider.crawler;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.mspring.msns.api.spider.vo.Rule;

/**
 * @author Gao Youbo
 * @since 2013-3-8
 * @description
 * @TODO
 */
public interface Crawler {
    /**
     * 获取列表URL
     * 
     * @param doc
     * @param rule
     * @return
     */
    public List<String> getUrls(Document doc, Rule rule);

    /**
     * 获取标题
     * 
     * @param doc
     * @param rule
     * @return
     */
    public String getTitle(Document doc, Rule rule);

    /**
     * 获取内容
     * 
     * @param doc
     * @param rule
     * @return
     */
    public Element getContent(Document doc, Rule rule);
}
