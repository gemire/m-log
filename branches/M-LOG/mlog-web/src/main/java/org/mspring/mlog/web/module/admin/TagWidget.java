package org.mspring.mlog.web.module.admin;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Tag;
import org.mspring.mlog.service.TagService;
import org.mspring.mlog.support.log.Log;
import org.mspring.mlog.support.resolver.QueryParam;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.query.TagQueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Hu Hongyu
 * @since 2013-03-05
 */
@Widget
@RequestMapping("/admin/tag")
public class TagWidget extends AbstractAdminWidget {
    private static final Logger log = Logger.getLogger(TagWidget.class);

    private TagService tagService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping("/list")
    @Log
    public String listTag(@ModelAttribute Page<Tag> tagPage, @ModelAttribute Tag tag, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (tagPage == null) {
            tagPage = new Page<Tag>();
        }
        tagPage.setSort(new Sort("id desc", ""));

        if (tag == null) {
            tag = new Tag();
        }
        tagPage = tagService.findTag(tagPage, new TagQueryCriterion(queryParams));
        model.addAttribute("tagPage", tagPage);
        return "/admin/tag/listTag";
    }

    @RequestMapping("/delete")
    @Log
    public String deleteTag(@RequestParam(required = false) Long[] id, @ModelAttribute Page<Tag> tagPage, @ModelAttribute Tag tag, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id != null && id.length > 0) {
            tagService.deleteTag(id);
        }
        return listTag(tagPage, tag, queryParams, request, response, model);
    }

    @RequestMapping("/edit")
    public String editTagView(@RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            Object obj = getSessionAttribute(request, "TagWidget_edit_id");
            if (obj != null) {
                id = (Long) obj;
            }
        }
        if (id == null) {
            return prompt(model, "请先选择要修改的tag");
        }

        Tag tag = tagService.getTagById(id);
        if (tag == null) {
            return prompt(model, "请先选择要修改的tag");
        }
        model.addAttribute("tag", tag);
        setSessionAttribute(request, "TagWidget_edit_id", id);
        return "/admin/tag/editTag";
    }

    @RequestMapping("/edit/save")
    @Log
    public String editTagSave(@ModelAttribute Tag tag, HttpServletRequest request, HttpServletResponse response, Model model) {
        tagService.modifyTag(tag);
        return "redirect:/admin/tag/list";
    }

    @RequestMapping("/create")
    public String createTagView(@ModelAttribute Tag tag, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/admin/tag/createTag";
    }

    @RequestMapping("/create/save")
    @Log
    public String createTag(@ModelAttribute Tag tag, HttpServletRequest request, HttpServletResponse response, Model model) {
        System.out.println(tag.getName());
        tag.setCreateTime(new Date());
        tagService.createTag(tag);
        return "redirect:/admin/tag/list";
    }

}
