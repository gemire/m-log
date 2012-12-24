/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.AbstractWidget;
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
@RequestMapping("/admin/cache")
public class CacheWidget extends AbstractWidget {
    @RequestMapping("/setting")
    public String setting(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<String> keys = cacheService.getCacheKeys();
        model.addAttribute("keys", keys);

        String cache_prefix = optionService.getPropertiesOption("cache_prefix");
        model.addAttribute("cache_prefix", cache_prefix);
        return "/admin/cache/setting";
    }

    @RequestMapping("/saveSetting")
    public String saveSetting(@RequestParam String cache_prefix, HttpServletRequest request, HttpServletResponse response, Model model) {
        optionService.setPropertiesOption("cache_prefix", cache_prefix);
        List<String> keys = cacheService.getCacheKeys();
        if (keys != null) {
            for (String key : keys) {
                cacheService.deleteCache(key);
            }
        }
        return "redirect:/admin/cache/setting";
    }

    @RequestMapping("/doClear")
    @ResponseBody
    public String doClear(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 开始清理缓存
        List<String> keys = cacheService.getCacheKeys();
        if (keys != null) {
            for (String key : keys) {
                cacheService.deleteCache(key);
            }
        }
        return "true";
    }
}
