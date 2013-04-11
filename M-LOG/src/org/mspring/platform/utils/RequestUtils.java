/**
 * 
 */
package org.mspring.platform.utils;

import java.util.Enumeration;
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

    /**
     * 获取请求的IP地址
     * 
     * @param request
     * @return
     */
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

    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("user-agent");
    }

    /**
     * 验证请求的合法性，防止跨域攻击
     * 
     * @param request
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean validateRequest(HttpServletRequest request) {
        String referer = "";
        boolean referer_sign = true; // true 站内提交，验证通过 //false 站外提交，验证失败
        Enumeration headerValues = request.getHeaders("referer");
        while (headerValues.hasMoreElements()) {
            referer = (String) headerValues.nextElement();
        }
        // 判断是否存在请求页面
        if (StringUtils.isBlank(referer))
            referer_sign = false;
        else {
            // 判断请求页面和getRequestURI是否相同
            String servername_str = request.getServerName();
            if (StringUtils.isNotBlank(servername_str)) {
                int index = 0;
                if (StringUtils.indexOf(referer, "https://") == 0) {
                    index = 8;
                } else if (StringUtils.indexOf(referer, "http://") == 0) {
                    index = 7;
                }
                if (referer.length() - index < servername_str.length()) {// 长度不够
                    referer_sign = false;
                } else { // 比较字符串（主机名称）是否相同
                    String referer_str = referer.substring(index, index + servername_str.length());
                    if (!servername_str.equalsIgnoreCase(referer_str))
                        referer_sign = false;
                }
            } else
                referer_sign = false;
        }
        return referer_sign;
    }
}
