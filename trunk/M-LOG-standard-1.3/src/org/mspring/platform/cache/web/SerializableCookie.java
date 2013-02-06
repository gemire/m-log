/**
 * 
 */
package org.mspring.platform.cache.web;

import java.io.Serializable;

import javax.servlet.http.Cookie;

/**
 * @author Gao Youbo
 * @since 2013-2-5
 * @description
 * @TODO
 */
public class SerializableCookie implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5683150099829811614L;

    private String name;
    private String value;
    private String comment;
    private String domain;
    private int maxAge;
    private String path;
    private boolean secure;
    private int version;

    /** Creates a cookie. */
    public SerializableCookie(final Cookie cookie) {
        name = cookie.getName();
        value = cookie.getValue();
        comment = cookie.getComment();
        domain = cookie.getDomain();
        maxAge = cookie.getMaxAge();
        path = cookie.getPath();
        secure = cookie.getSecure();
        version = cookie.getVersion();
    }

    /** Builds a Cookie object from this object. */
    public Cookie toCookie() {
        final Cookie cookie = new Cookie(name, value);
        cookie.setComment(comment);
        // Otherwise null pointer exception
        if (domain != null) {
            cookie.setDomain(domain);
        }
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        cookie.setSecure(secure);
        cookie.setVersion(version);
        return cookie;
    }
}
