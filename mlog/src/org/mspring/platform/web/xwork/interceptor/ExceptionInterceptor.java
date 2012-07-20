/**
 * 
 */
package org.mspring.platform.web.xwork.interceptor;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author Gao Youbo
 * @since Feb 20, 2012
 */
public class ExceptionInterceptor implements Interceptor {
    private static Logger logger = Logger.getLogger(ExceptionInterceptor.class);

    public static final String EXCEPTION = "exception";
    public static final String EXCEPTION_MESSAGE_KEY = "exception_message";
    public static final String EXCEPTION_STACKTRACE_KEY = "exception_stacktrace";

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
     */
    public void init() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
     */
    public String intercept(ActionInvocation invocation) throws Exception {
        // TODO Auto-generated method stub
        try {
            return invocation.invoke();
        } catch (Throwable cause) {
            logger.error("Catch exception at ExceptionInterceptor:", cause);

            StringWriter sw = new StringWriter();
            cause.printStackTrace(new PrintWriter(sw));
            invocation.getInvocationContext().put(EXCEPTION_STACKTRACE_KEY, sw.toString());
            invocation.getInvocationContext().put(EXCEPTION_MESSAGE_KEY, cause.getMessage());

            // TODO catch EntityNotFoundException
            return EXCEPTION;
        }
    }
}
