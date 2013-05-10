/**
 * 
 */
package org.mspring.msns.web.module.admin;

import org.mspring.platform.web.freemarker.widget.stereotype.Widget;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-26
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin")
public class Admin_ContactWidget extends AbstractAdminWidget {

    /**
     * 联系方式页面
     * 
     * @return
     */
    @RequestMapping("/contact")
    public String contact() {
        return "/admin/contact";
    }
}
