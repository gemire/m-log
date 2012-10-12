/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Link;
import org.mspring.mlog.service.LinkService;
import org.mspring.mlog.web.module.admin.query.LinkQueryCriterion;
import org.mspring.mlog.web.resolver.QueryParam;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.support.field.ColumnField;
import org.mspring.platform.support.field.Field;
import org.mspring.platform.web.validation.Errors;
import org.mspring.platform.web.widget.stereotype.Widget;
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
@Widget
@RequestMapping("/admin/link")
public class LinkWidget {
    private LinkService linkService;

    @Autowired
    public void setLinkService(LinkService linkService) {
        this.linkService = linkService;
    }

    @RequestMapping("/list")
    public String listLinks(@ModelAttribute Page<Link> linkPage, @ModelAttribute Link link, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (linkPage == null) {
            linkPage = new Page<Link>();
        }
        linkPage.setSort(new Sort("order", Sort.ASC));

        linkPage = linkService.findLinks(linkPage, new LinkQueryCriterion(queryParams));

        List<Field> columnfields = new ArrayList<Field>();
        columnfields.add(new ColumnField("id", "编号"));
        columnfields.add(new ColumnField("name", "名称"));
        columnfields.add(new ColumnField("url", "地址"));
        columnfields.add(new ColumnField("target", "Target"));
        columnfields.add(new ColumnField("order", "排序"));
        columnfields.add(new ColumnField("visable", "是否可见"));

        model.addAttribute("linkPage", linkPage);
        model.addAttribute("columnfields", columnfields);
        model.addAttribute("visable", Link.Visable.getVisableMap());
        return "/admin/link/listLink";
    }

    @RequestMapping("/create")
    public String createLinkView(@ModelAttribute Link link, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("target", Link.Target.getTargetMap());
        model.addAttribute("visable", Link.Visable.getVisableMap());
        return "/admin/link/createLink";
    }

    @RequestMapping("/doCreate")
    public String createLink(@ModelAttribute Link link, HttpServletRequest request, HttpServletResponse response, Model model) {
        linkService.createLink(link);
        return "redirect:/admin/link/list";
    }

    @RequestMapping("/delete")
    public String deleteLink(@RequestParam(required = false) Long[] id, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id != null && id.length > 0) {
            linkService.deleteLinks(id);
        }
        return "redirect:/admin/link/list";
    }

    @RequestMapping("/edit")
    public String editLinkView(@ModelAttribute Link link, HttpServletRequest request, HttpServletResponse response, Model model) {
        link = linkService.getLinkById(link.getId());
        return getEditLinkView(link, model);
    }

    @RequestMapping("/doEdit")
    public String doEditLink(@ModelAttribute Link link, HttpServletRequest request, HttpServletResponse response, Model model) {
        linkService.updateLink(link);
        return "redirect:/admin/link/list";
    }

    private String getEditLinkView(Link link, Model model) {
        model.addAttribute("link", link);
        model.addAttribute("target", Link.Target.getTargetMap());
        model.addAttribute("visable", Link.Visable.getVisableMap());
        return "/admin/link/editLink";
    }
}
