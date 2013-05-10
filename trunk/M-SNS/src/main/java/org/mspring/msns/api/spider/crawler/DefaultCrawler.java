/**
 * 
 */
package org.mspring.msns.api.spider.crawler;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mspring.msns.api.spider.vo.Rule;

/**
 * @author Gao Youbo
 * @since 2013-3-8
 * @description
 * @TODO
 */
public class DefaultCrawler implements Crawler {

    @Override
    public List<String> getUrls(Document doc, Rule rule) {
        // TODO Auto-generated method stub
        List<String> ret = new ArrayList<String>();
        Elements elements = doc.select(rule.getListRule());
        if (elements != null && elements.size() > 0) {
            String url = "";
            for (Element ele : elements) {
                String tagname = ele.tagName();
                if ("a".equalsIgnoreCase(tagname)) {
                    url = ele.attr("href");
                    url = ele.absUrl(url);
                } else {
                    Elements a_elements = ele.select("a");
                    if (a_elements != null && a_elements.size() > 0) {
                        url = a_elements.get(0).absUrl("href");
                    }
                }
                ret.add(url);
            }
        }
        return ret;
    }

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
