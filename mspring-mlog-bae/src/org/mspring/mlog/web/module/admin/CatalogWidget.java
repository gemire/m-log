/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.support.field.ColumnField;
import org.mspring.platform.support.field.Field;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Gao Youbo
 * @since 2012-7-18
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/catalog")
public class CatalogWidget {
    private CatalogService catalogService;

    @Autowired
    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    /**
     * 列表
     * 
     * @param page
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping({ "/list", "/", "" })
    public String listCatalog(@ModelAttribute Page<Catalog> catalogPage, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (catalogPage == null) {
            catalogPage = new Page<Catalog>();
        }
        catalogPage.setSort(new Sort("id", Sort.DESC));

        catalogPage = catalogService.findCatalog(catalogPage, "select c from Catalog c");

        List<Field> columnfields = new ArrayList<Field>();
        columnfields.add(new ColumnField("id", "编号"));
        columnfields.add(new ColumnField("name", "名称"));
        columnfields.add(new ColumnField("createTime", "创建时间"));
        columnfields.add(new ColumnField("modifyTime", "修改时间"));
        columnfields.add(new ColumnField("order", "排序"));

        model.addAttribute("catalogPage", catalogPage);
        model.addAttribute("columnfields", columnfields);

        return "/admin/catalog/listCatalog";
    }

    /**
     * 删除
     * 
     * @param id
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/delete")
    public String deleteCatalog(@RequestParam Long[] id, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id != null && id.length > 0) {
            catalogService.deleteCatalog(id);
        }
        return "redirect:/admin/catalog/list";
    }

    /**
     * 创建分类页面
     * 
     * @param catalog
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/create")
    public String createCatalogView(@ModelAttribute Catalog catalog, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/admin/catalog/createCatalog";
    }

    /**
     * 创建分类
     * 
     * @param catalog
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/doCreate")
    public String doCreateCatalog(@ModelAttribute Catalog catalog, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (catalog.getCreateTime() == null) {
            catalog.setCreateTime(new Date());
        }
        catalogService.createCatalog(catalog);
        return "redirect:/admin/catalog/list";
    }

    /**
     * 修改页面
     * 
     * @param catalog
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String editCatalogView(@ModelAttribute Catalog catalog, HttpServletRequest request, HttpServletResponse response, Model model) {
        catalog = catalogService.getCatalogById(catalog.getId());
        return getEditCatalogView(catalog, model);
    }

    /**
     * 执行修改操作
     * 
     * @param catalog
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/doEdit")
    public String doEditCatalog(@ModelAttribute Catalog catalog, HttpServletRequest request, HttpServletResponse response, Model model) {
        catalog.setModifyTime(new Date());
        catalogService.updateCatalog(catalog);
        return "redirect:/admin/catalog/list";
    }

    private String getEditCatalogView(Catalog catalog, Model model) {
        model.addAttribute("catalog", catalog);
        return "/admin/catalog/editCatalog";
    }
    
    /**
     * 判断分类名称是否存在
     * @param name
     * @param id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/catalogNameExists")
    @ResponseBody
    public String catalogNameExists(@RequestParam(required = false) String name, @RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response){
        if (StringUtils.isBlank(name)) {
            return "true";
        }
        boolean flag = catalogService.catalogExists(name, id);
        return flag ? "true" : "false";
    }
}
