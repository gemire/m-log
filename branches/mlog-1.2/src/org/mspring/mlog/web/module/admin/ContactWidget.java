/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import org.mspring.platform.web.widget.stereotype.Widget;
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
}
