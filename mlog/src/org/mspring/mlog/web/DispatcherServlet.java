/**
 * 
 */
package org.mspring.mlog.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.mspring.platform.web.Keys;
import org.springframework.web.servlet.ModelAndView;

import freemarker.ext.jsp.TaglibFactory;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * @author Gao Youbo
 * @since 2012-7-17
 * @Description
 * @TODO
 */
public class DispatcherServlet extends org.mspring.platform.web.servlet.DispatcherServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -6829172243642413052L;
    private static final String TEMPLATE_MODEL_CACHE_NAME = "TemplateModelCache";
    private static final String TEMPLATE_MODEL_CACHE_KEY = "TEMPLATE_MODEL_CACHE_KEY";

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.platform.web.servlet.DispatcherServlet#render(org.springframework
     * .web.servlet.ModelAndView, javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        exportWidgetTld(request, mv);
        super.render(mv, request, response);
    }

    /**
     * 刚widget.tld标签添加到环境变量
     * 
     * @param request
     * @param mv
     */
    private void exportWidgetTld(HttpServletRequest request, ModelAndView mv) {
        try {
            TemplateModel templateModel = getTemplateModelCache(TEMPLATE_MODEL_CACHE_KEY);
            if (templateModel == null) {
                TaglibFactory factory = (TaglibFactory) mv.getModel().get("MSPRING_WIDGET_TAGLIB");
                if (factory == null) {
                    factory = new TaglibFactory(request.getSession().getServletContext());
                    mv.addObject("MSPRING_WIDGET_TAGLIB", factory);
                }
                templateModel = factory.get("/WEB-INF/tld/widget.tld");
                cacheTemplateModel(TEMPLATE_MODEL_CACHE_KEY, templateModel);
            }
            mv.addObject(Keys.WIDGET_KEY, templateModel);
        }
        catch (TemplateModelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 缓存tld标签
     * 
     * @param key
     * @param templateModel
     */
    private void cacheTemplateModel(String key, TemplateModel templateModel) {
        Cache cache = CacheManager.getInstance().getCache(TEMPLATE_MODEL_CACHE_NAME);
        Element element = new Element(key, templateModel);
        cache.put(element);
    }

    /**
     * 获取tld标签缓存
     * 
     * @param key
     * @return
     */
    private TemplateModel getTemplateModelCache(String key) {
        Cache cache = CacheManager.getInstance().getCache(TEMPLATE_MODEL_CACHE_NAME);
        if (cache != null) {
            Element element = cache.get(key);
            if (element != null) {
                return (TemplateModel) element.getObjectValue();
            }
        }
        return null;
    }
}
