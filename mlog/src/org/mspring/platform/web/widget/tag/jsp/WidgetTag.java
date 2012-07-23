/**
 * 
 */
package org.mspring.platform.web.widget.tag.jsp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.web.GenericResponseWrapper;

import org.apache.log4j.Logger;
import org.mspring.platform.core.ContextManager;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.Keys;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

/**
 * @author Gao Youbo
 * @since 2012-7-17
 * @Description
 * @TODO
 */
public class WidgetTag extends TagSupport {
    /**
     * 
     */
    private static final long serialVersionUID = -3289603190424129610L;

    private static final Logger log = Logger.getLogger(WidgetTag.class);

    private String path;
    private boolean cache = true;

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     *            the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the cache
     */
    public boolean isCache() {
        return cache;
    }

    /**
     * @param cache
     *            the cache to set
     */
    public void setCache(boolean cache) {
        this.cache = cache;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
     */
    @Override
    public int doEndTag() throws JspException {
        // TODO Auto-generated method stub
        // 验证path信息是否为空
        if (StringUtils.isBlank(path)) {
            try {
                pageContext.getOut().write(errorInfo());
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return EVAL_PAGE;
        }

        StringBuffer buffer = new StringBuffer();
        Cache widgetCache = getWidgetCache();
        Object value = null;
        //(cache && widgetCache != null) ? widgetCache.get(path).getValue() : null;
        if (cache) {
            Element cacheElement = widgetCache.get(path);
            if (cacheElement != null) {
                value = cacheElement.getValue();
            }
        }
        if (value == null) {
            try {
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
                final HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();

                pageContext.getRequest().getRequestDispatcher(path).include(request, new GenericResponseWrapper(response, baos));
                value = (baos.toString("UTF-8"));
            }
            catch (ServletException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // 添加缓存
        if (cache && value != null && StringUtils.isNotBlank((String) value)) {
            Element element = new Element(path, value);
            widgetCache.put(element);
        }

        // 输出到页面
        if (value != null && StringUtils.isNotBlank((String) value)) {
            buffer.append((String) value);
        }
        if (StringUtils.isNotBlank(buffer.toString())) {
            try {
                pageContext.getOut().write(buffer.toString());
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return EVAL_PAGE;
    }

    private String errorInfo() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<div style='font-size:12px;color:blue;'>");
        buffer.append("path can't be null");
        buffer.append("</div>");
        return buffer.toString();
    }

    
    private Cache widgetCache = null;
    protected Cache getWidgetCache() {
        if (widgetCache == null) {
            this.widgetCache = CacheManager.getInstance().getCache(Keys.WIDGET_CACHE_KEY);
        }
        return this.widgetCache;
    }

}
