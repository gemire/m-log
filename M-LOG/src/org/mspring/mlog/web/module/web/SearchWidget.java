/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.lucene.search.Query;
import org.mspring.mlog.common.PageNames;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.service.search.HibernateSearchService;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@Widget("webSearchWidget")
@RequestMapping("/search")
public class SearchWidget extends AbstractWebWidget {
    private static final Logger log = Logger.getLogger(SearchWidget.class);

    @Autowired
    private HibernateSearchService hibernateSearchService;

    @RequestMapping({ "/", "" })
    public String searchView(@ModelAttribute Page<Post> postPage, @RequestParam(required = false) String keyword, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (postPage == null) {
            postPage = new Page<Post>();
        }
        keyword = StringUtils.encoding(keyword, "ISO-8859-1", "UTF-8");
        keyword = keyword.trim();
        String pageStr = request.getParameter("page");
        if (StringUtils.isBlank(pageStr) || !ValidatorUtils.isNumber(pageStr)) {
            postPage.setPageNo(1);
        }
        else {
            postPage.setPageNo(Integer.parseInt(pageStr.trim()));
        }
        if (StringUtils.isNotBlank(keyword)) {
            try {
                // postPage = postSearchService.search(postPage, keyword);
                String[] fields = new String[] { "title", "summary", "content" };
                Query query = hibernateSearchService.getQueryBuilder(Post.class).keyword().onFields(fields).matching(keyword).createQuery();
                postPage = hibernateSearchService.searchPage(postPage, query, Post.class);
            }
            catch (Exception e) {
                // TODO: handle exception
                log.debug(String.format("search for keyword [%keyword] failure!", keyword));
            }
        }
        model.addAttribute(FreemarkerVariableNames.SEARCH_KEYWORD, keyword);
        model.addAttribute(FreemarkerVariableNames.POST_PAGE, postPage);
        setCurrnetPage(model, PageNames.SEARCH);
        return "skin:/search";
    }
}
