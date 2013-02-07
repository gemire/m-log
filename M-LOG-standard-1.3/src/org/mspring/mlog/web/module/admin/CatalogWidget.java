/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.entity.Link;
import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.support.resolver.QueryParam;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.query.CatalogQueryCriterion;
import org.mspring.mlog.web.security.annotation.Premission;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
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
public class CatalogWidget extends AbstractAdminWidget {
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
    @RequestMapping({ "/list" })
    @Premission(item = "11515005")
    public String listCatalog(@ModelAttribute Page<Catalog> catalogPage, @ModelAttribute Catalog catalog, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (catalogPage == null) {
            catalogPage = new Page<Catalog>();
        }
        catalogPage.setSort(new Sort("id desc, order asc", ""));
        catalogPage = catalogService.findCatalog(catalogPage, new CatalogQueryCriterion(queryParams));
        
        Collections.sort(catalogPage.getResult(), new Comparator<Catalog>() {
            @Override
            public int compare(Catalog c1, Catalog c2) {
                // TODO Auto-generated method stub
                int flag = 0;
                if (c2.getOrder() == null && c1.getOrder() != null) {
                    flag = 1;
                }
                else if(c2.getOrder() != null && c1.getOrder() == null) {
                    flag = -1;
                }
                else if (c2.getOrder() != null && c1.getOrder() != null) {
                    flag = c1.getOrder() > c2.getOrder() ? -1 : 1;
                }
                else if (c2.getOrder() == null && c1.getOrder() == null) {
                    flag = c1.getId() > c2.getId() ? 1 : -1;
                }
                return flag;
            }
        });
        
        List<Catalog> catalogs = catalogService.findAllCatalog();

        model.addAttribute("catalogs", catalogs);
        model.addAttribute("catalogPage", catalogPage);
        return "/admin/catalog/listCatalog";
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
    @Premission(item = "11515010")
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
    @RequestMapping("/create/save")
    @Premission(item = "11515010")
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
    @Premission(item = "11515015")
    public String editCatalogView(@ModelAttribute Catalog catalog, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (catalog == null || catalog.getId() == null) {
            Object obj = getSessionAttribute(request, "CatalogWidget_edit_id");
            if (obj != null) {
                Long id = (Long) obj;
                catalog = new Catalog(id);
            }
        }
        setSessionAttribute(request, "CatalogWidget_edit_id", catalog.getId());

        if (catalog == null || catalog.getId() == null) {
            return prompt(model, "请先选择要修改的分类");
        }
        catalog = catalogService.getCatalogById(catalog.getId());
        model.addAttribute("catalog", catalog);
        return "/admin/catalog/editCatalog";
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
    @RequestMapping("/edit/save")
    @Premission(item = "11515015")
    public String doEditCatalog(@ModelAttribute Catalog catalog, HttpServletRequest request, HttpServletResponse response, Model model) {
        catalog.setModifyTime(new Date());
        catalogService.updateCatalog(catalog);
        return "redirect:/admin/catalog/list";
    }
    
    /**
     * ajax设置父分类
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/setParent")
    public String setParent(@RequestParam(required = false) Long id, @RequestParam(required = false) Long parent, HttpServletRequest request, HttpServletResponse response, Model model){
        try {
            catalogService.setCatalogParent(id, parent);
        } catch (Exception e) {
            // TODO: handle exception
            return "false";
        }
        return "true";
    }

    @RequestMapping("/ctrl")
    public String ctrl(@ModelAttribute Page<Catalog> catalogPage, @ModelAttribute Catalog catalog, @QueryParam Map queryParams, 
            @RequestParam(required = false) Long[] ids,
            @RequestParam(required = false) Long[] deleteIds, 
            @RequestParam(required = false) Long[] orders,
            HttpServletRequest request, 
            HttpServletResponse response, 
            Model model) {
        if (deleteIds != null && deleteIds.length > 0) {
            catalogService.deleteCatalog(deleteIds);
        }
        if (orders != null && orders.length > 0) {
            catalogService.setCatalogOrders(ids, orders);
        }
        return listCatalog(catalogPage, catalog, queryParams, request, response, model);
    }

}
