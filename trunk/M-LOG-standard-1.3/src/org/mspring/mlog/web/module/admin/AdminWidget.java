/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-18
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin")
public class AdminWidget extends AbstractAdminWidget {
    /**
     * 后台首页
     * 
     * @return
     */
    @RequestMapping({ "", "/", "/index" })
    public String index() {
        return "/admin/index";
    }
}
