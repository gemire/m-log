/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.service.search.PostSearchService;
import org.mspring.platform.core.ContextManager;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-23
 * @Description
 * @TODO
 */
@Widget
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
        if (postPage == null) {
            postPage = new Page<Post>();
        }
        if (postPage.getSort() == null) {
            postPage.setSort(new Sort("id", Sort.DESC));
        }
        postService.findPost(postPage, "select post from Post post");
        model.addAttribute("postPage", postPage);
        return "skin:/index";
    }

    @RequestMapping("/catalog/{catalog}")
    public String archiveByCatalog(@ModelAttribute Page<Post> postPage, @PathVariable String catalog, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (postPage == null) {
            postPage = new Page<Post>();
        }
        if (postPage.getSort() == null) {
            postPage.setSort(new Sort("id", Sort.DESC));
        }
        if (StringUtils.isNotBlank(catalog)) {
            postService.findPost(postPage, "select post from Post post where post.catalog.name = ?", catalog.trim());
            model.addAttribute("postPage", postPage);
        }
        return "skin:/index";
    }
}
