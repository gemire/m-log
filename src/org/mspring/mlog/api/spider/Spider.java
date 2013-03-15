/**
 * 
 */
package org.mspring.mlog.api.spider;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.mspring.mlog.api.spider.crawler.Crawler;
import org.mspring.mlog.api.spider.handler.ContentHandler;
import org.mspring.mlog.api.spider.handler.TitleHandler;
import org.mspring.mlog.api.spider.vo.Rule;

/**
 * @author Gao Youbo
 * @since 2013-3-8
 * @description
 * @TODO
 */
public class Spider {
    private static final Logger log = Logger.getLogger(Spider.class);
    private Crawler crawler;
    private List<TitleHandler> titleHandlers;
    private List<ContentHandler> contentHandlers;

    public Crawler getCrawler() {
        return crawler;
    }

    public void setCrawler(Crawler crawler) {
        this.crawler = crawler;
    }

    public List<TitleHandler> getTitleHandlers() {
        return titleHandlers;
    }

    public void setTitleHandlers(List<TitleHandler> titleHandlers) {
        this.titleHandlers = titleHandlers;
    }

    public List<ContentHandler> getContentHandlers() {
        return contentHandlers;
    }

    public void setContentHandlers(List<ContentHandler> contentHandlers) {
        this.contentHandlers = contentHandlers;
    }

    /**
     * 
     */
    public Spider() {
        // TODO Auto-generated constructor stub
    }

    public Spider(Crawler crawler) {
        super();
        this.crawler = crawler;
    }

    public Spider(Crawler crawler, List<TitleHandler> titleHandlers, List<ContentHandler> contentHandlers) {
        super();
        this.crawler = crawler;
        this.titleHandlers = titleHandlers;
        this.contentHandlers = contentHandlers;
    }

    public void addContentHandler(ContentHandler contentHandler) {
        if (this.contentHandlers == null) {
            this.contentHandlers = new ArrayList<ContentHandler>();
        }
        contentHandlers.add(contentHandler);
    }

    public void addTitleHandler(TitleHandler titleHandler) {
        if (this.titleHandlers == null) {
            this.titleHandlers = new ArrayList<TitleHandler>();
        }
        titleHandlers.add(titleHandler);
    }

    public String getTitle(Document doc, Rule rule) {
        if (crawler == null) {
            log.warn("Crawler is null");
            return null;
        }
        String title = crawler.getTitle(doc, rule);
        if (titleHandlers != null) {
            for (TitleHandler handler : titleHandlers) {
                title = handler.handle(title);
            }
        }
        return title;
    }

    public Element getContent(Document doc, Rule rule) {
        if (crawler == null) {
            log.warn("Crawler is null");
            return null;
        }
        Element content = crawler.getContent(doc, rule);
        if (contentHandlers != null) {
            for (ContentHandler handler : contentHandlers) {
                content = handler.handle(content);
            }
        }
        return content;
    }

}
