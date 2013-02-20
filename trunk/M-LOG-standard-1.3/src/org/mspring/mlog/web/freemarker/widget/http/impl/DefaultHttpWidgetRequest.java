/**
 * 
 */
package org.mspring.mlog.web.freemarker.widget.http.impl;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.mspring.mlog.web.freemarker.widget.http.HttpWidgetRequest;
import org.springframework.web.util.WebUtils;

/**
 * @author Gao Youbo
 * @since 2012-12-5
 * @Description
 * @TODO
 */
public class DefaultHttpWidgetRequest extends HttpServletRequestWrapper implements HttpWidgetRequest, Serializable {

    private Map<String, ?> widgetModel;

    /**
     * @param request
     */
    public DefaultHttpWidgetRequest(HttpServletRequest request) {
        super(request);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.web.freemarker.widget.http.HttpWidgetRequest#getWidgetModel
     * ()
     */
    @Override
    public Map<String, ?> getWidgetModel() {
        // TODO Auto-generated method stub
        return widgetModel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.web.freemarker.widget.http.HttpWidgetRequest#bindWidgetModel
     * (java.util.Map)
     */
    @Override
    public void bindWidgetModel(Map<String, ?> widgetModel) {
        // TODO Auto-generated method stub
        this.widgetModel = widgetModel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.ServletRequestWrapper#getRequestDispatcher(java.lang.String
     * )
     */
    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        // TODO Auto-generated method stub
//        try {
//            setAttribute(WebUtils.INCLUDE_PATH_INFO_ATTRIBUTE, path);
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
        return super.getRequestDispatcher(path);
    }

}
