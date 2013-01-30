/**
 * 
 */
package org.mspring.mlog.web.freemarker.widget.http.impl;

import org.mspring.mlog.web.freemarker.widget.http.HttpWidget;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidgetRequest;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidgetResponse;

/**
 * @author Gao Youbo
 * @since 2012-12-5
 * @Description
 * @TODO
 */
public class DefaultHttpWidget implements HttpWidget {

    /**
     * 
     */
    private static final long serialVersionUID = 9161552432626563867L;

    private HttpWidgetRequest widgetRequest;
    private HttpWidgetResponse widgetResponse;

    /**
     * @param widgetRequest
     * @param widgetResponse
     */
    public DefaultHttpWidget(HttpWidgetRequest widgetRequest, HttpWidgetResponse widgetResponse) {
        super();
        this.widgetRequest = widgetRequest;
        this.widgetResponse = widgetResponse;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.web.freemarker.widget.http.HttpWidget#getWidgetRequest()
     */
    @Override
    public HttpWidgetRequest getWidgetRequest() {
        // TODO Auto-generated method stub
        return widgetRequest;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.web.freemarker.widget.http.HttpWidget#getWidgetResponse
     * ()
     */
    @Override
    public HttpWidgetResponse getWidgetResponse() {
        // TODO Auto-generated method stub
        return widgetResponse;
    }

}
