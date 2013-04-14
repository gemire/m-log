/**
 * 
 */
package org.mspring.mlog.web.module.admin.spider;

import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 1994-4-14
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/spider/rule")
public class Admin_SpiderRuleWidget extends AbstractSpiderWidget {
    public String list() {
        return "";
    }
}
