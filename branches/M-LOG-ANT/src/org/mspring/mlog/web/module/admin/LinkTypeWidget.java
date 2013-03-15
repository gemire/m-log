/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.LinkType;
import org.mspring.mlog.service.LinkTypeService;
import org.mspring.mlog.support.log.Log;
import org.mspring.mlog.support.resolver.QueryParam;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
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
 * @since 2012-12-1
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/linkType")
public class LinkTypeWidget extends AbstractAdminWidget {
    private LinkTypeService linkTypeService;

    @Autowired
    public void setLinkTypeService(LinkTypeService linkTypeService) {
        this.linkTypeService = linkTypeService;
    }

    @RequestMapping("/list")
    @Log
    public String listLinkType(@ModelAttribute Page<LinkType> linkTypePage, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (linkTypePage == null) {
            linkTypePage = new Page<LinkType>();
        }
        linkTypePage.setSort(new Sort("order", Sort.ASC));

        linkTypePage = linkTypeService.findLinkTypes(linkTypePage, "select linkType from LinkType linkType");

        List<Field> columnfields = new ArrayList<Field>();
        columnfields.add(new ColumnField("id", "编号"));
        columnfields.add(new ColumnField("name", "名称"));
        columnfields.add(new ColumnField("order", "排序"));

        model.addAttribute("columnfields", columnfields);
        model.addAttribute("visable", LinkType.Visable.getVisableMap());
        model.addAttribute("linkTypePage", linkTypePage);
        return "/admin/link/listLinkType";
    }

    /**
     * 创建分类页面
     * 
     * @param linkType
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/create")
    public String createLinkType(@ModelAttribute LinkType linkType, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("visable", LinkType.Visable.getVisableMap());
        return "/admin/link/createLinkType";
    }

    /**
     * 执行创建分类
     * 
     * @param linkType
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/create/save")
    @Log
    public String create_save(@ModelAttribute LinkType linkType, HttpServletRequest request, HttpServletResponse response, Model model) {
        linkTypeService.createLinkType(linkType);
        return "redirect:/admin/linkType/list";
    }

    @RequestMapping("/edit")
    public String editLinkType(@RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            Object obj = getSessionAttribute(request, "LinkTypeWidget_edit_id");
            if (obj != null) {
                id = (Long) obj;
            }
        }
        setSessionAttribute(request, "LinkTypeWidget_edit_id", id);

        if (id == null) {
            return prompt(model, "请先选择要修改的链接分类");
        }
        LinkType lt = linkTypeService.getLinkTypeById(id);
        model.addAttribute("linkType", lt);
        model.addAttribute("visable", LinkType.Visable.getVisableMap());
        return "/admin/link/editLinkType";
    }

    @RequestMapping("/edit/save")
    @Log
    public String edit_save(@ModelAttribute LinkType linkType, HttpServletRequest request, HttpServletResponse response, Model model) {
        linkTypeService.updateLinkType(linkType);
        return "redirect:/admin/linkType/list";
    }

    /**
     * 
     * @param linkTypePage
     * @param queryParams
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/ctrl")
    @Log
    public String ctrl(@ModelAttribute Page<LinkType> linkTypePage, @QueryParam Map queryParams, @RequestParam(required = false) Long[] ids, @RequestParam(required = false) String[] names, @RequestParam(required = false) Long[] orders, @RequestParam(required = false) Long[] deleteIds, @RequestParam(required = false) Long[] visableIds, HttpServletRequest request, HttpServletResponse response, Model model) {
        // 删除
        if (deleteIds != null && deleteIds.length > 0) {
            linkTypeService.deleteLinkType(deleteIds);
        }
        // 设置显示
        if (visableIds != null && visableIds.length > 0) {
            linkTypeService.setLinkTypeVisable(true, visableIds);
        }
        // 设置不显示
        Long[] hiddenId = ArrayUtils.removeAll(ids, visableIds, Long.class);
        if (hiddenId != null && hiddenId.length > 0) {
            linkTypeService.setLinkTypeVisable(false, hiddenId);
        }
        // 设置排序
        linkTypeService.updateLinkTypeOrders(ids, orders);

        // 设置名称
        linkTypeService.updateLinkTypeNames(ids, names);
        return listLinkType(linkTypePage, queryParams, request, response, model);
    }

}
