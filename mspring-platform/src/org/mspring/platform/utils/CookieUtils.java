package org.mspring.platform.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author gaoyb(www.mspring.org)
 * @since Mar 5, 2011 org.mspring.core.web.util
 */
public class CookieUtils {
    private static final Logger logger = Logger.getLogger(CookieUtils.class);

    private CookieUtils() {
    }

    public static void printCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0)
            return;

        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            System.out.println("Cookie[name=" + cookie.getName() + ", value=" + cookie.getValue() + "]");
        }
    }

    public static void setCookie(HttpServletResponse response, String name, String value) {
        setCookie(response, name, value, 0);
    }

    public static void setCookie(HttpServletResponse response, String name, String value, int iExpireDays) {
        Cookie cookie = new Cookie(name, value);
        if (iExpireDays != 0) {
            cookie.setMaxAge(iExpireDays * 24 * 60 * 60 * 1000);
        }
        response.addCookie(cookie);
        logger.debug("set cookie [" + name + "=" + value + "]");
    }

    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0)
            return "";

        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (name.equals(cookie.getName()))
                return cookie.getValue();
        }
        return "";
    }
}
