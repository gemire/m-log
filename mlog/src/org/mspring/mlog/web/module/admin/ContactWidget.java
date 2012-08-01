/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-26
 * @Description 
 * @TODO 
 */
@Widget
@RequestMapping("/admin")
public class ContactWidget {

    /**
     * 联系方式页面
     * @return
     */
    @RequestMapping("/contact")
    public String contact(){
        return "/admin/contact";
    }
    
    /**
     * 联系方式 widget
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/widget/contact")
    public String contact(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/admin/widget/contact";
    }
}
