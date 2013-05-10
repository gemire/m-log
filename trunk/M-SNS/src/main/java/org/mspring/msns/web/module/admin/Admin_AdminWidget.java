/**
 * 
 */
package org.mspring.msns.web.module.admin;

import org.mspring.msns.support.log.Log;
import org.mspring.platform.web.freemarker.widget.stereotype.Widget;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-18
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin")
public class Admin_AdminWidget extends AbstractAdminWidget {
    /**
     * 后台首页
     * 
     * @return
     */
    @RequestMapping({ "", "/" })
    @Log(action = "登录后台")
    public String index() {
        return "redirect:/admin/index";
    }
    
    @RequestMapping({"/index"})
    public String redirect(){
        return "/admin/index";
    }
}
