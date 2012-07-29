/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Post;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.utils.ValidatorUtils;
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
public class SingleWidget extends AbstractWebWidget {

    @RequestMapping("/single")
    private String single(HttpServletRequest request, HttpServletResponse response, Model model) {
        String idStr = request.getParameter("id");
        if (!StringUtils.isBlank(idStr) && ValidatorUtils.isNumber(idStr)) {
            // 文章信息
            Post post = postService.getPostById(new Long(idStr));
            model.addAttribute("post", post);
        }
        return "skin:/single";
    }

}
