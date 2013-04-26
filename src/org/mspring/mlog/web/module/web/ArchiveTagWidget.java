/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.common.PageNames;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.entity.Tag;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.service.TagService;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.platform.persistence.support.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2013-3-4
 * @description
 * @TODO
 */
@Widget
@RequestMapping("/")
public class ArchiveTagWidget extends AbstractWebWidget {
    @Autowired
    private PostService postService;
    @Autowired
    private TagService tagService;

    @RequestMapping("/tag")
    public String archiveByTag(@ModelAttribute Page<Post> postPage, @RequestParam(required = false) Long tagId, HttpServletRequest request, HttpServletResponse response, Model model) {
        postService.findPostByTag(postPage, tagId);

        // 这个tagId是判断是否是tag归档的依据
        model.addAttribute("tagId", tagId);
        model.addAttribute(FreemarkerVariableNames.POST_PAGE, postPage);

        // 设置当前页
        Tag tag = tagService.getTagById(tagId);
        model.addAttribute(FreemarkerVariableNames.TAG_ARCHIVE_NAME, tag.getName());
        setCurrnetPage(model, PageNames.TAG_ARCHIVE);
        return "skin:/archiveTag";
    }
}
