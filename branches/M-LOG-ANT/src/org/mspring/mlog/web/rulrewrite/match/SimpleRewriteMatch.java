/**
 * 
 */
package org.mspring.mlog.web.rulrewrite.match;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.platform.utils.StringUtils;
import org.tuckey.web.filters.urlrewrite.extend.RewriteMatch;

/**
 * @author Gao Youbo
 * @since 2013-1-28
 * @description
 * @TODO
 */
public class SimpleRewriteMatch extends RewriteMatch {

    private String baseUrl;
    private Map<String, Object> queryParams;

    /**
     * 
     */
    public SimpleRewriteMatch() {
        // TODO Auto-generated constructor stub
    }

    public SimpleRewriteMatch(Map<String, Object> queryParams) {
        this.queryParams = queryParams;
    }

    public SimpleRewriteMatch(String baseUrl, Map<String, Object> queryParams) {
        super();
        this.baseUrl = baseUrl;
        this.queryParams = queryParams;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, Object> queryParams) {
        this.queryParams = queryParams;
    }

    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        if (StringUtils.isBlank(baseUrl)) {
            return true;
        }
        StringBuffer url = new StringBuffer(baseUrl);
        String joinChar = baseUrl.indexOf("?") > 0 ? "&" : "?"; 
        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            url.append(joinChar).append(entry.getKey()).append("=").append(entry.getValue());
            joinChar = "&";
        }
        RequestDispatcher rd = request.getRequestDispatcher(url.toString());
        rd.forward(request, response);
        return true;
    }

}
