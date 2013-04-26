/**
 * 
 */
package org.mspring.mlog.web.freemarker.render.tag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.service.OptionService;
import org.mspring.platform.utils.StringUtils;

import freemarker.template.SimpleHash;

/**
 * @author Gao Youbo
 * @since 2013-3-4
 * @description
 * @TODO
 */
public class MenuRenderTag extends CacheRenderTag {

    /**
     * 
     */
    private static final long serialVersionUID = 3155106965747800827L;

    @Override
    protected String cachedProcess(SimpleHash model) {
        // TODO Auto-generated method stub
        OptionService optionService = ServiceFactory.getOptionService();
        List<String> menus = new ArrayList<String>();
        String url = optionService.getOption("blogurl");

        // 用户配置menu
        String menuString = optionService.getOption("menu");
        if (StringUtils.isNotBlank(menuString)) {
            StringReader sr = new StringReader(menuString);
            BufferedReader reader = new BufferedReader(sr);
            try {
                String menu = "";
                while (StringUtils.isNotBlank(menu = reader.readLine())) {
                    menu = menu.replaceAll("%base%", url);
                    menus.add(menu);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        model.put("menus", menus);
        return getTemplateString(model);
    }

    @Override
    protected String getCacheKey(SimpleHash model) {
        // TODO Auto-generated method stub
        return null;
    }

}
