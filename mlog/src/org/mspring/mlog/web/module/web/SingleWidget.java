/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Post;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.resolver.stereotype.UrlVariable;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/{url:.*}")
    private String single(@PathVariable String url, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (StringUtils.isNotBlank(url)) {
            url = "/" + url;
            // 文章信息
            Post post = postService.getPostByUrl(url);
            model.addAttribute("post", post);
        }
        return "skin:/single";
    }
}
