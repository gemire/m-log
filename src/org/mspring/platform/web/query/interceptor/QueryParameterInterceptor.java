/**
 * 
 */
package org.mspring.platform.web.query.interceptor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mspring.platform.persistence.query.QueryContext;
import org.mspring.platform.persistence.query.QueryFilter;
import org.mspring.platform.persistence.query.support.SimpleQueryFilter;
import org.mspring.platform.utils.RequestUtils;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.query.QueryParameterAware;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Gao Youbo
 * @since 2012-7-28
 * @Description 2012-7-28
 * @TODO 2012-7-28
 */
public class QueryParameterInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = Logger.getLogger(QueryParameterInterceptor.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle
     * (javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO Auto-generated method stub
        if (handler.getClass().isAssignableFrom(QueryParameterAware.class)) {
            HandlerMethod method = (HandlerMethod) handler;
            Object action = method.getBean();
            if (action instanceof QueryParameterAware) {
                setParameterMaps(request, (QueryParameterAware) action);
            }
        }
        return true;
    }

    protected void setParameterMaps(HttpServletRequest request, QueryParameterAware action) {
        if (log.isDebugEnabled()) {
            log.debug("Beging interceptor QueryParameterAware for " + action.getClass());
        }

        Map requestParams = request.getParameterMap();
        Map queryParams = new HashMap();

        String encodedQueryParams = RequestUtils.getRequestParameter(requestParams, "encodedQueryParams");
        if (log.isDebugEnabled())
            log.debug("encodedQueryParams=" + encodedQueryParams);
        Iterator it;
        if (StringUtils.isNotBlank(encodedQueryParams)) {
            if (log.isDebugEnabled())
                log.debug("Set QueryParameterAware's queryParams with encodedQueryParams");

            String[] nameValuePairs = StringUtils.split(encodedQueryParams, '&');
            if (nameValuePairs != null) {
                for (int i = 0; i < nameValuePairs.length; i++) {
                    addParam(queryParams, nameValuePairs[i]);
                    if (!log.isDebugEnabled())
                        continue;
                    log.debug("Set queryParams with encodedQueryParams:[" + nameValuePairs[i] + "]");
                }
            }
        } else {
            if (log.isDebugEnabled())
                log.debug("Set QueryParameterAware's queryParams with ServletRequest's parameters");

            for (it = requestParams.keySet().iterator(); it.hasNext();) {
                String key = (String) it.next();
                String value = RequestUtils.getRequestParameter(requestParams, key);
                queryParams.put(key, value);
                if (!log.isDebugEnabled())
                    continue;
                log.debug("Set queryParams with HttpRequest parameter:[" + key + "=" + value + "]");
            }
        }

        action.setQueryParameters(queryParams);
        if (log.isDebugEnabled())
            log.debug("Set QueryParameterAware's queryParams with value: " + queryParams);

        QueryContext queryContext = new QueryContext(action, requestParams);
        QueryFilter queryFilter = new SimpleQueryFilter();
        if ((StringUtils.isBlank(encodedQueryParams)) && (queryFilter.isQueryRequired(queryContext))) {
            if (log.isDebugEnabled()) {
                log.debug("Query required for this request, dispatche to queryrequired page");
                log.debug("End interceptor QueryParameterAware for " + action.getClass());
            }
            // return "queryrequired";
        }
        if (log.isDebugEnabled())
            log.debug("End interceptor QueryParameterAware for " + action.getClass());
    }

    private void addParam(Map queryParams, String nameValuePair) {
        String[] nameValue = StringUtils.split(nameValuePair, '=');
        if ((nameValue != null) && (nameValue.length == 2) && (!nameValue.equals("hasQueried")))
            queryParams.put(nameValue[0], nameValue[1]);
    }

}
