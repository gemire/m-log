/**
 * 
 */
package org.mspring.mlog.web.module.web;

import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-30
 * @Description 
 * @TODO 
 */
@Widget
@RequestMapping("/menu")
public class MenuWidget {
    @RequestMapping({"/", ""})
    public String menu(){
        return "";
    }
}
