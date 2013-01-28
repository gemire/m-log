/**
 * 
 */
package org.mspring.mlog.web.module.admin.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.rulrewrite.PostRewriteRule;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2013-1-28
 * @description
 * @TODO
 */
@Widget
@RequestMapping("/admin/system/permalink")
public class PermaLinkWidget {
    @RequestMapping("/config")
    public String config(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("rules", PostRewriteRule.Rule.getRuleMap());
        model.addAttribute("permalink", ServiceFactory.getOptionService().getOption("permalink"));
        return "/admin/system/permalink/config";
    }

    @RequestMapping("/config/save")
    public String saveConfig(HttpServletRequest request, HttpServletResponse response, Model model) {
        String permalink = request.getParameter("permalink");
        ServiceFactory.getOptionService().setOption("permalink", permalink);
        return "redirect:/admin/system/permalink/config";
    }
}
