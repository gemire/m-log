/**
 * 
 */
package org.mspring.mlog.web.module.admin.spider;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.mspring.mlog.api.spider.Spider;
import org.mspring.mlog.api.spider.crawler.DefaultCrawler;
import org.mspring.mlog.api.spider.service.SpiderRuleService;
import org.mspring.mlog.api.spider.utils.DocumentUtils;
import org.mspring.mlog.api.spider.vo.Rule;
import org.mspring.mlog.support.log.Log;
import org.mspring.mlog.support.resolver.QueryParam;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.query.RuleQueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 1994-4-14
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/spider/rule")
public class Admin_SpiderRuleWidget extends AbstractSpiderWidget {
    @Autowired
    private SpiderRuleService spiderRuleService;

    @SuppressWarnings("rawtypes")
    @RequestMapping("/list")
    @Log
    public String list(@ModelAttribute Page<Rule> rulePage, @ModelAttribute Rule rule, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (rulePage == null) {
            rulePage = new Page<Rule>();
        }
        rulePage.setSort(new Sort("id", Sort.DESC));
        rulePage = spiderRuleService.findRulePage(new RuleQueryCriterion(queryParams), rulePage);
        model.addAttribute("rulePage", rulePage);
        return "/admin/spider/rule/listRule";
    }

    @RequestMapping("/create")
    public String create_view(@ModelAttribute Rule rule, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/admin/spider/rule/createRule";
    }

    @RequestMapping("/create/save")
    @Log
    public String create_save(@ModelAttribute Rule rule, HttpServletRequest request, HttpServletResponse response, Model model) {
        spiderRuleService.createRule(rule);
        return prompt(model, "系统提示", "规则创建成功", "/admin/spider/rule/list");
    }

    @RequestMapping("/edit")
    public String edit_view(@RequestParam(required = false) Long id, @ModelAttribute Rule rule, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            Object obj = getSessionAttribute(request, "SpiderRuleWidget_edit_id");
            if (obj != null) {
                id = (Long) obj;
            }
        }
        if (id == null) {
            return prompt(model, "请先选择要修改的规则");
        }

        rule = spiderRuleService.getRuleById(id);
        if (rule == null) {
            return prompt(model, "请先选择要修改的规则");
        }
        model.addAttribute("rule", rule);
        setSessionAttribute(request, "SpiderRuleWidget_edit_id", id);
        return "/admin/spider/rule/editRule";
    }

    @RequestMapping("/edit/save")
    @Log
    public String edit_save(@RequestParam(required = false) Long id, @ModelAttribute Rule rule, HttpServletRequest request, HttpServletResponse response, Model model) {
        spiderRuleService.updateRule(rule);
        return prompt(model, "系统提示", "规则修改成功", "/admin/spider/rule/list");
    }

    @RequestMapping("/run_view")
    public String run_view(@RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            Object obj = getSessionAttribute(request, "SpiderRuleWidget_run_view_id");
            if (obj != null) {
                id = (Long) obj;
            }
        }
        if (id == null) {
            return prompt(model, "请选择要运行的规则");
        }
        Rule rule = spiderRuleService.getRuleById(id);
        model.addAttribute("rule", rule);
        setSessionAttribute(request, "SpiderRuleWidget_run_view_id", id);
        return "/admin/spider/rule/run";
    }

    @RequestMapping("/run")
    public void run(@RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            return;
        }
        Rule rule = spiderRuleService.getRuleById(id);
        Spider spilder = new Spider(new DefaultCrawler());

        Document listPageDoc = DocumentUtils.getDocument(rule.getUrl());
        List<String> urls = spilder.getUrls(listPageDoc, rule);

        Document contentDoc = null;
        for (String url : urls) {
            if (StringUtils.isNotBlank(url)) {
                contentDoc = DocumentUtils.getDocument(url);
                String title = spilder.getTitle(contentDoc, rule);
                Element content = spilder.getContent(contentDoc, rule);
            }
        }
    }
}
