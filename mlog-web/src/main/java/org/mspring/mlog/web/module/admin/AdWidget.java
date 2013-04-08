/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Ad;
import org.mspring.mlog.service.AdService;
import org.mspring.mlog.support.log.Log;
import org.mspring.mlog.support.resolver.QueryParam;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.query.AdQueryCriterion;
import org.mspring.mlog.web.module.web.AbstractWebWidget;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2013-3-7
 * @description
 * @TODO
 */
@Widget
@RequestMapping("/admin/ad")
public class AdWidget extends AbstractWebWidget {
    @Autowired
    private AdService adService;

    @RequestMapping("/list")
    @Log
    public String list(@ModelAttribute Page<Ad> adPage, @ModelAttribute Ad ad, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (adPage == null) {
            adPage = new Page<Ad>();
        }
        adPage.setSort(new Sort("id", Sort.DESC));
        adPage = adService.findAdPage(new AdQueryCriterion(queryParams), adPage);
        model.addAttribute("adPage", adPage);
        return "/admin/ad/listAd";
    }

    @RequestMapping("/create")
    public String create(@ModelAttribute Ad ad, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/admin/ad/createAd";
    }

    @RequestMapping("/create/save")
    @Log
    public String create_save(@ModelAttribute Ad ad, HttpServletRequest request, HttpServletResponse response, Model model) {
        ad = adService.createAd(ad);
        return "redirect:/admin/ad/list";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam(required = false) Long id, @ModelAttribute Ad ad, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            Object obj = getSessionAttribute(request, "AdWidget_edit_id");
            if (obj != null) {
                id = (Long) obj;
            }
        }
        if (id == null) {
            return prompt(model, "请先选择要修改的广告");
        }

        ad = adService.getAdById(id);
        if (ad == null) {
            return prompt(model, "请先选择要修改的广告");
        }
        model.addAttribute("ad", ad);
        setSessionAttribute(request, "AdWidget_edit_id", id);
        return "/admin/ad/editAd";
    }

    @RequestMapping("/edit/save")
    @Log
    public String edit_save(@ModelAttribute Ad ad, HttpServletRequest request, HttpServletResponse response, Model model) {
        adService.updateAd(ad);
        return "redirect:/admin/ad/list";
    }

    @RequestMapping("/delete")
    @Log
    public String delete(@RequestParam(required = false) Long[] id, @ModelAttribute Page<Ad> adPage, @ModelAttribute Ad ad, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        adService.deleteAd(id);
        return list(adPage, ad, queryParams, request, response, model);
    }

}
