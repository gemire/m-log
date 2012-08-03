/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Post;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2012-7-30
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/search")
public class SearchWidget extends AbstractWebWidget {
    @RequestMapping({ "/", "" })
    public String searchView(@ModelAttribute Page<Post> postPage, @RequestParam(required = false) String s, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (postPage == null) {
            postPage = new Page<Post>();
        }
        s = StringUtils.encoding(s, "ISO-8859-1", "UTF-8");
        if (StringUtils.isNotBlank(s)) {
            postSearchService.search(postPage, s);
        }
        model.addAttribute("postPage", postPage);
        model.addAttribute("s", s);
        return "skin:/search";
    }
}
