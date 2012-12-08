/**
 * 
 */
package org.mspring.platform.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Gao Youbo
 * @since Jan 31, 2012
 */
public class RequestUtils {
    
    public static String getRequestParameter(Map requestParams, String paramKey) {
        Object value = requestParams.get(paramKey);
        if (value == null) {
            return null;
        }
        String[] vals = (String[]) value;

        if ((vals != null) && (vals.length > 0)) {
            return vals[0];
        }
        return null;
    }

    public static String getRemortIP(HttpServletRequest i11IlI) {
        return i11IlI.getRemoteAddr() == null ? "" : i11IlI.getRemoteAddr();
    }

    public static String getRemoteIP(HttpServletRequest request) {
        String header = request.getHeader("X-Forwarded-For");

        if ((header == null) || (header.length() == 0) || ("unknown".equalsIgnoreCase(header))) {
            header = request.getHeader("Proxy-Client-IP");
        }

        if ((header == null) || (header.length() == 0) || ("unknown".equalsIgnoreCase(header))) {
            header = request.getHeader("WL-Proxy-Client-IP");
        }

        if ((header == null) || (header.length() == 0) || ("unknown".equalsIgnoreCase(header))) {
            header = request.getHeader("HTTP_CLIENT_IP");
        }

        if ((header == null) || (header.length() == 0) || ("unknown".equalsIgnoreCase(header))) {
            header = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if ((header == null) || (header.length() == 0) || ("unknown".equalsIgnoreCase(header))) {
            header = request.getRemoteAddr();
        }
        return header;
    }
}
