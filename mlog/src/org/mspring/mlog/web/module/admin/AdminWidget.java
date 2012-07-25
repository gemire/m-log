/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-18
 * @Description 
 * @TODO 
 */
@Widget
@RequestMapping("/admin")
public class AdminWidget {
    /**
     * 后台首页
     * @return
     */
    @RequestMapping(value = { "/admin/index" ,"/admin", "/admin/" })
    public String index() {
        return "/admin/index";
    }
    
    @RequestMapping("/about")
    public String about(){
        return "/admin/about";
    }
    
    @RequestMapping("/about")
    public String contact(){
        return "/admin/contact";
    }
}
