package org.mspring.platform.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.mspring.platform.security.config.SecurityConfig;

public class SecurityFilter implements Filter {
    private static final Log log = LogFactory.getLog(SecurityFilter.class);
    private static final String ALREADY_FILTERED = "securityfilter_already_filtered";
    private static final String ORIGINAL_URL = "sentry.original.url";
    private FilterConfig config = null;
    private SecurityConfig securityConfig = null;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void destroy() {
        this.config = null;
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(req, res);
    }
}