/**
 * 
 */
package org.mspring.platform.web.filter;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.platform.web.wrapper.GzipResponseWrapper;
import org.springframework.web.util.UrlPathHelper;

/**
 * @author Gao Youbo
 * @since 2012-11-8
 * @Description
 * @TODO
 */
public class GzipFilter implements Filter {

    private FilterConfig config;

    protected FilterConfig getFilterConfig() {
        return (config);
    }

    /***
     * If browser supports gzip, set the Content-Encoding response header and
     * invoke resource with a wrapped response that collects all the output.
     * Extract the output and write it into a gzipped byte array. Finally, write
     * that array to the client's output stream.
     * 
     * If browser does not support gzip, invoke resource normally.
     * 
     * @exception ServletException
     * @exception IOException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if ((threshold == 0) || (isGzipSupported(req) == false) || (isGzipEligible(req) == false)) {
            chain.doFilter(request, response);
            return;
        }
        GzipResponseWrapper wrappedResponse = new GzipResponseWrapper(res);
        wrappedResponse.setCompressionThreshold(threshold);
        try {
            chain.doFilter(request, wrappedResponse);
        }
        finally {
            wrappedResponse.finishResponse();
        }
    }

    /***
     * Comma separated string patterns to be found in the request URI
     */
    protected String uriMatch;

    /***
     * Comma separated string patterns to be excluded in the request URI if
     * founded by match.
     */
    protected String uriExclude;

    /***
     * Minimal threshold.
     */
    private int minThreshold = 128;

    /***
     * The threshold number to compress, (0 == no compression).
     */
    protected int threshold;

    private String[] extensions;
    private String[] excludes;

    /***
     * Filter initialization.
     * 
     * @exception ServletException
     */
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        extensions = null;

        // min size
        try {
            threshold = Integer.parseInt(config.getInitParameter("threshold"));
        }
        catch (NumberFormatException nfe) {
            threshold = 0;
        }
        if (threshold < minThreshold) {
            threshold = 0;
        }

        // match string
        uriMatch = config.getInitParameter("match");
        if ((uriMatch != null) && (uriMatch.equals("*") == false)) {
            StringTokenizer st = new StringTokenizer(uriMatch, ",");
            int i = st.countTokens();
            if (i >= 1) {
                extensions = new String[i];
                i = 0;
                while (st.hasMoreTokens()) {
                    extensions[i] = st.nextToken().trim();
                    i++;
                }
            }
        }
        // exclude string
        uriExclude = config.getInitParameter("exclude");
        if (uriExclude != null) {
            StringTokenizer st = new StringTokenizer(uriExclude, ",");
            int i = st.countTokens();
            if (i >= 1) {
                excludes = new String[i];
                i = 0;
                while (st.hasMoreTokens()) {
                    excludes[i] = st.nextToken().trim();
                    i++;
                }
            }
        }
    }

    public void destroy() {
        config = null;
        extensions = null;
        excludes = null;
    }

    /**
     * 检测是否支持gzip压缩
     * @param req
     * @return
     */
    private boolean isGzipSupported(HttpServletRequest req) {
        String browserEncodings = req.getHeader("Accept-Encoding");
        return (browserEncodings != null) && (browserEncodings.indexOf("gzip") != -1);
    }

    /***
     * 判定请求URI是否进行gzip压缩
     * 
     * @return 可压缩返回true，否则返回false
     */
    private boolean isGzipEligible(HttpServletRequest req) {
        String uri = req.getRequestURI();
        if (uri == null) {
            return false;
        }
        boolean result = false;

        if (extensions == null) { // match=*
            result = true;
        }
        else {
            for (String extension : extensions) {
                try {
                    Matcher matcher = Pattern.compile(extension).matcher(uri);
                    if (matcher.find()) {
                        result = true;
                        break;
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    result = false;
                    break;
                }
                /*
                 if (uri.indexOf(extension) != -1) {
                    result = true; // extension founded
                    break;
                 } 
                 */
            }
        }

        if ((result == true) && (excludes != null)) {
            for (String exclude : excludes) {
                if (uri.indexOf(exclude) != -1) {
                    result = false; // excludes founded
                    break;
                }
            }
        }
        return result;
    }

}
