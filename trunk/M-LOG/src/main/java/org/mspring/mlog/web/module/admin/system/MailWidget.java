/**
 * 
 */
package org.mspring.mlog.web.module.admin.system;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.service.MailService;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.support.log.Log;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.AbstractAdminWidget;
import org.mspring.platform.core.ContextManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2013-1-10
 * @Description
 * @TODO 系统配置
 */
@Widget
@RequestMapping("/admin/system/mail")
public class MailWidget extends AbstractAdminWidget {
    @Autowired
    private OptionService optionService;

    /**
     * 显示"邮件设置"
     * 
     * @param options
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/setting")
    public String mailSettingView(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        options = optionService.getOptions();
        model.addAllAttributes(options);
        return "/admin/system/mail/setting";
    }

    /**
     * 保存"邮件设置"
     * 
     * @param options
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/saveSetting")
    @Log
    public String saveMail(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        optionService.setOptions(options);
        return "redirect:/admin/system/mail/setting";
    }

    /**
     * 显示邮件测试页面
     * 
     * @param options
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/test")
    public String testView(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/admin/system/mail/test";
    }

    /**
     * 发送测试邮件
     * 
     * @param options
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/sendTestMail")
    @Log
    public String sendTestMail(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        boolean success = true;
        String message = "success!";
        try {
            String mail_to = request.getParameter("mail_to");
            String mail_content = request.getParameter("mail_content");

            MailService mailService = ContextManager.getApplicationContext().getBean(MailService.class);
            mailService.sendMail(mail_to, "", "M-LOG测试邮件", mail_content);
        } catch (Exception e) {
            // TODO: handle exception
            success = false;
            message = e.getMessage();
        }
        model.addAttribute("success", success);
        model.addAttribute("message", message);
        return "/admin/system/mail/test";
    }
}
