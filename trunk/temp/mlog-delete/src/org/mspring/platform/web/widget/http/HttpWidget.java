/**
 * 
 */
package org.mspring.platform.web.widget.http;

/**
 * @author Gao Youbo
 * @since 2012-7-17
 * @Description
 * @TODO
 */
public interface HttpWidget {
    HttpWidgetRequest getWidgetRequest();

    HttpWidgetResponse getWidgetResponse();
}
