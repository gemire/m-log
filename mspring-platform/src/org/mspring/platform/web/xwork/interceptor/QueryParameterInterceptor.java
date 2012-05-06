/**
 * 
 */
package org.mspring.platform.web.xwork.interceptor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.platform.dao.query.QueryContext;
import org.mspring.platform.dao.query.QueryFilter;
import org.mspring.platform.dao.query.support.SimpleQueryFilter;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.utils.RequestUtils;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author Gao Youbo
 * @since Mar 21, 2012
 */
public class QueryParameterInterceptor implements Interceptor {

    /**
     * 
     */
    private static final long serialVersionUID = -8541196647287106319L;

    private static final Logger log = Logger.getLogger(QueryParameterInterceptor.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
     */
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
     */
    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
     */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        // TODO Auto-generated method stub
        Action action = (Action) invocation.getAction();
        if ((action instanceof QueryParameterAware)) {
            return doIntercept(invocation, (QueryParameterAware) action);
        }
        return invocation.invoke();
    }

    protected String doIntercept(ActionInvocation invocation, QueryParameterAware action) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Beging interceptor QueryParameterAware for " + action.getClass());
        }

        Map requestParams = ActionContext.getContext().getParameters();
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
            return "queryrequired";
        }

        if (log.isDebugEnabled())
            log.debug("End interceptor QueryParameterAware for " + action.getClass());
        return invocation.invoke();
    }

    private void addParam(Map queryParams, String nameValuePair) {
        String[] nameValue = StringUtils.split(nameValuePair, '=');
        if ((nameValue != null) && (nameValue.length == 2) && (!nameValue.equals("hasQueried")))
            queryParams.put(nameValue[0], nameValue[1]);
    }

}
