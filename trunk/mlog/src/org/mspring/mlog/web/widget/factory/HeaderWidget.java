/**
 * 
 */
package org.mspring.mlog.web.widget.factory;

import java.util.List;
import java.util.Map;

import org.mspring.mlog.common.ConfigTokens;
import org.mspring.mlog.web.widget.AbstractWidget;

/**
 * @author Gao Youbo
 * @since Apr 27, 2012
 */
public class HeaderWidget extends AbstractWidget {

    /* (non-Javadoc)
     * @see org.mspring.mlog.web.widget.WidgetInterface#render(java.util.List)
     */
    @Override
    public String render(List args) {
        // TODO Auto-generated method stub
        String path = getRequest().getContextPath();
        StringBuilder result = new StringBuilder();
        result.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + path + "/css/common.css\" />");
        result.append("<script language=\"JavaScript\" src=\"" + path + "/script/jquery/jquery.js\" type=\"text/javascript\"></script>\n");
        result.append("<script language=\"JavaScript\" src=\"" + path + "/script/common.js\" type=\"text/javascript\"></script>\n");
        
        //处理seo优化
        Map<String, String> seo_options = optionService.getOptionsByPrefix(ConfigTokens.MSPRING_CONFIG_SEO_PREFIX);
        if ("1".equals(seo_options.get(ConfigTokens.mspring_config_seo_enableseo))) { //如果启用了seo优化
            result.append("<meta name=\"description\" content=\"" + seo_options.get(ConfigTokens.mspring_config_seo_description) + "\" />\n");
            result.append("<meta name=\"keywords\" content=\"" + seo_options.get(ConfigTokens.mspring_config_seo_keyworld) + "\" />\n");
        }
        
        return result.toString();
    }

}
