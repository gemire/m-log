/**
 * 
 */
package org.mspring.mlog.web.widget.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.mspring.mlog.common.ConfigTokens;
import org.mspring.mlog.common.Const;
import org.mspring.mlog.common.ServiceFactory;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.web.widget.AbstractWidget;
import org.mspring.platform.utils.CookieUtils;
import org.mspring.platform.utils.FreemarkerUtils;

import freemarker.template.Configuration;

/**
 * @author Gao Youbo
 * @since Apr 28, 2012
 */
public class AdminBarWidget extends AbstractWidget {

    private static final Logger log = Logger.getLogger(AdminBarWidget.class);

    private static final String TEMPLATE_COMMON_ADMINBAR = "common/admin-bar.ftl";

    private Configuration configuration = ServiceFactory.getFreemarkerConfiguration();

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.web.widget.WidgetInterface#render(java.util.List)
     */
    @Override
    public String render(List args) {
        // TODO Auto-generated method stub
        HttpServletRequest request = getRequest();

        String guest = CookieUtils.getCookie(request, Const.MSPRING_COOKIE_COMMENT_AUTHOR);
        User user = (User) getSessionAttribute(Const.SESSION_LOGINUSER);

        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("path", request.getContextPath());
        map.put("blogname", optionService.getOption(ConfigTokens.mspring_config_base_blogname));
        map.put("blogurl", optionService.getOption(ConfigTokens.mspring_config_base_blogurl));
        map.put("guest", guest);
        map.put("currentUser", user);

        return FreemarkerUtils.render(configuration, TEMPLATE_COMMON_ADMINBAR, map);
    }
}
