/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.common.PageNames;
import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.entity.Tag;
import org.mspring.mlog.utils.CatalogUtils;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        if (postPage == null) {
            postPage = new Page<Post>();
        }
        if (postPage.getSort() == null) {
            postPage.setSort(new Sort("id", Sort.DESC));
        }
        postService.findPost(postPage, "select post from Post post where post.status = ? order by post.isTop desc, post.id desc", Post.Status.PUBLISH);
        model.addAttribute(FreemarkerVariableNames.POST_PAGE, postPage);
        model.addAttribute("navs", CatalogUtils.getTreeList(catalogService.findAllCatalog()));
        setCurrnetPage(model, PageNames.INDEX);
        return "skin:/index";
    }

    @RequestMapping("/catalog")
    public String archiveByCatalog(@ModelAttribute Page<Post> postPage, @RequestParam(required = false) Long catalogId, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (postPage == null) {
            postPage = new Page<Post>();
        }
        if (postPage.getSort() == null) {
            postPage.setSort(new Sort("postCatalog.PK.post.id", Sort.DESC));
        }
        if (catalogId != null) {
            postService.findPost(postPage, "select postCatalog.PK.post from PostCatalog postCatalog where postCatalog.PK.catalog.id = ? and postCatalog.PK.post.status = ?", new Object[] { catalogId, Post.Status.PUBLISH });
            model.addAttribute(FreemarkerVariableNames.POST_PAGE, postPage);
        }
        // 这个catalogId是判断是否是分类归档的依据
        model.addAttribute("catalogId", catalogId);
        // 设置当前页
        Catalog cat = catalogService.getCatalogById(catalogId);
        model.addAttribute(FreemarkerVariableNames.CATALOG_ARCHIVE_NAME, cat.getName());
        setCurrnetPage(model, PageNames.CATALOG_ARCHIVE);
        return "skin:/index";
    }

    @RequestMapping("/tag")
    public String archiveByTag(@ModelAttribute Page<Post> postPage, @RequestParam(required = false) Long tagId, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (postPage == null) {
            postPage = new Page<Post>();
        }
        if (postPage.getSort() == null) {
            postPage.setSort(new Sort("postTag.PK.post.id", Sort.DESC));
        }
        if (tagId != null) {
            postService.findPost(postPage, "select postTag.PK.post from PostTag postTag where postTag.PK.tag.id = ? and postTag.PK.post.status = ?", new Object[] { tagId, Post.Status.PUBLISH });
            model.addAttribute(FreemarkerVariableNames.POST_PAGE, postPage);
        }
        // 这个tagId是判断是否是tag归档的依据
        model.addAttribute("tagId", tagId);
        // 设置当前页
        Tag tag = tagService.getTagById(tagId);
        model.addAttribute(FreemarkerVariableNames.TAG_ARCHIVE_NAME, tag.getName());
        setCurrnetPage(model, PageNames.TAG_ARCHIVE);
        return "skin:/index";
    }
}
