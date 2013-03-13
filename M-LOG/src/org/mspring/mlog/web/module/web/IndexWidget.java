/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.common.PageNames;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.utils.CatalogUtils;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private PostService postService;
    @Autowired
    private CatalogService catalogService;

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
        postService.findPost(postPage, "select post from Post post where post.status = ? order by post.isTop desc, post.id desc", new Object[] { Post.Status.PUBLISH });
        model.addAttribute(FreemarkerVariableNames.POST_PAGE, postPage);
        model.addAttribute("navs", CatalogUtils.getTreeList(catalogService.findAllCatalog()));
        setCurrnetPage(model, PageNames.INDEX);
        return "skin:/index";
    }
}
