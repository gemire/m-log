/**
 * 
 */
package org.mspring.msns.web.module.user;

import org.mspring.platform.web.freemarker.widget.stereotype.Widget;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2013-5-9
 * @description
 * @TODO
 */
@Widget
@RequestMapping("/user")
public class PageWidget {
    @RequestMapping("/{ftl}")
    public String page(@PathVariable String ftl) {
        System.out.println(ftl);
        return "skin:/" + ftl;
    }
}