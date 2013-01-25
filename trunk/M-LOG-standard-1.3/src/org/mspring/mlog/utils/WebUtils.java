/**
 * 
 */
package org.mspring.mlog.utils;

import org.mspring.mlog.web.WebContext;

/**
 * @author Gao Youbo
 * @since 2012-7-23
 * @Description
 * @TODO
 */
public class WebUtils {

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

}
