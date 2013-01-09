/**
 * 
 */
package org.mspring.mlog.web;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Gao Youbo
 * @since 2012-12-5
 * @Description
 * @TODO
 */
public class WebContext implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5854991708389982695L;
    
    public static final String WEB_CONTEXT_KEY = "_WEB_CONTEXT_KEY__";
    
    private HttpServletRequest request;
    private HttpServletResponse response;

    /**
     * 
     */
    public WebContext() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param request
     * @param response
     */
    public WebContext(HttpServletRequest request, HttpServletResponse response) {
        super();
        this.request = request;
        this.response = response;
    }

    /**
     * @return the request
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * @param request
     *            the request to set
     */
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * @return the response
     */
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * @param response
     *            the response to set
     */
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

}
