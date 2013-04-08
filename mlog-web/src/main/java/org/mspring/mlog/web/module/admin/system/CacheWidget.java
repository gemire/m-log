/**
 * 
 */
package org.mspring.mlog.web.module.admin.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.service.cache.CacheService;
import org.mspring.mlog.support.log.Log;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.AbstractAdminWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2012-11-9
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/system/cache")
public class CacheWidget extends AbstractAdminWidget {
    @Autowired
    private CacheService cacheService;

    @RequestMapping("/config")
    @Log
    public String setting(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/admin/system/cache/config";
    }

    @RequestMapping("/config/save")
    @Log
    public String saveSetting(@RequestParam String cache_prefix, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "redirect:/admin/system/cache/config";
    }

    @RequestMapping("/clear")
    @Log
    public String doClear(HttpServletRequest request, HttpServletResponse response, Model model) {
        String defaultCache = request.getParameter("defaultCache");
        String widgetCache = request.getParameter("widgetCache");
        String optionCache = request.getParameter("optionCache");
        String otherCache = request.getParameter("otherCache");

        String[] cacheNames = cacheService.getCacheNames();
        for (String cacheName : cacheNames) {
            if (CacheService.CacheName.DEFAULT_CACHE_NAME.equals(cacheName)) {
                if ("on".equals(defaultCache)) {
                    cacheService.clearCache(cacheName);
                }
            } else if (CacheService.CacheName.WIDGET_CACHE_NAME.equals(cacheName)) {
                if ("on".equals(widgetCache)) {
                    cacheService.clearCache(cacheName);
                }
            } else if (CacheService.CacheName.OPTION_CACHE_NAME.equals(cacheName)) {
                if ("on".equals(optionCache)) {
                    cacheService.clearCache(cacheName);
                }
            } else {
                if ("on".equals(otherCache)) {
                    cacheService.clearCache(cacheName);
                }
            }
        }
        return prompt(model, "缓存清理成功！");
    }
}
