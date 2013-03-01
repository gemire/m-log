/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.common.PageNames;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.platform.persistence.support.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-23
 * @Description
 * @TODO
 */
@Widget("webIndexWidget")
@RequestMapping({ "/", "" })
public class IndexWidget extends AbstractWebWidget {

    /**
     * 文章首页
     * 
     * @param postPage
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping({ "/", "" })
    public String index(@ModelAttribute Page<Post> postPage, HttpServletRequest request, HttpServletResponse response, Model model) {
        setCurrnetPage(model, PageNames.INDEX);
        return "skin:/index";
    }
}
