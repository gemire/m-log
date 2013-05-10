/**
 * 
 */
package org.mspring.msns.web.module.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.msns.core.ServiceFactory;
import org.mspring.platform.web.freemarker.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2013-5-6
 * @description
 * @TODO
 */
@Widget
@RequestMapping("/admin/comment/spam")
public class Admin_SpamWidget extends Admin_CommentWidget {

    @RequestMapping("/config")
    public String config(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        options = ServiceFactory.getOptionService().getOptions();
        model.addAllAttributes(options);
        return "/admin/comment/spam/config";
    }

    @RequestMapping("/config/save")
    public String config_save(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        ServiceFactory.getOptionService().setOptions(options);
        return prompt(model, "系统消息", "配置保存成功", "/admin/comment/spam/config");
    }
}
