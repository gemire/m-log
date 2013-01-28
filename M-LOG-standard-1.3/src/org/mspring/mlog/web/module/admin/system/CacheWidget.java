/**
 * 
 */
package org.mspring.mlog.web.module.admin.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.CacheManager;

import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.AbstractAdminWidget;
import org.mspring.mlog.web.security.annotation.Premission;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Gao Youbo
 * @since 2012-11-9
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/system/cache")
public class CacheWidget extends AbstractAdminWidget {
    @RequestMapping("/config")
    @Premission(item = "740005")
    public String setting(HttpServletRequest request, HttpServletResponse response, Model model) {
        String cache_prefix = optionService.getPropertiesOption("cache_prefix");
        model.addAttribute("cache_prefix", cache_prefix);
        return "/admin/system/cache/config";
    }

    @RequestMapping("/config/save")
    @Premission(item = "740005")
    public String saveSetting(@RequestParam String cache_prefix, HttpServletRequest request, HttpServletResponse response, Model model) {
        optionService.setPropertiesOption("cache_prefix", cache_prefix);
        List<String> keys = cacheService.getCacheKeys();
        if (keys != null) {
            for (String key : keys) {
                cacheService.deleteCache(key);
            }
        }
        return "redirect:/admin/system/cache/config";
    }

    @RequestMapping("/clear")
    @ResponseBody
    @Premission(item = "740005")
    public String doClear(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 开始清理缓存
        CacheManager.getInstance().clearAll();
        return "true";
    }
}
