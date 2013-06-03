/**
 * 
 */
package org.mspring.mlog.web.module.js;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.mspring.mlog.common.OptionKeys;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.utils.SkinUtils;
import org.mspring.mlog.web.security.SecurityUtils;
import org.mspring.platform.utils.FreemarkerUtils;
import org.mspring.platform.web.freemarker.widget.stereotype.Widget;
import org.mspring.platform.web.render.ScriptRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import freemarker.template.Configuration;

/**
 * @author Gao Youbo
 * @since 2012-8-1
 * @Description
 * @TODO 向页面输出javascript需要调用的系统变量
 */
@Widget
@RequestMapping("/script")
public class ScriptVariableWidget {
    @Autowired
    private Configuration configuration;

    @RequestMapping("/script_variable")
    public void execute(HttpServletRequest request, HttpServletResponse response, Model model) {

        Map<Object, Object> variables = new HashMap<Object, Object>();
        variables.put("base", StringEscapeUtils.escapeEcmaScript(request.getContextPath()));
        variables.put(OptionKeys.TEMPLATE_URL, StringEscapeUtils.escapeEcmaScript(SkinUtils.getTemplateUrl(request)));
        variables.put("blogurl", ServiceFactory.getOptionService().getOption("blogurl"));

        User user = SecurityUtils.getCurrentUser(request);
        if (user != null) {
            variables.put("user", user.getId());
            variables.put("username", user.getName());
            variables.put("useralias", user.getAlias());
            variables.put("useremail", user.getEmail());
        }

        Map<Object, Object> mapModel = new HashMap<Object, Object>();
        mapModel.put("variables", variables);

        String script = FreemarkerUtils.render(configuration, "script/scriptVariable.ftl", mapModel);
        ScriptRender renderer = new ScriptRender(script);
        renderer.render(response);
    }
}
