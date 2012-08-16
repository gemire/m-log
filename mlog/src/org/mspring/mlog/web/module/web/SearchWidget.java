/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
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
    private static final Logger log = Logger.getLogger(SearchWidget.class);

    @RequestMapping({ "/", "" })
    public String searchView(@ModelAttribute Page<Post> postPage, @RequestParam(required = false) String keyword, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (postPage == null) {
            postPage = new Page<Post>();
        }
        keyword = StringUtils.encoding(keyword, "ISO-8859-1", "UTF-8");
        keyword = keyword.trim();
        if (StringUtils.isNotBlank(keyword)) {
            try {
                postPage = postSearchService.search(postPage, keyword);
            }
            catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                log.debug(String.format("search for keyword [%keyword] failure!", keyword));
            }
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute(FreemarkerVariableNames.POST_PAGE, postPage);
        return "skin:/search";
    }
}
