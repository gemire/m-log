/**
 * 
 */
package org.mspring.mlog.web.module.admin.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.utils.PostUrlUtils;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.AbstractAdminWidget;
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
public class PermaLinkWidget extends AbstractAdminWidget {
    @RequestMapping("/config")
    public String config(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("rules", PostUrlUtils.Rule.getRuleMap());
        model.addAttribute("permalink", optionService.getOption("permalink"));
        return "/admin/system/permalink/config";
    }

    @RequestMapping("/config/save")
    public String saveConfig(HttpServletRequest request, HttpServletResponse response, Model model) {
        String permalink = request.getParameter("permalink");
        optionService.setOption("permalink", permalink);
        return prompt(model, "系统消息", "固定连接设置成功", "/admin/system/permalink/config");
    }
}
