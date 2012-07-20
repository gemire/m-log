/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.service.CatalogService;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.support.field.ColumnField;
import org.mspring.platform.support.field.Field;
import org.mspring.platform.web.widget.stereotype.Widget;

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

    /**
     * @param catalogService
     *            the catalogService to set
     */
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
    @RequestMapping("/list")
    public String listCatalog(@ModelAttribute Page<Catalog> catalogPage, HttpServletRequest request, HttpServletResponse response, Model model) {
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
     * 创建分类页面
     * @param catalog
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/create")
    public String createCatalogView(@ModelAttribute Catalog catalog, HttpServletRequest request, HttpServletResponse response, Model model){
        return "/admin/catalog/createCatalog";
    }
    
    /**
     * 创建分类
     * @param catalog
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/doCreate")
    public String doCreateCatalog(@ModelAttribute Catalog catalog, HttpServletRequest request, HttpServletResponse response, Model model){
        if (catalog.getCreateTime() == null) {
            catalog.setCreateTime(new Date());
        }
        catalogService.createCatalog(catalog);
        return "redirect:/admin/catalog/list";
    }
}
