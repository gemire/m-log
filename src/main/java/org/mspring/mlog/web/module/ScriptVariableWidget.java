/**
 * 
 */
package org.mspring.mlog.web.module;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.mspring.mlog.common.OptionKeys;
import org.mspring.mlog.utils.SkinUtils;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-8-1
 * @Description
 * @TODO 向页面输出javascript需要调用的系统变量
 */
@Widget
@RequestMapping("/")
public class ScriptVariableWidget {
    @RequestMapping("/script_variable.js")
    public String execute(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map<String, String> variables = new HashMap<String, String>();
        variables.put("base", StringEscapeUtils.escapeEcmaScript(request.getContextPath()));
        variables.put(OptionKeys.TEMPLATE_URL, StringEscapeUtils.escapeEcmaScript(SkinUtils.getTemplateUrl(request)));
        model.addAttribute("variables", variables);
        return "/common/scriptVariable";
    }
}
