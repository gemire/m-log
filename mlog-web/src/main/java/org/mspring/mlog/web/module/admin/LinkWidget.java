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
import org.mspring.mlog.service.LinkTypeService;
import org.mspring.mlog.support.log.Log;
import org.mspring.mlog.support.resolver.QueryParam;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.query.LinkQueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.support.field.ColumnField;
import org.mspring.platform.support.field.Field;
import org.mspring.platform.utils.ArrayUtils;
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
public class LinkWidget extends AbstractAdminWidget {
    @Autowired
    private LinkService linkService;
    @Autowired
    private LinkTypeService linkTypeService;

    @RequestMapping("/list")
    @Log
    public String listLinks(@ModelAttribute Page<Link> linkPage, @ModelAttribute Link link, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (linkPage == null) {
            linkPage = new Page<Link>();
        }
        linkPage.setSort(new Sort("type.id desc, order asc, visable desc", ""));
        // linkPage.setSort(new Sort("type.id asc, visable desc", ""));

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
        model.addAttribute("types", linkTypeService.findAllVisable());
        return "/admin/link/listLink";
    }

    @RequestMapping("/create")
    public String createLinkView(@ModelAttribute Link link, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("target", Link.Target.getTargetMap());
        model.addAttribute("visable", Link.Visable.getVisableMap());
        model.addAttribute("types", linkTypeService.findAllVisable());
        return "/admin/link/createLink";
    }

    @RequestMapping("/create/save")
    @Log
    public String createLink(@ModelAttribute Link link, HttpServletRequest request, HttpServletResponse response, Model model) {
        linkService.createLink(link);
        return "redirect:/admin/link/list";
    }

    @RequestMapping("/delete")
    @Log
    public String deleteLink(@RequestParam(required = false) Long[] id, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id != null && id.length > 0) {
            linkService.deleteLinks(id);
        }
        return "redirect:/admin/link/list";
    }

    @RequestMapping("/edit")
    public String editLinkView(@ModelAttribute Link link, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (link == null || link.getId() == null) {
            Object obj = getSessionAttribute(request, "LinkWidget_edit_id");
            if (obj != null) {
                Long id = (Long) obj;
                link = new Link(id);
            }
        }
        setSessionAttribute(request, "LinkWidget_edit_id", link.getId());

        if (link == null || link.getId() == null) {
            return prompt(model, "请先选择要修改的链接");
        }
        link = linkService.getLinkById(link.getId());
        model.addAttribute("link", link);
        model.addAttribute("target", Link.Target.getTargetMap());
        model.addAttribute("visable", Link.Visable.getVisableMap());
        model.addAttribute("types", linkTypeService.findAllVisable());
        return "/admin/link/editLink";
    }

    @RequestMapping("/edit/save")
    @Log
    public String edit_save(@ModelAttribute Link link, HttpServletRequest request, HttpServletResponse response, Model model) {
        linkService.updateLink(link);
        return "redirect:/admin/link/list";
    }

    @RequestMapping("/ctrl")
    @Log
    public String ctrl(@ModelAttribute Page<Link> linkPage, @ModelAttribute Link link, @QueryParam Map queryParams, @RequestParam(required = false) Long[] ids, @RequestParam(required = false) Long[] orders, @RequestParam(required = false) Long[] deleteIds, @RequestParam(required = false) Long[] visableIds, HttpServletRequest request, HttpServletResponse response, Model model) {
        // 删除
        if (deleteIds != null && deleteIds.length > 0) {
            linkService.deleteLinks(deleteIds);
        }
        // 设置显示
        if (visableIds != null && visableIds.length > 0) {
            linkService.setLinkVisable(true, visableIds);
        }
        // 设置不现实
        Long[] hiddenId = (Long[]) ArrayUtils.removeAll(ids, visableIds, Long.class);
        if (hiddenId != null && hiddenId.length > 0) {
            linkService.setLinkVisable(false, hiddenId);
        }
        // 设置排序
        linkService.updateLinkOrders(ids, orders);
        return listLinks(linkPage, link, queryParams, request, response, model);
    }

}
