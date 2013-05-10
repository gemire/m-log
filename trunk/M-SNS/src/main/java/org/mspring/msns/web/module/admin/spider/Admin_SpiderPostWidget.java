/**
 * 
 */
package org.mspring.msns.web.module.admin.spider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.msns.api.spider.service.SpiderPostService;
import org.mspring.msns.api.spider.service.SpiderRuleService;
import org.mspring.msns.api.spider.vo.Rule;
import org.mspring.msns.api.spider.vo.SpiderPost;
import org.mspring.msns.core.ServiceFactory;
import org.mspring.msns.entity.Catalog;
import org.mspring.msns.support.resolver.QueryParam;
import org.mspring.msns.web.query.SpiderPostQueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.utils.ArrayUtils;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.freemarker.widget.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2013-5-3
 * @description
 * @TODO
 */
@Widget
@RequestMapping("/admin/spider/post")
public class Admin_SpiderPostWidget extends AbstractSpiderWidget {
    @Autowired
    private SpiderPostService spiderPostService;
    @Autowired
    private SpiderRuleService spiderRuleService;

    @RequestMapping("/list")
    public String list(@ModelAttribute Page<SpiderPost> spiderPostPage, @ModelAttribute SpiderPost spiderPost, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (spiderPostPage == null) {
            spiderPostPage = new Page<SpiderPost>();
        }
        spiderPostPage.setSort(new Sort("id", Sort.DESC));
        spiderPostPage = spiderPostService.findSpiderPostPage(new SpiderPostQueryCriterion(queryParams), spiderPostPage);

        List<Rule> rules = spiderRuleService.findAllEnabledRules();

        model.addAttribute("spiderPostPage", spiderPostPage);
        model.addAttribute("rules", rules);
        model.addAllAttributes(queryParams);
        
        // 文章分类
        List<Catalog> catalogs = ServiceFactory.getCatalogService().findAllCatalog();
        model.addAttribute("catalogs", catalogs);
        return "/admin/spider/post/listSpiderPost";
    }

    /**
     * 发布文章
     * 
     * @param id
     *            要发布的采集文章编号
     * @param catalog
     *            发不到的分类
     * @param spiderPostPage
     * @param spiderPost
     * @param queryParams
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/publish")
    public String publish(@RequestParam(required = false) Long[] id, @RequestParam(required = false) String catalogs, @ModelAttribute Page<SpiderPost> spiderPostPage, @ModelAttribute SpiderPost spiderPost, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            return prompt(model, "请选择要操作的对象");
        }
        List<Long> list = new ArrayList<Long>();
        if (StringUtils.isNotBlank(catalogs)) {
            String[] catalogArray = StringUtils.split(catalogs, ",");
            for (String str : catalogArray) {
                list.add(new Long(str));
            }
        }
        spiderPostService.publishPost(ArrayUtils.listToArray(list, Long.class), id);
        return list(spiderPostPage, spiderPost, queryParams, request, response, model);
    }

    /**
     * 删除文章
     * 
     * @param id
     * @param spiderPostPage
     * @param spiderPost
     * @param queryParams
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(required = false) Long[] id, @ModelAttribute Page<SpiderPost> spiderPostPage, @ModelAttribute SpiderPost spiderPost, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            return prompt(model, "请选择要操作的对象");
        }
        spiderPostService.deleteSpiderPost(id);
        return list(spiderPostPage, spiderPost, queryParams, request, response, model);
    }

    /**
     * 变更发布状态
     * 
     * @param id
     * @param posted
     * @param spiderPostPage
     * @param spiderPost
     * @param queryParams
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/changePosted")
    public String changePosted(@RequestParam(required = false) Long[] id, @RequestParam(required = false) Boolean posted, @ModelAttribute Page<SpiderPost> spiderPostPage, @ModelAttribute SpiderPost spiderPost, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            return prompt(model, "请选择要操作的对象");
        }
        spiderPostService.changePosted(posted, id);
        return list(spiderPostPage, spiderPost, queryParams, request, response, model);
    }
}
