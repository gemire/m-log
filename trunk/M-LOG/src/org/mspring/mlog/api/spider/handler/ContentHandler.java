/**
 * 
 */
package org.mspring.mlog.api.spider.handler;

import org.jsoup.nodes.Element;

/**
 * @author Gao Youbo
 * @since 2013-3-8
 * @description
 * @TODO
 */
public interface ContentHandler {
    public Element handle(Element content);
}
