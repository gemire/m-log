/**
 * 
 */
package org.mspring.mlog.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.common.WebContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Gao Youbo
 * @since 2012-7-23
 * @Description
 * @TODO
 */
public class WebUtils extends org.springframework.web.util.WebUtils {

    /**
     * 获取对应文件系统的路径
     * 
     * @return
     */
    public static String getRealContextPath() {
        return getRealContextPath("/");
    }

    /**
     * 获取对应文件系统的路径
     * 
     * @param path
     * @return
     */
    public static String getRealContextPath(String path) {
        return WebContext.getInstance().getServletContext().getRealPath(path);
    }

    /**
     * 获取Request
     * 
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    /**
     * 获取Response
     * 
     * @return
     */
    @Deprecated
    public static HttpServletResponse getResponse() {
        return WebContext.getInstance().getResponse();
    }

}
