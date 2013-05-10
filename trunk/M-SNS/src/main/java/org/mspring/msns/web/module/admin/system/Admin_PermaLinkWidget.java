/**
 * 
 */
package org.mspring.msns.web.module.admin.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.msns.service.OptionService;
import org.mspring.msns.support.log.Log;
import org.mspring.msns.utils.PostUrlUtils;
import org.mspring.msns.web.module.admin.AbstractAdminWidget;
import org.mspring.platform.web.freemarker.widget.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
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
public class Admin_PermaLinkWidget extends AbstractAdminWidget {
    @Autowired
    private OptionService optionService;

    @RequestMapping("/config")
    public String config(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("rules", PostUrlUtils.Rule.getRuleMap());
        model.addAttribute("permalink", optionService.getOption("permalink"));
        return "/admin/system/permalink/config";
    }

    @RequestMapping("/config/save")
    @Log
    public String saveConfig(HttpServletRequest request, HttpServletResponse response, Model model) {
        String permalink = request.getParameter("permalink");
        optionService.setOption("permalink", permalink);
        return prompt(model, "系统消息", "固定连接设置成功", "/admin/system/permalink/config");
    }
}
