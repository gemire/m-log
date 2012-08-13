/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Post;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-24
 * @Description
 * @TODO
 */
@Widget
@RequestMapping({ "/", "" })
@SuppressWarnings("unused")
public class SingleWidget extends AbstractWebWidget {

    @RequestMapping("/post")
    private String single(HttpServletRequest request, HttpServletResponse response, Model model) {
        Object postObj = request.getAttribute("post");
        if (postObj != null) {
            model.addAttribute(FreemarkerVariableNames.POST, postObj);
        }
        return "skin:/single";
    }
}
