/**
 * 
 */
package org.mspring.mlog.web.freemarker.widget.http;

import java.io.Serializable;

/**
 * @author Gao Youbo
 * @since 2012-7-17
 * @Description
 * @TODO
 */
public interface HttpWidget extends Serializable {
    HttpWidgetRequest getWidgetRequest();

    HttpWidgetResponse getWidgetResponse();
}
