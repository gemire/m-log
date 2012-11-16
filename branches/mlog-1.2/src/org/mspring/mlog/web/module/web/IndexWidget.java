/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.web.common.PageNames;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
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
        postService.findPost(postPage, "select post from Post post where post.status = ? order by post.createTime desc", Post.Status.PUBLISH);
        model.addAttribute(FreemarkerVariableNames.POST_PAGE, postPage);
        setCurrnetPage(model, PageNames.INDEX);
        return "skin:/index";
    }

    @RequestMapping("/catalog/{catalog}")
    public String archiveByCatalog(@ModelAttribute Page<Post> postPage, @PathVariable String catalog, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (postPage == null) {
            postPage = new Page<Post>();
        }
        if (postPage.getSort() == null) {
            postPage.setSort(new Sort("postCatalog.PK.post.id", Sort.DESC));
        }
        if (StringUtils.isNotBlank(catalog)) {
            postService.findPost(postPage, "select postCatalog.PK.post from PostCatalog postCatalog where postCatalog.PK.catalog.name = ? and postCatalog.PK.post.status = ?", new Object[] { catalog.trim(), Post.Status.PUBLISH });
            model.addAttribute(FreemarkerVariableNames.POST_PAGE, postPage);
        }
        model.addAttribute(FreemarkerVariableNames.CATALOG_ARCHIVE_NAME, catalog);
        setCurrnetPage(model, PageNames.CATALOG_ARCHIVE);
        return "skin:/index";
    }
}
