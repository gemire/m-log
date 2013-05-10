/**
 * 
 */
package org.mspring.msns.web.module.web;

import org.mspring.msns.web.module.AbstractWidget;
import org.mspring.platform.web.freemarker.FreemarkerVariableNames;
import org.springframework.ui.Model;

/**
 * @author Gao Youbo
 * @since 2013-1-10
 * @Description 
 * @TODO 
 */
public class AbstractWebWidget extends AbstractWidget {
    protected void setCurrnetPage(Model model, String currentPage) {
        model.addAttribute(FreemarkerVariableNames.CURRENT_PAGE, currentPage);
    }
}
